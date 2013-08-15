/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Dmitry
 */

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @NotNull
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    private Long id;
    @Column(name = "FOLDER")
    private Short folder;
    @Column(name = "IDPARENT")
    private String idparent;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SCANCODE")
    private String scancode;
    @Column(name = "UNIT_ID")
    private BigInteger unitId;
    @Column(name = "URLPHOTO")
    private String urlphoto;

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getFolder() {
        return folder;
    }

    public void setFolder(Short folder) {
        this.folder = folder;
    }

    public String getIdparent() {
        return idparent;
    }

    public void setIdparent(String idparent) {
        this.idparent = idparent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScancode() {
        return scancode;
    }

    public void setScancode(String scancode) {
        this.scancode = scancode;
    }

    public BigInteger getUnitId() {
        return unitId;
    }

    public void setUnitId(BigInteger unitId) {
        this.unitId = unitId;
    }

    public String getUrlphoto() {
        return urlphoto;
    }

    public void setUrlphoto(String urlphoto) {
        this.urlphoto = urlphoto;
    }

}
