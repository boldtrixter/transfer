/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.service;


import com.pb.business.message.Response;
import com.pb.business.message.ServerException;
import com.pb.business.message.AuthorizationResponse;
import com.pb.business.json.entity.TransferDetails;
import com.pb.business.message.ServerResponse;
import com.pb.business.json.entity.UserData;
import com.pb.business.dao.BusinessDAO;
import com.pb.business.tools.Constant;
import com.pb.business.tools.EKBRequest;
import com.pb.transfer.Avtransf;
import com.pb.transfer.Coordinates;
import com.pb.transfer.Person;
import com.pb.transfer.Product;
import com.pb.transfer.Status;
import com.pb.transfer.Token;
import com.pb.transfer.Transfer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
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
    @Autowired
    EKBRequest ekb;

//    @Override
//    public List<Transfertable1> getAllMakers(String userToken) throws ServerException {
//        if (checkToken(userToken) != 0) {
//            throw new ServerException("Иди авторизируйся!", "-112");
//        }
//        checkToken(userToken);
//        return dao.getAllMakers();
//    }

    @Override
    public String hiberTest() {
        
        return dao.hiberTest();
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public ServerResponse addTransfer(TransferDetails transferDetails, String ip) throws Exception {


//        if (!checkIp(ip)) {
//            throw new ServerException(Response.AccessDenied.MESSAGE + " c IP: " + ip, Response.AccessDenied.CODE);
//        }
//        
//        //        проверка на существование и время жизни токена
//        if (!checkToken(transferDetails.getToken())) {
//            throw new ServerException("Token1 is dead, you need autorization", "-1");
//        }
        

        if ((!checkPhone(transferDetails.getSenderPhone())) || transferDetails.getSenderPhone().equals("")) {
            throw new ServerException(Response.IncorrectSenderPhone.MESSAGE, Response.IncorrectSenderPhone.CODE);
        }

        if (!checkPhone(transferDetails.getRecipientPhone()) || transferDetails.getRecipientPhone().equals("")) {
            throw new ServerException(Response.IncorrectReceiverPhone.MESSAGE, Response.IncorrectReceiverPhone.CODE);
        }

        if (!checkPhone(transferDetails.getCarrierPhone()) || transferDetails.getCarrierPhone().equals("")) {
            throw new ServerException(Response.IncorrectCarrierPhone.MESSAGE, Response.IncorrectCarrierPhone.CODE);
        }

//        if (!checkDateTime(transferDetails.getDateTime()) || transferDetails.getDateTime().equals("")) {
//            throw new ServerException(Response.IncorrectDate.MESSAGE, Response.IncorrectDate.CODE);
//        }

        if (transferDetails.getName().equals("")) {
            throw new ServerException(Response.IncorrectProductName.MESSAGE, Response.IncorrectProductName.CODE);
        }

        if ((!transferDetails.getAcceptanceType().equals("SMS") && !transferDetails.getAcceptanceType().equals("PHOTO")) || transferDetails.getAcceptanceType().equals("")) {
            throw new ServerException(Response.IncorrectAcceptanceType.MESSAGE, Response.IncorrectAcceptanceType.CODE);
        }

        if (transferDetails.getCallbackLink().equals("")) {
            throw new ServerException(Response.IncorrectCallbackLink.MESSAGE, Response.IncorrectCallbackLink.CODE);
        }

        //!!!!ДАННЫЕ ПРОВЕРЕНЫ 

        // ПРОВЕРЯЕМ НА СОВПАДЕНГИЯ С БД!!!!

        Person sender = dao.getPersonByPhone(transferDetails.getSenderPhone());

        if (sender == null) {
            sender = ekb.getPersonDetailsByPhone(transferDetails.getSenderPhone());
        }

        Person recipient = dao.getPersonByPhone(transferDetails.getRecipientPhone());

        if (recipient == null) {
            recipient = ekb.getPersonDetailsByPhone(transferDetails.getRecipientPhone());
        }

        //Person carrier = dao.getPersonByPhone(transferDetails.getCarrierPhone());

//        if (carrier == null) {
//            carrier = ekb.getPersonDetailsByPhone(transferDetails.getCarrierPhone());
//        }
        
        Status s = dao.getStatusByTitle("NEW");
                
        Product p = new Product();
        p.setTitle(transferDetails.getName());
        p.setDescription(transferDetails.getDescription());
        p.setPhoto(transferDetails.getPhoto());
        
         
        
        Coordinates coors = new Coordinates();
        coors.setLatitude(transferDetails.getCoords().getLatitude());
        coors.setLongtitude(transferDetails.getCoords().getLongitude());
        coors.setVariation(200);
        
        
        
        Transfer transfer = new Transfer();
        transfer.setSenderid(sender);
        transfer.setRecipientid(recipient);
        //transfer.setCarrierid(carrier);
        transfer.setProductid(p);
        transfer.setStatusid(s);
        transfer.setCoordsid(coors);
        transfer.setDatecreate(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime())));
        //new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(Calendar.getInstance().getTime())
        transfer.setAcceptancetype(transferDetails.getAcceptanceType());
        transfer.setCallbacklink(transferDetails.getCallbackLink());

        dao.addTransfer(transfer);
        
//        String phones = "1#2#3#4#5";
//        
//        StringTokenizer tokenizer = new StringTokenizer(phones,"#",false);
//       List<String> l = new LinkedList<String>();
//        while(tokenizer.hasMoreElements()){
//            l.add((String) tokenizer.nextElement());
//        }
//        
//        for(int i=0; i<l.size(); i++){
//            
//        }
        //Заполняем трансфер, затем доступный трансфер
        //Ложим в бд


        //return dao.addTransfer(data);

        ServerResponse sr = new ServerResponse();
        sr.setNote("Прошли валидацию");
        sr.setRef("0");
        return sr;


        //checkToken(data.getToken());
        //Проверка номера телефона отправителя
//        if (!checkPhone(data.getSender().getPhoneNumber())) {
//            throw new ServerException(Response.IncorrectSenderPhone.MESSAGE, Response.IncorrectSenderPhone.CODE);
//        }
//
//        //Проверка номера телефона получателя
//        if (!checkPhone(data.getReceiver().getPhoneNumber())) {
//            throw new ServerException(Response.IncorrectReceiverPhone.MESSAGE, Response.IncorrectReceiverPhone.CODE);
//        }
//
//        //Имя товара
//        if (data.getName().equals("")) {
//            throw new ServerException(Response.IncorrectProductName.MESSAGE, Response.IncorrectProductName.CODE);
//        }
//
//        if (data.getScanCode().equals("") && data.getStandNumber().equals("")) {
//            throw new ServerException("Пустые штрихкод/номер авто", "-99");
//        }
//
//        if (!data.getScanCode().equals("")) {
//            if (!data.getStandNumber().equals("")) {
//                throw new ServerException("Нужен либо штрихкод/либо номер кузова", "-99");
//            }
//        }

        //  EKBRequest eKBRequest = new EKBRequest();
        // try {
        //EKB ekb = new EKB();
        // Sessions session = new Sessions("http://10.1.108.22:8071/ChameleonServer", "UTSM", "EXCL", "GFhJUKIghFgh");
        //String s = "";

        // s = session.getSession();
        //Person p = ekb.getPersonDetailsByPhone(data.getReceiver().getPhoneNumber());
        //p = ekb.getPersonDetailsByPhone(data.getSender().getPhoneNumber());
        //p = eKBRequest.getPersonDetailsByPhone(data.getSender().getPhoneNumber());
//        } catch (Exception ex) {
//            java.util.logging.Logger.getLogger(BusinessServiceImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        EKBRequest ekb = new EKBRequest();
//        ekb.setCountry("UA");
//        ekb.setPhone("+380934682670");
//        ekb.setSid("130812PL0vj12ic3oaaa");
//        try {
////            com.pb.business.tools.PersonDetails sender = ekb.getResponse();
//        } catch (Exception ex) {
//            java.util.logging.Logger.getLogger(BusinessServiceImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        try {
//            Person1 receiver = ekb.getPersonDetailsByPhone(data.getReceiver().getPhoneNumber());
//        } catch (Exception ex) {
//            java.util.logging.Logger.getLogger(BusinessServiceImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        
//        Person1 sender = dao.getPersonByPhone(data.getSender().getPhoneNumber());
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
        current.add(Calendar.MINUTE, -Constant.TOKEN_LIFETIME);
        //TokenEntity te = lte.get(0);
        //System.out.println((new SimpleDateFormat("dd.MM.yyyy HH-mm-ss")).format(current.getTime()));
        Logger.getLogger(BusinessServiceImpl.class.getName()).log(Level.INFO, (new SimpleDateFormat("dd.MM.yyyy HH-mm-ss")).format(current.getTime()) + "CURRENT!!!");
        Logger.getLogger(BusinessServiceImpl.class.getName()).log(Level.INFO, (new SimpleDateFormat("dd.MM.yyyy HH-mm-ss")).format(t.getDatecreate()) + "TOKEN!!!");
        //System.out.println((new SimpleDateFormat("dd.MM.yyyy HH-mm-ss")).format(t.getDatechange()));

        if (t.getDatecreate().after(current.getTime())) {
            t.setDatecreate(Calendar.getInstance().getTime());
            Logger.getLogger(BusinessServiceImpl.class.getName()).log(Level.INFO, (new SimpleDateFormat("dd.MM.yyyy HH-mm-ss")).format(t.getDatecreate()) + " TOKEN CHANGED!!!");

            //проапдейтить время жизни токена в базу
            dao.updateToken(t);

            return true;
        }
        //УДАЛИТЬ ИЗ БД т.к. умер. В будущем планируеться добавить сервис удаляющий токены
        dao.deleteToken(t);
        //return true;

        throw new ServerException(Response.TokenLifetimeEnd.MESSAGE, Response.TokenLifetimeEnd.CODE);
        //return true;
    }

    private boolean checkIp(String ip) {
        return dao.checkIp(ip);
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

    private boolean checkDateTime(String dateTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy hh:mm");
        formatter.setLenient(false);

        try {
            formatter.parse(dateTime);
        } catch (Exception e) {
            return false;
        }
        return true;
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

}
