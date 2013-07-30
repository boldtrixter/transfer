/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.layer.business.impl;

import Entity.Transfertable;
import ResponsePattern.Response;
import com.pb.business.exception.ServerException;
import com.pb.business.json.entity.AuthorizationResponse;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import com.pb.business.json.entity.UserData;
import com.pb.business.layer.business.BusinessLayer;
import com.pb.business.layer.dao.DAOLayer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dmitry
 */
@Service
public class BusinessLayerImpl implements BusinessLayer {

    @Autowired
    DAOLayer dao;

    @Override
    public List<Transfertable> getAllMakers() {
        return dao.getAllMakers();
    }

    @Override
    public ServerResponse checkData(Data data) throws ServerException {

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

        return dao.addTransfer(data);


    }

    @Override
    public ServerResponse deleteTransfer(String transferId) throws ServerException {
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
                throw new ServerException("Incorrect time password", "-99");
            }
        }

        String response = checkSmsPassword(userData.getPhone(), userData.getSmsPassword());

        AuthorizationResponse ar = new AuthorizationResponse();
        if (response.indexOf("<package_status>ok</package_status>") != -1) {
            ar.setRes("0");
            ar.setNote("OK");
            ar.setToken("cool_session_token");
            return ar;
        } else {
            ar.setRes("-10");
            ar.setNote("FAILED");
            ar.setToken("not_cool_session_token");
            return ar;
        }

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
            sb.append("<id>").append("428").append("</id>"); //428
            sb.append("<password>").append("AEXAENAHN8AIFAIZEES9").append("</password>"); //AEXAENAHN8AIFAIZEES9
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
            sb.append("<id>").append("428").append("</id>"); //428
            sb.append("<password>").append("AEXAENAHN8AIFAIZEES9").append("</password>"); //AEXAENAHN8AIFAIZEES9
            sb.append("<version>1.0</version>");
            sb.append("</merchant>");
            sb.append("<otp_create>");
            sb.append("<phone>").append(phone).append("</phone>");
            sb.append("<sms_template>");
            sb.append("<text val=\"Parol: \" order=\"1\"/>");
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
