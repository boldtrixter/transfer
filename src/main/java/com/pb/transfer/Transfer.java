/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.transfer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author user
 */
@Entity
@Table(name = "TRANSFER")
public class Transfer {
    @Id
    @NotNull
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATECREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreate;
    @Size(max = 10)
    @Column(name = "ACCEPTANCETYPE")
    private String acceptancetype;
    @Size(max = 100)
    @Column(name = "CALLBACKLINK")
    private String callbacklink;
    @OneToMany(mappedBy = "transferId")
    private List<Avtransf> avtransfList;
    @JoinColumn(name = "STATUSID", referencedColumnName = "ID")
    @ManyToOne
    private Status statusId;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "ID")
    @ManyToOne
    private Product productid;
    @JoinColumn(name = "SENDERID", referencedColumnName = "ID")
    @ManyToOne
    private Person senderId;
    @JoinColumn(name = "RECIPIENTID", referencedColumnName = "ID")
    @ManyToOne
    private Person recipientId;
    @JoinColumn(name = "CARRIERID", referencedColumnName = "ID")
    @ManyToOne
    private Person carrierId;
    @JoinColumn(name = "COORDSID", referencedColumnName = "ID")
    @ManyToOne
    private Coordinates coordsid;
    

    public Transfer() {
    }

    public Transfer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public String getAcceptancetype() {
        return acceptancetype;
    }

    public void setAcceptancetype(String acceptancetype) {
        this.acceptancetype = acceptancetype;
    }

    public String getCallbacklink() {
        return callbacklink;
    }

    public void setCallbacklink(String callbacklink) {
        this.callbacklink = callbacklink;
    }

    public List<Avtransf> getAvtransfList() {
        return avtransfList;
    }

    public void setAvtransfList(List<Avtransf> avtransfList) {
        this.avtransfList = avtransfList;
    }

    public Status getStatusid() {
        return statusId;
    }

    public void setStatusid(Status statusid) {
        this.statusId = statusid;
    }

    public Product getProductid() {
        return productid;
    }

    public void setProductid(Product productid) {
        this.productid = productid;
    }

    public Person getSenderid() {
        return senderId;
    }

    public void setSenderid(Person senderid) {
        this.senderId = senderid;
    }

    public Person getRecipientid() {
        return recipientId;
    }

    public void setRecipientid(Person recipientid) {
        this.recipientId = recipientid;
    }

    public Person getCarrierid() {
        return carrierId;
    }

    public void setCarrierid(Person carrierid) {
        this.carrierId = carrierid;
    }

    public Coordinates getCoordsid() {
        return coordsid;
    }

    public void setCoordsid(Coordinates coordsid) {
        this.coordsid = coordsid;
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
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pb.transfer.Transfer[ id=" + id + " ]";
    }
    
}
