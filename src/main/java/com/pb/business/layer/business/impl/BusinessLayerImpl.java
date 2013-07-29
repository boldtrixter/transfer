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
import com.pb.business.json.entity.User;
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
    public AuthorizationResponse getSmsPassword(String phone) throws ServerException {
        if (!checkPhone(phone)) {
            throw new ServerException(Response.IncorrectPhone.MESSAGE, Response.IncorrectPhone.CODE);
        } else {
            //Получили ответ от сервера
            String response = checkOTP(phone);
            AuthorizationResponse ar = new AuthorizationResponse();
            
            ar.setRes("0");
            ar.setNote("OK");
            ar.setToken("cool_token");
            return ar;
//            ServerResponse sr = new ServerResponse();
//            sr.setRef(Response.Done.CODE);
//            sr.setNote(Response.Done.MESSAGE);
//            return sr;
            //Дергаем сервис! наверно...
        }
    }

    @Override
    public ServerResponse verifyUser(User user) throws ServerException {
        if ((user.getLogin().equals("")) || (user.getPassword().equals(""))) {
            throw new ServerException(Response.IncorrectID.MESSAGE, Response.IncorrectID.CODE);
        }

        ServerResponse sr = new ServerResponse();
        sr.setRef(Response.Done.CODE);
        sr.setNote(Response.Done.MESSAGE);
        return sr;
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

    private String checkOTP1(/*..Params..*/) {
        try {
            URL url = new URL("http://10.1.108.198:2246/SpiderBR");//OTP service

            String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
                    + "<doc t=\"find\" ds=\"2013070100000\" de=\"2013071700000\"  lsid = \"drivauto\" maxcount=\"-1\""
                    + " passwd=\"deliverytake\" agr=\"DELIVERYAUTO\" sys=\"DLV_TB\" agrsys = \"AVTOBR\">\n"
                    + "<p name= \"TRANSPORTER.LDAP\" value = \"SE301275KAA\"/>\n"
                    + "<a name = \"PRODUCT.MARK\"/>\n"
                    + "<a name = \"PRODUCT.MODEL\"/>\n"
                    + "</doc>";

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

            //String input = "{\"name\":\"Вася Пупкин\",\"scanCode\":\"1111222233334444\",\"coordLatitude\":\"1.1\",\"coordLongitude\":\"2.2\"}";
            String input = s;//b.toString(); //Content
            //String input = "request body";
            OutputStream os = conn.getOutputStream();
            //os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            System.out.println("Output from Server .... \n" + br);



            conn.disconnect();

            //BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            int i = br.toString().length();
            System.out.println("IIIIIIIIIIIIIIIIII");
            System.out.println(i);
            int x = i;

        } catch (Exception e) {
        }
        return null;
    }

    public String checkOTP(String phone) throws ServerException {
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL("http://httpbin.org/post");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            // wr.writeBytes(urlParameters);
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
    
            //e.printStackTrace();
            //return null;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
