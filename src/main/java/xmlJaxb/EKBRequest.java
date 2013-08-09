/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlJaxb;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
public class EKBRequest {

    
    private Doc d;

    
    public Doc getD() {
        return d;
    }

    public void setD(Doc d) {
        this.d = d;
    }

    public EKBRequest() {
        d = new Doc();
    }

    public void setCountry(String country) {
        d.getR().setCntr(country);
    }

    public void setSid(String sid) {
        d.getR().setSid(sid);
    }
    
    public void setPhone(String phone){
        d.getR().getI().setAllPhone(phone);
    }
}
