/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.transfer;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author user
 */
@Entity
@Table(name = "COORDINATES")
public class Coordinates implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    private Integer id;
    @Size(max = 10)
    @Column(name = "LONGTITUDE")
    private String longtitude;
    @Size(max = 10)
    @Column(name = "LATITUDE")
    private String latitude;
    @Column(name = "VARIATION")
    private Integer variation;
    @OneToMany(mappedBy = "coordsid")
    private List<Transfer> transferList;

    public Coordinates() {
    }

    public Coordinates(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getVariation() {
        return variation;
    }

    public void setVariation(Integer variation) {
        this.variation = variation;
    }

    public List<Transfer> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<Transfer> transferList) {
        this.transferList = transferList;
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
        if (!(object instanceof Coordinates)) {
            return false;
        }
        Coordinates other = (Coordinates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pb.transfer.Coordinates[ id=" + id + " ]";
    }
    
}
