/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Dmitry
 */

public class Transfertable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "DATECHANGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datechange;
    @Column(name = "DATECREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "PRICECURRENCY")
    private Double pricecurrency;
    @Column(name = "PRODUCT_ID")
    private BigInteger productId;
    @Column(name = "PRODUCTSCANSERIALNUMBER_ID")
    private BigInteger productscanserialnumberId;
    @Column(name = "QTY")
    private Double qty;
    @Column(name = "TOTAL")
    private Double total;
    @Column(name = "TOTALCURRENCY")
    private Double totalcurrency;
    @Column(name = "TRANSFER_ID")
    private BigInteger transferId;
    @Column(name = "URLPHOTO")
    private String urlphoto;

    public Transfertable() {
    }

    public Transfertable(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPricecurrency() {
        return pricecurrency;
    }

    public void setPricecurrency(Double pricecurrency) {
        this.pricecurrency = pricecurrency;
    }

    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public BigInteger getProductscanserialnumberId() {
        return productscanserialnumberId;
    }

    public void setProductscanserialnumberId(BigInteger productscanserialnumberId) {
        this.productscanserialnumberId = productscanserialnumberId;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalcurrency() {
        return totalcurrency;
    }

    public void setTotalcurrency(Double totalcurrency) {
        this.totalcurrency = totalcurrency;
    }

    public BigInteger getTransferId() {
        return transferId;
    }

    public void setTransferId(BigInteger transferId) {
        this.transferId = transferId;
    }

    public String getUrlphoto() {
        return urlphoto;
    }

    public void setUrlphoto(String urlphoto) {
        this.urlphoto = urlphoto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfertable)) {
            return false;
        }
        Transfertable other = (Transfertable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Transfertable[ id=" + id + " ]";
    }
    
}
