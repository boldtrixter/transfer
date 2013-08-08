/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.service;

import com.pb.business.entity.Person;
import com.pb.business.entity.Token;
import com.pb.business.entity.Transfertable;
import com.pb.business.message.Response;
import com.pb.business.message.ServerException;
import com.pb.business.message.AuthorizationResponse;
import com.pb.business.json.entity.Data;
import com.pb.business.message.ServerResponse;
import com.pb.business.json.entity.UserData;
import com.pb.business.dao.BusinessDAO;
import com.pb.business.tools.Constant;
import com.pb.business.tools.EKB;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dmitry
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessDAO dao;

    @Override
    public List<Transfertable> getAllMakers(String userToken) throws ServerException {
//        if (checkToken(userToken) != 0) {
//            throw new ServerException("Иди авторизируйся!", "-112");
//        }
        checkToken(userToken);
        return dao.getAllMakers();
    }
    
    @Override
    public String hiberTest(){
        dao.hiberTest();
        return "ok";
    }

    @Override
    public ServerResponse checkData(Data data) throws ServerException {

       //проверка на существование и время жизни токена
        if (!checkToken(data.getToken())) {
            throw new ServerException("Token is dead, you need autorization", "-1");
        }
        //checkToken(data.getToken());
        //Проверка номера телефона отправителя
        if (!checkPhone(data.getSender().getPhoneNumber())) {
            throw new ServerException(Response.IncorrectSenderPhone.MESSAGE, Response.IncorrectSenderPhone.CODE);
        }

        //Проверка номера телефона получателя
        if (!checkPhone(data.getReceiver().getPhoneNumber())) {
            throw new ServerException(Response.IncorrectReceiverPhone.MESSAGE, Response.IncorrectReceiverPhone.CODE);
        }

        //Имя товара
        if (data.getName().equals("")) {
            throw new ServerException(Response.IncorrectProductName.MESSAGE, Response.IncorrectProductName.CODE);
        }
        
        if(data.getScanCode().equals("") && data.getStandNumber().equals("")){
            throw new ServerException("Пустые штрихкод/номер авто", "-99");
        }
        
        if(!data.getScanCode().equals("")){
            if(!data.getStandNumber().equals("")){
                throw new ServerException("Нужен либо штрихкод/либо номер кузова", "-99");
            }
        }
        EKB ekb = new EKB();
        try {
            Person sender = ekb.getPersonDetailsByPhone(data.getSender().getPhoneNumber());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(BusinessServiceImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            Person receiver = ekb.getPersonDetailsByPhone(data.getReceiver().getPhoneNumber());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(BusinessServiceImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
//        
//        Person sender = dao.getPersonByPhone(data.getSender().getPhoneNumber());
//        
////        if(sender == null){
////            sender = 
////        }
//        
//        
//        
//        Product product = new Product();
//        product.setName(data.getName());
//        product.setScancode(data.getScanCode());
//        
//        Unit unit = new Unit();
//        unit.setName(data.getUnits());
        
        
        return dao.addTransfer(data);

    }

    @Override
    public ServerResponse deleteTransfer(String transferId) throws ServerException {

        //checkToken(transferId);

        try {
            Integer.parseInt(transferId);
        } catch (Exception e) {
            throw new ServerException(Response.IncorrectID.MESSAGE, Response.IncorrectID.CODE);
        }

        ServerResponse sr = new ServerResponse();
        sr.setRef(Response.Done.CODE);
        sr.setNote(Response.Done.MESSAGE);

        return sr;
    }

    @Override
    public ServerResponse getSmsPassword(String phone) throws ServerException {
        if (!checkPhone(phone)) {
            throw new ServerException(Response.IncorrectPhone.MESSAGE, Response.IncorrectPhone.CODE);
        }
        //AuthorizationResponse ar = new AuthorizationResponse();
        ServerResponse sr = new ServerResponse();
        //Получили ответ от сервера
        String response = sendSmsFromOTP(phone);
        if (response.indexOf("<package_status>ok</package_status>") != -1) {
            sr.setRef(Response.Done.CODE);
            sr.setNote(Response.Done.MESSAGE + " (" + phone + ")");
            return sr;
        } else {
            sr.setRef(Response.AuthorizationError.CODE);
            sr.setNote(Response.AuthorizationError.MESSAGE);
            return sr;
        }
    }

    @Override
    public AuthorizationResponse verifyUser(UserData userData) throws ServerException {

        if (!checkPhone(userData.getPhone())) {
            throw new ServerException(Response.IncorrectPhone.MESSAGE, Response.IncorrectPhone.CODE);
        }

        if (!(userData.getSmsPassword().equals(""))) {
            try {
                Integer.parseInt(userData.getSmsPassword());
            } catch (Exception e) {
                throw new ServerException(Response.AuthorizationError.MESSAGE, Response.AuthorizationError.MESSAGE);
            }
        }

        String response = checkSmsPassword(userData.getPhone(), userData.getSmsPassword());

        AuthorizationResponse ar = new AuthorizationResponse();
        if (response.indexOf("<package_status>ok</package_status>") != -1) {
            String token = generateToken(userData.getPhone());
            ar.setRes(Response.Done.CODE);
            ar.setNote(Response.Done.MESSAGE);
            ar.setToken(token);
            return ar;
        } else {
            ar.setRes("-10");
            ar.setNote("FAILED");
            ar.setToken("not_session_token");
            return ar;
        }

    }

    private String generateToken(String phone) {
        String token = (new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime())) + "_" + phone.substring(1);
        // генерация времени жизни и сохранение в бд
        //String dateChange = Calendar.getInstance().getTime().toString();
        dao.saveToken(token, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(Calendar.getInstance().getTime()));
        return token;
    }

    private boolean checkToken(String token) throws ServerException {
        // send token to db and check!
        //20130731121746_380934682670
        Token t = dao.getToken(token);

        if (t == null) {
            throw new ServerException(Response.AuthorizationError.MESSAGE, Response.AuthorizationError.CODE);
            //return -1;
        }

        // Определить не умер ли токен, если да удалить и послать на авторизацию, если нет то обнулить время жизни

        Calendar current = Calendar.getInstance();
        current.add(Calendar.MINUTE, - Constant.TOKEN_LIFETIME);
        //TokenEntity te = lte.get(0);
        //System.out.println((new SimpleDateFormat("dd.MM.yyyy HH-mm-ss")).format(current.getTime()));
        Logger.getLogger(BusinessServiceImpl.class.getName()).log(Level.INFO, (new SimpleDateFormat("dd.MM.yyyy HH-mm-ss")).format(current.getTime()) + "CURRENT!!!");
        Logger.getLogger(BusinessServiceImpl.class.getName()).log(Level.INFO, (new SimpleDateFormat("dd.MM.yyyy HH-mm-ss")).format(t.getDatechange()) + "TOKEN!!!");
        //System.out.println((new SimpleDateFormat("dd.MM.yyyy HH-mm-ss")).format(t.getDatechange()));

        if (t.getDatechange().after(current.getTime())) {
            t.setDatechange(Calendar.getInstance().getTime());
            Logger.getLogger(BusinessServiceImpl.class.getName()).log(Level.INFO, (new SimpleDateFormat("dd.MM.yyyy HH-mm-ss")).format(t.getDatechange()) + " TOKEN CHANGED!!!");

            //проапдейтить время жизни токена в базу
            dao.updateToken(t.getDatechange(), token);

            return true;
        }
        //УДАЛИТЬ ИЗ БД т.к. умер. В будущем планируеться добавить сервис удаляющий токены
        dao.deleteToken(token);

        throw new ServerException(Response.TokenLifetimeEnd.MESSAGE, Response.TokenLifetimeEnd.CODE);
        //return -2;
    }

    private boolean checkPhone(String phone) {

        Pattern ua = Pattern.compile("\\+380\\d{9}");
        Pattern ru = Pattern.compile("\\+7\\d{10}");

        Matcher mUA = ua.matcher(phone);
        Matcher mRU = ru.matcher(phone);

        if ((mUA.matches()) || (mRU.matches())) {
            return true;
        }
        return false;
    }

    private String checkSmsPassword(String phone, String smsPassword) throws ServerException {
        URL url;
        HttpURLConnection connection = null;

        try {
            //Create connection
            url = new URL("https://sms-inner.siteheart.com:4444/api/otp_verify_api.cgi");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            StringBuilder sb = new StringBuilder();

            sb.append("<?xml version='1.0' encoding='UTF-8'?>");
            sb.append("<request>");
            sb.append("<merchant>");
            sb.append("<id>").append(Constant.OTP_SMS_SERVICE_ID).append("</id>"); //428
            sb.append("<password>").append(Constant.OTP_SMS_SERVICE_PASSWORD).append("</password>"); //AEXAENAHN8AIFAIZEES9
            sb.append("<version>1.0</version>");
            sb.append("</merchant>");
            sb.append("<otp_verify>");
            sb.append("<phone>").append(phone).append("</phone>");
            sb.append("<password>").append(smsPassword).append("</password>");
            sb.append("</otp_verify>");
            sb.append("</request>");

            String input = sb.toString();

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(input.getBytes());
            wr.flush();
            wr.close();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new ServerException("Failed : HTTP error code : "
                        + connection.getResponseCode(), "-123");
            }

            //Get Response	
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {
            //Сбой соединения
            throw new ServerException(Response.AuthorizationError.MESSAGE, Response.AuthorizationError.CODE);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private String sendSmsFromOTP(String phone) throws ServerException {
        URL url;
        HttpURLConnection connection = null;

        try {
            //Create connection
            url = new URL("https://sms-inner.siteheart.com:4444/api/otp_verify_api.cgi");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version='1.0' encoding='UTF-8'?>");
            sb.append("<request>");
            sb.append("<merchant>");
            sb.append("<id>").append(Constant.OTP_SMS_SERVICE_ID).append("</id>"); //428
            sb.append("<password>").append(Constant.OTP_SMS_SERVICE_PASSWORD).append("</password>"); //AEXAENAHN8AIFAIZEES9
            sb.append("<version>1.0</version>");
            sb.append("</merchant>");
            sb.append("<otp_create>");
            sb.append("<phone>").append(phone).append("</phone>");
            sb.append("<sms_template>");
            sb.append("<text val=\"Parol vhodu v PrivatTransfer: \" order=\"1\"/>");
            sb.append("<password type=\"digit\" len=\"4\" order=\"2\"/>");
            sb.append("</sms_template>");
            sb.append("</otp_create>");
            sb.append("</request>");

            //Send request
            String input = sb.toString();

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(input.getBytes());
            wr.flush();
            wr.close();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new ServerException("Failed : HTTP error code : "
                        + connection.getResponseCode(), "-123");
            }

            //Get Response	
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {
            //Сбой соединения
            throw new ServerException(Response.AuthorizationError.MESSAGE, Response.AuthorizationError.CODE);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    public Person getUserDeTails(String phone) throws ServerException{
        if (!checkPhone(phone)) {
            throw new ServerException(Response.IncorrectPhone.MESSAGE, Response.IncorrectPhone.CODE);
        }
        
        EKB ekb = new EKB();
        try {
            return ekb.getPersonDetailsByPhone(phone);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(BusinessServiceImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }
}
