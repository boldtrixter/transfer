///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.pb.business.entity;
//
//import java.io.Serializable;
//import java.math.BigInteger;
//import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//import org.hibernate.annotations.GenericGenerator;
//
///**
// *
// * @author Dmitry
// */
//
//public class Transfertable1 {
//    @Id
//    @NotNull
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name = "increment", strategy = "increment")
//    @Column(name = "ID")
//    private Long id;
//    @Column(name = "CURRENCY")
//    private String currency;
//    @Column(name = "DATECHANGE")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date datechange;
//    @Column(name = "DATECREATE")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date datecreate;
//    @Column(name = "PRICE")
//    private Double price;
//    @Column(name = "PRICECURRENCY")
//    private Double pricecurrency;
//    @Column(name = "PRODUCT_ID")
//    private BigInteger productId;
//    @Column(name = "PRODUCTSCANSERIALNUMBER_ID")
//    private BigInteger productscanserialnumberId;
//    @Column(name = "QTY")
//    private Double qty;
//    @Column(name = "TOTAL")
//    private Double total;
//    @Column(name = "TOTALCURRENCY")
//    private Double totalcurrency;
//    @Column(name = "TRANSFER_ID")
//    private BigInteger transferId;
//    @Column(name = "URLPHOTO")
//    private String urlphoto;
//
//    public Transfertable1() {
//    }
//
//    public Transfertable1(Long id) {
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
//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
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
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public Double getPricecurrency() {
//        return pricecurrency;
//    }
//
//    public void setPricecurrency(Double pricecurrency) {
//        this.pricecurrency = pricecurrency;
//    }
//
//    public BigInteger getProductId() {
//        return productId;
//    }
//
//    public void setProductId(BigInteger productId) {
//        this.productId = productId;
//    }
//
//    public BigInteger getProductscanserialnumberId() {
//        return productscanserialnumberId;
//    }
//
//    public void setProductscanserialnumberId(BigInteger productscanserialnumberId) {
//        this.productscanserialnumberId = productscanserialnumberId;
//    }
//
//    public Double getQty() {
//        return qty;
//    }
//
//    public void setQty(Double qty) {
//        this.qty = qty;
//    }
//
//    public Double getTotal() {
//        return total;
//    }
//
//    public void setTotal(Double total) {
//        this.total = total;
//    }
//
//    public Double getTotalcurrency() {
//        return totalcurrency;
//    }
//
//    public void setTotalcurrency(Double totalcurrency) {
//        this.totalcurrency = totalcurrency;
//    }
//
//    public BigInteger getTransferId() {
//        return transferId;
//    }
//
//    public void setTransferId(BigInteger transferId) {
//        this.transferId = transferId;
//    }
//
//    public String getUrlphoto() {
//        return urlphoto;
//    }
//
//    public void setUrlphoto(String urlphoto) {
//        this.urlphoto = urlphoto;
//    }
//    
//}
