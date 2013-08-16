///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.pb.business.entity;
//
//import com.pb.tEntity.Avtransfer;
//import java.io.Serializable;
//import java.math.BigInteger;
//import java.util.Date;
//import java.util.List;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import org.hibernate.annotations.GenericGenerator;
//
//
///**
// *
// * @author Dmitry
// */
//
//@Entity
//@Table(name = "PERSON")
//public class Person1 {
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "Id")
//    private Integer id;
//    @Size(max = 50)
//    @Column(name = "FName")
//    private String fName;
//    @Size(max = 50)
//    @Column(name = "LName")
//    private String lName;
//    @Size(max = 50)
//    @Column(name = "MName")
//    private String mName;
//    @Size(max = 50)
//    @Column(name = "PhoneNumber")
//    private String phoneNumber;
//    @Size(max = 50)
//    @Column(name = "IdEKB")
//    private String idEKB;
//    @OneToMany(mappedBy = "personId")
//    private List<Avtransfer> avtransferList;
//    @OneToMany(mappedBy = "senderId")
//    private List<Transfer1> transfer1List;
//    @OneToMany(mappedBy = "recipientId")
//    private List<Transfer1> transfer1List1;
//    @OneToMany(mappedBy = "carrierId")
//    private List<Transfer1> transfer1List2;
//    @OneToMany(mappedBy = "personId")
//    private List<Token1> token1List;
//    
//    @Id
//    @NotNull
//    @GeneratedValue(generator="increment")
//    @GenericGenerator(name="increment", strategy = "increment")
//    @Column(name = "ID")
//    private Long id;
//    @Column(name = "DATECHANGE")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date datechange;
//    @Column(name = "DATECREATE")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date datecreate;
//    @Column(name = "FIO")
//    private String fio;
//    @Column(name = "IDEKB")
//    private String idekb;
//    @Column(name = "INN")
//    private BigInteger inn;
//    @Column(name = "PASS")
//    private String pass;
//    @Column(name = "PHONENUMBER")
//    private String phonenumber;
//    @Column(name = "TABNUM")
//    private BigInteger tabnum;
//    @Column(name = "TYPE")
//    private Short type;
//
//    public Person1() {
//    }
//
//    public Person1(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Date getDatechange() {
//        return datechange;
//    }
//
//    public void setDatechange(Date datechange) {
//        this.datechange = datechange;
//    }
//
//    public Date getDatecreate() {
//        return datecreate;
//    }
//
//    public void setDatecreate(Date datecreate) {
//        this.datecreate = datecreate;
//    }
//
//    public String getFio() {
//        return fio;
//    }
//
//    public void setFio(String fio) {
//        this.fio = fio;
//    }
//
//    public String getIdekb() {
//        return idekb;
//    }
//
//    public void setIdekb(String idekb) {
//        this.idekb = idekb;
//    }
//
//    public BigInteger getInn() {
//        return inn;
//    }
//
//    public void setInn(BigInteger inn) {
//        this.inn = inn;
//    }
//
//    public String getPass() {
//        return pass;
//    }
//
//    public void setPass(String pass) {
//        this.pass = pass;
//    }
//
//    public String getPhonenumber() {
//        return phonenumber;
//    }
//
//    public void setPhonenumber(String phonenumber) {
//        this.phonenumber = phonenumber;
//    }
//
//    public BigInteger getTabnum() {
//        return tabnum;
//    }
//
//    public void setTabnum(BigInteger tabnum) {
//        this.tabnum = tabnum;
//    }
//
//    public Short getType() {
//        return type;
//    }
//
//    public void setType(Short type) {
//        this.type = type;
//    }
//
//    public Person1(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getFName() {
//        return fName;
//    }
//
//    public void setFName(String fName) {
//        this.fName = fName;
//    }
//
//    public String getLName() {
//        return lName;
//    }
//
//    public void setLName(String lName) {
//        this.lName = lName;
//    }
//
//    public String getMName() {
//        return mName;
//    }
//
//    public void setMName(String mName) {
//        this.mName = mName;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getIdEKB() {
//        return idEKB;
//    }
//
//    public void setIdEKB(String idEKB) {
//        this.idEKB = idEKB;
//    }
//
//    public List<Avtransfer> getAvtransferList() {
//        return avtransferList;
//    }
//
//    public void setAvtransferList(List<Avtransfer> avtransferList) {
//        this.avtransferList = avtransferList;
//    }
//
//    public List<Transfer1> getTransfer1List() {
//        return transfer1List;
//    }
//
//    public void setTransfer1List(List<Transfer1> transfer1List) {
//        this.transfer1List = transfer1List;
//    }
//
//    public List<Transfer1> getTransfer1List1() {
//        return transfer1List1;
//    }
//
//    public void setTransfer1List1(List<Transfer1> transfer1List1) {
//        this.transfer1List1 = transfer1List1;
//    }
//
//    public List<Transfer1> getTransfer1List2() {
//        return transfer1List2;
//    }
//
//    public void setTransfer1List2(List<Transfer1> transfer1List2) {
//        this.transfer1List2 = transfer1List2;
//    }
//
//    public List<Token1> getToken1List() {
//        return token1List;
//    }
//
//    public void setToken1List(List<Token1> token1List) {
//        this.token1List = token1List;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Person1)) {
//            return false;
//        }
//        Person1 other = (Person1) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.pb.business.entity.Person1[ id=" + id + " ]";
//    }
//    
//}
