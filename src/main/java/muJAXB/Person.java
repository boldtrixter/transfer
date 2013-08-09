/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muJAXB;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author user
 */

//@XmlType(propOrder = {"INF_NEW"})
public class Person {
    
    
    private Data data;

    public Data getData() {
        return data;
    }

    @XmlElement(name = "INF_NEW")
    public void setData(Data infNew) {
        this.data = infNew;
    }
    
}
