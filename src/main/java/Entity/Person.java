/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATECHANGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datechange;
    @Column(name = "DATECREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreate;
    @Column(name = "FIO")
    private String fio;
    @Column(name = "IDEKB")
    private String idekb;
    @Column(name = "INN")
    private BigInteger inn;
    @Column(name = "PASS")
    private String pass;
    @Column(name = "PHONENUMBER")
    private String phonenumber;
    @Column(name = "TABNUM")
    private BigInteger tabnum;
    @Column(name = "TYPE")
    private Short type;

    public Person() {
    }

    public Person(Long id) {
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

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getIdekb() {
        return idekb;
    }

    public void setIdekb(String idekb) {
        this.idekb = idekb;
    }

    public BigInteger getInn() {
        return inn;
    }

    public void setInn(BigInteger inn) {
        this.inn = inn;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public BigInteger getTabnum() {
        return tabnum;
    }

    public void setTabnum(BigInteger tabnum) {
        this.tabnum = tabnum;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Person[ id=" + id + " ]";
    }
    
}
