///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.pb.business.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlRootElement;
//import org.hibernate.annotations.GenericGenerator;
//
///**
// *
// * @author Dmitry
// */
//
//@Entity
//@Table(name = "TOKEN")
//public class Token1 {
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "Id")
//    private Integer id;
//    @Size(max = 50)
//    @Column(name = "Token")
//    private String token;
//    @Column(name = "DateCreate")
//    @Temporal(TemporalType.DATE)
//    private Date dateCreate;
//    @JoinColumn(name = "PersonId", referencedColumnName = "Id")
//    @ManyToOne
//    private Person1 personId;
//    @Id
//    @NotNull
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name = "increment", strategy = "increment")
//    @Column(name = "ID")
//    private Long id;
//    @Column(name = "DATECHANGE")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date datechange;
//    @Column(name = "STATUS")
//    private Short status;
//    @Column(name = "TOKEN")
//    private String token;
//
//    public Token1() {
//    }
//
//    public Token1(Long id) {
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
//    public Short getStatus() {
//        return status;
//    }
//
//    public void setStatus(Short status) {
//        this.status = status;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public Token1(Integer id) {
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
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public Date getDateCreate() {
//        return dateCreate;
//    }
//
//    public void setDateCreate(Date dateCreate) {
//        this.dateCreate = dateCreate;
//    }
//
//    public Person1 getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(Person1 personId) {
//        this.personId = personId;
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
//        if (!(object instanceof Token1)) {
//            return false;
//        }
//        Token1 other = (Token1) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.pb.business.entity.Token1[ id=" + id + " ]";
//    }
//    
//}
