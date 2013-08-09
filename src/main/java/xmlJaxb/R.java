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
class R {
    
    private String cntr;
    private String key;
    private String sid;
    private String t;
    
    private I i;

    public R() {
        this.cntr = "UA";
        this.key = "0";
        this.sid = "1234567";
        this.t = "INF_NEW";
        i = new I();
    }
    
 
    

    public I getI() {
        return i;
    }

    @XmlElement
    public void setI(I i) {
        this.i = i;
    }

    public String getCntr() {
        return cntr;
    }

    @XmlAttribute
    public void setCntr(String cntr) {
        this.cntr = cntr;
    }

    public String getKey() {
        return key;
    }

    @XmlAttribute
    public void setKey(String key) {
        this.key = key;
    }

    public String getSid() {
        return sid;
    }

    @XmlAttribute
    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getT() {
        return t;
    }

    @XmlAttribute
    public void setT(String t) {
        this.t = t;
    }
       
}
