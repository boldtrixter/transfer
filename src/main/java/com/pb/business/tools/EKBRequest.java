/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.tools;


import com.pb.business.message.ServerException;
import com.pb.transfer.Person;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author user
 */
public class EKBRequest {

    private Doc d;
    @Autowired
    private Sessions session;

    public Sessions getSession() {
        return session;
    }

    public void setSession(Sessions session) {
        this.session = session;
    }

    public EKBRequest() {
        d = new Doc();
    }

    public Doc getD() {
        return d;
    }

    public void setCountry(String country) {
        d.getR().setCntr(country);
    }

    public void setSid(String sid) {
        d.getR().setSid(sid);
    }

    public void setPhone(String phone) {
        d.getR().getI().setAllPhone(phone);
    }

     public Person getPersonDetailsByPhone(String phone) throws Exception {
        
        Person p = new Person();
        PersonDetails pd = sendEKBrequest(phone);
        
        if(pd.getPerson().getData() == null){
            return null;
        }

        p.setFname(pd.getFName());
        p.setMname(pd.getMName());
        p.setLname(pd.getLName());
        
        p.setPhonenumber(phone);

        p.setIdekb(pd.getIdEKB());
        
        return p;
    }

    private PersonDetails sendEKBrequest(String phone) throws Exception {

        //Sessions session = new Sessions("http://10.1.108.22:8071/ChameleonServer", "UTSM", "EXCL", "GFhJUKIghFgh");
        String s = session.getSession();
        URL url;
        HttpURLConnection connection = null;
        try {
            EKBRequest ekb = new EKBRequest();
            ekb.setCountry("UA");
            ekb.setPhone(phone);
            ekb.setSid(s);

            JAXBContext jaxbContext = JAXBContext.newInstance(EKBRequest.Doc.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(ekb.getD(), sw);

            url = new URL("http://10.1.195.39:3201/CIS4");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            String input = sw.toString();

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
            JAXBContext jaxbContext1 = JAXBContext.newInstance(com.pb.business.tools.PersonDetails.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext1.createUnmarshaller();
            PersonDetails person = (com.pb.business.tools.PersonDetails) jaxbUnmarshaller.unmarshal(is);
            return person;

        } catch (Exception e) {
            throw new Exception();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    //*************************************
    @XmlType
    @XmlRootElement(name = "doc")
    public static class Doc {

        private R r;

        public Doc() {
            r = new R();
        }

        public R getR() {
            return r;
        }

        @XmlElement
        public void setR(R r) {
            this.r = r;
        }
    }
    //*************************************

    public static class R {

        private String cntr;
        private String key;
        private String sid;
        private String t;
        private I i;
        private O o;

        public R() {
            this.cntr = new String();
            this.key = new String("0");
            this.sid = new String();
            this.t = new String("INF_NEW");
            i = new I();
            o = new O();
        }

        public I getI() {
            return i;
        }

        @XmlElement
        public void setI(I i) {
            this.i = i;
        }

        public String getCntr() {
            return cntr;
        }

        @XmlAttribute
        public void setCntr(String cntr) {
            this.cntr = cntr;
        }

        public String getKey() {
            return key;
        }

        @XmlAttribute
        public void setKey(String key) {
            this.key = key;
        }

        public String getSid() {
            return sid;
        }

        @XmlAttribute
        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getT() {
            return t;
        }

        @XmlAttribute
        public void setT(String t) {
            this.t = t;
        }

        public O getO() {
            return o;
        }

        @XmlElement
        public void setO(O o) {
            this.o = o;
        }
    }
    //*************************************

    public static class I {

        private String allPhone;

        public String getAllPhone() {
            return allPhone;
        }

        @XmlAttribute(name = "AllPhone")
        public void setAllPhone(String allPhone) {
            this.allPhone = allPhone;
        }

        public I() {
            this.allPhone = new String();
        }
    }
    //*************************************

    public static class O {

        private String id;
        private String okpo;
        private String ruLName;
        private String ruFName;
        private String ruMName;

        public O() {
            this.id = new String();
            this.okpo = new String();
            this.ruLName = new String();
            this.ruFName = new String();
            this.ruMName = new String();
        }

        public String getId() {
            return id;
        }

        @XmlElement(name = "OKPO")
        public void setId(String id) {
            this.id = id;
        }

        public String getOkpo() {
            return okpo;
        }

        @XmlElement(name = "Id")
        public void setOkpo(String okpo) {
            this.okpo = okpo;
        }

        public String getRuLName() {
            return ruLName;
        }

        @XmlElement
        public void setRuLName(String ruLName) {
            this.ruLName = ruLName;
        }

        public String getRuFName() {
            return ruFName;
        }

        @XmlElement
        public void setRuFName(String ruFName) {
            this.ruFName = ruFName;
        }

        public String getRuMName() {
            return ruMName;
        }

        @XmlElement
        public void setRuMName(String ruMName) {
            this.ruMName = ruMName;
        }
    }
    
}
