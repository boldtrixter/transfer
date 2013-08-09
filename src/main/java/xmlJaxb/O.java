/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlJaxb;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author user
 */
public class O {
    private String id;
    private String okpo;
    private String ruLName;
    private String ruFName;
    private String ruMName;

    public O() {
        this.id = "";
        this.okpo = "";
        this.ruLName = "";
        this.ruFName = "";
        this.ruMName = "";
    }
    
    

    public String getId() {
        return id;
    }

    @XmlElement
    public void setId(String id) {
        this.id = id;
    }

    public String getOkpo() {
        return okpo;
    }

    @XmlElement
    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getRuLName() {
        return ruLName;
    }

    @XmlElement
    public void setRuLName(String ruLName) {
        this.ruLName = ruLName;
    }

    public String getRuFName() {
        return ruFName;
    }

    @XmlElement
    public void setRuFName(String ruFName) {
        this.ruFName = ruFName;
    }

    public String getRuMName() {
        return ruMName;
    }

    @XmlElement
    public void setRuMName(String ruMName) {
        this.ruMName = ruMName;
    }
    
}
