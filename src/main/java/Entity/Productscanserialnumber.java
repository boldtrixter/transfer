/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author user
 */

public class Productscanserialnumber implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRODUCT_ID")
    private BigInteger productId;

    public Productscanserialnumber() {
    }

    public Productscanserialnumber(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
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
        if (!(object instanceof Productscanserialnumber)) {
            return false;
        }
        Productscanserialnumber other = (Productscanserialnumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Productscanserialnumber[ id=" + id + " ]";
    }
    
}
