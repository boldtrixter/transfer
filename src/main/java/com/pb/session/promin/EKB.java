/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.session.promin;

import Entity.Person;
import com.pb.business.exception.ServerException;
import com.pb.cis.toolsn.requestObjects.ClEtc.ICLEtc;
import com.pb.cis.toolsn.requestObjects.ClEtc.P_CLIENTATTRSRequest;
import com.pb.cis.toolsn.requestObjects.FactoryRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.inject.Singleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author user
 */
public class EKB {

//    @Autowired
//    @Singleton
//    private Sessions session;
    public Person getPersonDetailsByPhone(String phone) throws Exception {

        String pd = sendEKBrequest(phone);
        
        // ОСТОРОЖНО КАСТЫЛЬ!
        // СРАЗУЖЕ КАК ТОЛЬКО ВОЗМОЖНО ПЕРЕДЕЛАТЬ!!! 
        int inns = pd.indexOf("OKPO=\"") + 6;
        int inne = pd.indexOf("\" ruLName");

        String inn = pd.substring(inns, inne);

        int idEKBs = pd.indexOf("Id=\"") + 4;
        int idEKBe = pd.indexOf("\" OKPO");

        String idEKB = pd.substring(idEKBs, idEKBe);

        int fnames = pd.indexOf("ruFName=\"") + 9;
        int fnamee = pd.indexOf("\" ruMName");
        String fname = pd.substring(fnames, fnamee);

        int snames = pd.indexOf("ruMName=\"") + 9;
        int snamee = pd.indexOf("\"></INF_NEW>");
        String mname = pd.substring(snames, snamee);
        
        int mnames = pd.indexOf("ruLName=\"") + 9;
        int mnamee = pd.indexOf("\" ruFName");
        String lname = pd.substring(mnames, mnamee);

        String fio = lname + " " + fname + " " + mname;
        
        Person p = new Person();
        p.setFio(fio);
        p.setIdekb(idEKB);
        p.setInn(new BigInteger(inn));
        p.setPhonenumber(phone);
        
        return p;
    }

    private String sendEKBrequest(String phone) throws Exception {


        Sessions session = new Sessions("http://10.1.108.22:8071/ChameleonServer", "UTSM", "EXCL", "GFhJUKIghFgh");
        String s = session.getSession();
        //130802PL0sevvbccrlaa

        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL("http://10.1.195.39:3201/CIS4");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml");
            //connection.setRequestProperty("encoding", "UTF-8");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            StringBuilder sb = new StringBuilder();

            sb.append("<?xml version='1.0' encoding='UTF-8' standalone='no'?>");
            sb.append("<doc>");
            sb.append("<r cntr='UA' key='0' sid='").append(s).append("' t='INF_NEW'>");
            sb.append("<i AllPhone='").append(phone).append("' ></i>");
            sb.append("<o>");
            sb.append("<Id></Id>");
            sb.append("<OKPO></OKPO>");
            sb.append("<ruLName></ruLName>");
            sb.append("<ruFName></ruFName>");
            sb.append("<ruMName></ruMName>");
//            sb.append("<enLName></enLName>");
//            sb.append("<enFName></enFName>");
//            sb.append("<enMName></enMName>");
            sb.append("</o>");
            //  sb.append("<t ></t>");
            sb.append("</r>");
            sb.append("</doc>");

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


            Logger.getLogger(EKB.class).log(Level.INFO, response.toString());


            rd.close();



            return response.toString();

            //return null;

        } catch (Exception e) {
            //Сбой соединения
            return e.toString();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }
}
