/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.tools;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@XmlRootElement(name = "doc")
public class PersonDetails {

    private Person person;

    public Person getPerson() {
        return person;
    }

    @XmlElement(name = "r")
    public void setPerson(Person person) {
        this.person = person;
    }

    public String getFName() {
        return person.getData().getRuFName();
    }

    public String getMName() {
        return person.getData().getRuMName();
    }

    public String getLName() {
        return person.getData().getRuLName();
    }

    public String getINN() {
        return person.getData().getOkro();
    }

    public String getIdEKB() {
        return person.getData().getId();
    }

    public static class Person {

        private Data data;

        public Data getData() {
            return data;
        }

        @XmlElement(name = "INF_NEW")
        public void setData(Data infNew) {
            this.data = infNew;
        }
    }

    public static class Data {

        private String id;
        private String ocpo;
        private String ruLName;
        private String ruFName;
        private String ruMName;

        public String getId() {
            return id;
        }

        @XmlAttribute(name = "Id")
        public void setId(String id) {
            this.id = id;
        }

        public String getOkro() {
            return ocpo;
        }

        @XmlAttribute(name = "OKPO")
        public void setOkro(String ocro) {
            this.ocpo = ocro;
        }

        public String getRuLName() {
            return ruLName;
        }

        @XmlAttribute
        public void setRuLName(String ruLName) {
            this.ruLName = ruLName;
        }

        public String getRuFName() {
            return ruFName;
        }

        @XmlAttribute
        public void setRuFName(String ruFName) {
            this.ruFName = ruFName;
        }

        public String getRuMName() {
            return ruMName;
        }

        @XmlAttribute
        public void setRuMName(String ruMName) {
            this.ruMName = ruMName;
        }
    }
}
