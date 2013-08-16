/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.transfer;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author user
 */
@Entity
@Table(name = "AVTRANSF")
public class Avtransf {
    @Id
    @NotNull
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "TRANSFERID", referencedColumnName = "ID")
    @ManyToOne
    private Transfer transferId;
    @JoinColumn(name = "PERSONID", referencedColumnName = "ID")
    @ManyToOne
    private Person personId;

    public Avtransf() {
    }

    public Avtransf(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Transfer getTransferid() {
        return transferId;
    }

    public void setTransferid(Transfer transferid) {
        this.transferId = transferid;
    }

    public Person getPersonid() {
        return personId;
    }

    public void setPersonid(Person personid) {
        this.personId = personid;
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
        if (!(object instanceof Avtransf)) {
            return false;
        }
        Avtransf other = (Avtransf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pb.transfer.Avtransf[ id=" + id + " ]";
    }
}
