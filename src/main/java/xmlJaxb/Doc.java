/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlJaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */


@XmlRootElement(name = "doc")
public class Doc {

     private R r;
    
    public Doc() {
        r = new R();
    }
    
    public R getR() {
        return r;
    }
    
    @XmlElement
    public void setR(R r) {
        this.r = r;
    }
    
}
