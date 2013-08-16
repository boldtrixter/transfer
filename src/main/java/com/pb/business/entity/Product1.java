///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.pb.business.entity;
//
//import java.io.Serializable;
//import java.math.BigInteger;
//import java.util.List;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import org.hibernate.annotations.GenericGenerator;
//
///**
// *
// * @author Dmitry
// */
//
//@Entity
//@Table(name = "PRODUCT")
//public class Product1 {
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "Id")
//    private Integer id;
//    @Size(max = 50)
//    @Column(name = "Title")
//    private String title;
//    @Size(max = 100)
//    @Column(name = "UrlPhoto")
//    private String urlPhoto;
//    @Size(max = 100)
//    @Column(name = "Description")
//    private String description;
//    @OneToMany(mappedBy = "productId")
//    private List<Transfer1> transfer1List;
//
//    @Id
//    @NotNull
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name = "increment", strategy = "increment")
//    @Column(name = "ID")
//    private Long id;
//    @Column(name = "FOLDER")
//    private Short folder;
//    @Column(name = "IDPARENT")
//    private String idparent;
//    @Column(name = "NAME")
//    private String name;
//    @Column(name = "SCANCODE")
//    private String scancode;
//    @Column(name = "UNIT_ID")
//    private BigInteger unitId;
//    @Column(name = "URLPHOTO")
//    private String urlphoto;
//
//    public Product1() {
//    }
//
//    public Product1(Long id) {
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
//    public Short getFolder() {
//        return folder;
//    }
//
//    public void setFolder(Short folder) {
//        this.folder = folder;
//    }
//
//    public String getIdparent() {
//        return idparent;
//    }
//
//    public void setIdparent(String idparent) {
//        this.idparent = idparent;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getScancode() {
//        return scancode;
//    }
//
//    public void setScancode(String scancode) {
//        this.scancode = scancode;
//    }
//
//    public BigInteger getUnitId() {
//        return unitId;
//    }
//
//    public void setUnitId(BigInteger unitId) {
//        this.unitId = unitId;
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
//    public Product1(Integer id) {
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
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getUrlPhoto() {
//        return urlPhoto;
//    }
//
//    public void setUrlPhoto(String urlPhoto) {
//        this.urlPhoto = urlPhoto;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
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
//        if (!(object instanceof Product1)) {
//            return false;
//        }
//        Product1 other = (Product1) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.pb.business.entity.Product1[ id=" + id + " ]";
//    }
//
//}
