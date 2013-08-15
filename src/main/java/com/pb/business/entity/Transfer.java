/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.entity;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Dmitry
 */
@Entity
@Table(name = "TRANSFER")
public class Transfer {
    @Id
    @NotNull
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATECHANGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datechange;
    @Column(name = "DATECREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreate;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "PHONERECIPIENT")
    private String phonerecipient;
    @Column(name = "PHONESENDER")
    private String phonesender;
    @Column(name = "RECIPIENT_ID")
    private BigInteger recipientId;
    @Column(name = "SENDER_ID")
    private BigInteger senderId;
    @Column(name = "STATUS_ID")
    private BigInteger statusId;
    @Column(name = "TYPEPROCESS")
    private Short typeprocess;

    public Transfer() {
    }

    public Transfer(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatechange() {
        return datechange;
    }

    public void setDatechange(Date datechange) {
        this.datechange = datechange;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhonerecipient() {
        return phonerecipient;
    }

    public void setPhonerecipient(String phonerecipient) {
        this.phonerecipient = phonerecipient;
    }

    public String getPhonesender() {
        return phonesender;
    }

    public void setPhonesender(String phonesender) {
        this.phonesender = phonesender;
    }

    public BigInteger getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(BigInteger recipientId) {
        this.recipientId = recipientId;
    }

    public BigInteger getSenderId() {
        return senderId;
    }

    public void setSenderId(BigInteger senderId) {
        this.senderId = senderId;
    }

    public BigInteger getStatusId() {
        return statusId;
    }

    public void setStatusId(BigInteger statusId) {
        this.statusId = statusId;
    }

    public Short getTypeprocess() {
        return typeprocess;
    }

    public void setTypeprocess(Short typeprocess) {
        this.typeprocess = typeprocess;
    }    
}
