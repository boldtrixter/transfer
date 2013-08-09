/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muJAXB;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author user
 */

public class Data {
    
   private String id;
   private String ocpo;
   private String ruLName;
   private String ruFName;
   private String ruMName;

    public String getId() {
        return id;
    }

    @XmlAttribute(name = "Id")
    public void setId(String id) {
        this.id = id;
    }

    public String getOkro() {
        return ocpo;
    }

    @XmlAttribute(name = "OKPO")
    public void setOkro(String ocro) {
        this.ocpo = ocro;
    }

    public String getRuLName() {
        return ruLName;
    }

    @XmlAttribute
    public void setRuLName(String ruLName) {
        this.ruLName = ruLName;
    }

    public String getRuFName() {
        return ruFName;
    }

    @XmlAttribute
    public void setRuFName(String ruFName) {
        this.ruFName = ruFName;
    }

    public String getRuMName() {
        return ruMName;
    }

    @XmlAttribute
    public void setRuMName(String ruMName) {
        this.ruMName = ruMName;
    }
  
}
