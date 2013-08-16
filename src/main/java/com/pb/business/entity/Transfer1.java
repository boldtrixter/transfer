///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.pb.business.entity;
//
//import com.pb.tEntity.Avtransfer;
//import com.pb.tEntity.Coordinates;
//import java.math.BigInteger;
//import java.util.Date;
//import java.util.List;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import org.hibernate.annotations.GenericGenerator;
//
///**
// *
// * @author Dmitry
// */
//@Entity
//@Table(name = "TRANSFER")
//public class Transfer1 {
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "Id")
//    private Integer id;
//    @Column(name = "DateCreate")
//    @Temporal(TemporalType.DATE)
//    private Date dateCreate;
//    @Size(max = 10)
//    @Column(name = "AcceptanType")
//    private String acceptanType;
//    @Size(max = 100)
//    @Column(name = "CallBackLink")
//    private String callBackLink;
//    @OneToMany(mappedBy = "transferId")
//    private List<Avtransfer> avtransferList;
//    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
//    @ManyToOne
//    private Status1 statusId;
//    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
//    @ManyToOne
//    private Product1 productId;
//    @JoinColumn(name = "SenderId", referencedColumnName = "Id")
//    @ManyToOne
//    private Person1 senderId;
//    @JoinColumn(name = "RecipientId", referencedColumnName = "Id")
//    @ManyToOne
//    private Person1 recipientId;
//    @JoinColumn(name = "CarrierId", referencedColumnName = "Id")
//    @ManyToOne
//    private Person1 carrierId;
//    @JoinColumn(name = "CoordsId", referencedColumnName = "Id")
//    @ManyToOne
//    private Coordinates coordsId;
//    @Id
//    @NotNull
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name = "increment", strategy = "increment")
//    @Column(name = "ID")
//    private Long id;
//    @Column(name = "DATECHANGE")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date datechange;
//    @Column(name = "DATECREATE")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date datecreate;
//    @Column(name = "NOTE")
//    private String note;
//    @Column(name = "PHONERECIPIENT")
//    private String phonerecipient;
//    @Column(name = "PHONESENDER")
//    private String phonesender;
//    @Column(name = "RECIPIENT_ID")
//    private BigInteger recipientId;
//    @Column(name = "SENDER_ID")
//    private BigInteger senderId;
//    @Column(name = "STATUS_ID")
//    private BigInteger statusId;
//    @Column(name = "TYPEPROCESS")
//    private Short typeprocess;
//
//    public Transfer1() {
//    }
//
//    public Transfer1(Long id) {
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
//    public String getNote() {
//        return note;
//    }
//
//    public void setNote(String note) {
//        this.note = note;
//    }
//
//    public String getPhonerecipient() {
//        return phonerecipient;
//    }
//
//    public void setPhonerecipient(String phonerecipient) {
//        this.phonerecipient = phonerecipient;
//    }
//
//    public String getPhonesender() {
//        return phonesender;
//    }
//
//    public void setPhonesender(String phonesender) {
//        this.phonesender = phonesender;
//    }
//
//    public BigInteger getRecipientId() {
//        return recipientId;
//    }
//
//    public void setRecipientId(BigInteger recipientId) {
//        this.recipientId = recipientId;
//    }
//
//    public BigInteger getSenderId() {
//        return senderId;
//    }
//
//    public void setSenderId(BigInteger senderId) {
//        this.senderId = senderId;
//    }
//
//    public BigInteger getStatusId() {
//        return statusId;
//    }
//
//    public void setStatusId(BigInteger statusId) {
//        this.statusId = statusId;
//    }
//
//    public Short getTypeprocess() {
//        return typeprocess;
//    }
//
//    public void setTypeprocess(Short typeprocess) {
//        this.typeprocess = typeprocess;
//    }    
//
//    public Transfer1(Integer id) {
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
//    public Date getDateCreate() {
//        return dateCreate;
//    }
//
//    public void setDateCreate(Date dateCreate) {
//        this.dateCreate = dateCreate;
//    }
//
//    public String getAcceptanType() {
//        return acceptanType;
//    }
//
//    public void setAcceptanType(String acceptanType) {
//        this.acceptanType = acceptanType;
//    }
//
//    public String getCallBackLink() {
//        return callBackLink;
//    }
//
//    public void setCallBackLink(String callBackLink) {
//        this.callBackLink = callBackLink;
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
//    public Status1 getStatusId() {
//        return statusId;
//    }
//
//    public void setStatusId(Status1 statusId) {
//        this.statusId = statusId;
//    }
//
//    public Product1 getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Product1 productId) {
//        this.productId = productId;
//    }
//
//    public Person1 getSenderId() {
//        return senderId;
//    }
//
//    public void setSenderId(Person1 senderId) {
//        this.senderId = senderId;
//    }
//
//    public Person1 getRecipientId() {
//        return recipientId;
//    }
//
//    public void setRecipientId(Person1 recipientId) {
//        this.recipientId = recipientId;
//    }
//
//    public Person1 getCarrierId() {
//        return carrierId;
//    }
//
//    public void setCarrierId(Person1 carrierId) {
//        this.carrierId = carrierId;
//    }
//
//    public Coordinates getCoordsId() {
//        return coordsId;
//    }
//
//    public void setCoordsId(Coordinates coordsId) {
//        this.coordsId = coordsId;
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
//        if (!(object instanceof Transfer1)) {
//            return false;
//        }
//        Transfer1 other = (Transfer1) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.pb.business.entity.Transfer1[ id=" + id + " ]";
//    }
//}
