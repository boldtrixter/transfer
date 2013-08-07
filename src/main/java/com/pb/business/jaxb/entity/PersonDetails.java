/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.jaxb.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author user
 */

@XmlType
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="doc")
public class PersonDetails {
    
    @XmlElement(name = "Id")
    private String Id;
    @XmlElement(name = "OKPO")
    private String OKPO;
    @XmlElement(name = "ruFName")
    private String ruFName;
    @XmlElement(name = "ruMName")
    private String ruMName;
    @XmlElement(name = "ruLName")
    private String ruLName;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getOKPO() {
        return OKPO;
    }

    public void setOKPO(String OKPO) {
        this.OKPO = OKPO;
    }

    public String getRuFName() {
        return ruFName;
    }

    public void setRuFName(String ruFName) {
        this.ruFName = ruFName;
    }

    public String getRuMName() {
        return ruMName;
    }

    public void setRuMName(String ruMName) {
        this.ruMName = ruMName;
    }

    public String getRuLName() {
        return ruLName;
    }

    public void setRuLName(String ruLName) {
        this.ruLName = ruLName;
    }
    
    public void doSmth(){

    }
       
}
