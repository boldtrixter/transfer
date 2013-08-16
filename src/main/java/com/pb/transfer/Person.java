/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.transfer;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author user
 */
@Entity
@Table(name = "PERSON")
public class Person {
    @Id
    @NotNull
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FNAME")
    private String fname;
    @Column(name = "LNAME")
    private String lname;
    @Column(name = "MNAME")
    private String mname;
    @Column(name = "PHONENUMBER")
    private String phonenumber;
    @Column(name = "IDEKB")
    private String idekb;
    @OneToMany(mappedBy = "personId")
    private List<Token> token1List;
    @OneToMany(mappedBy = "senderId")
    private List<Transfer> senderList;
    @OneToMany(mappedBy = "recipientId")
    private List<Transfer> recipientList;
    @OneToMany(mappedBy = "carrierId")
    private List<Transfer> carrierList;
    @OneToMany(mappedBy = "personId")
    private List<Avtransf> avtransferList;
    

    public List<Avtransf> getAvtransferList() {
        return avtransferList;
    }

    public void setAvtransferList(List<Avtransf> avtransferList) {
        this.avtransferList = avtransferList;
    }

    public List<Transfer> getSenderList() {
        return senderList;
    }

    public void setSenderList(List<Transfer> senderList) {
        this.senderList = senderList;
    }

    public List<Transfer> getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(List<Transfer> recipientList) {
        this.recipientList = recipientList;
    }

    public List<Transfer> getCarrierList() {
        return carrierList;
    }

    public void setCarrierList(List<Transfer> carrierList) {
        this.carrierList = carrierList;
    }

    public List<Token> getToken1List() {
        return token1List;
    }

    public void setToken1List(List<Token> token1List) {
        this.token1List = token1List;
    }
    
    public Person() {
    }

    public Person(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getIdekb() {
        return idekb;
    }

    public void setIdekb(String idekb) {
        this.idekb = idekb;
    }
    
}
