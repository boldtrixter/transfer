/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlJaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author user
 */
public class I {

    private O o;
    private String allPhone;

    public String getAllPhone() {
        return allPhone;
    }

    @XmlAttribute
    public void setAllPhone(String allPhone) {
        this.allPhone = allPhone;
    }
    
    public I() {
        o = new O();
        this.allPhone = "+380934682670";
    }
    
    
    

    public O getO() {
        return o;
    }

    @XmlElement
    public void setO(O o) {
        this.o = o;
    }
    
}
