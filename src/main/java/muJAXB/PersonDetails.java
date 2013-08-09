/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muJAXB;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@XmlRootElement(name = "doc")
public class PersonDetails {

    private Person person;

    public Person getPerson() {
        return person;
    }

    @XmlElement(name = "r")
    public void setPerson(Person person) {
        this.person = person;
    }

    public String getFName() {
        return person.getData().getRuFName();
    }

    public String getMName() {
        return person.getData().getRuMName();
    }

    public String getLName() {
        return person.getData().getRuLName();
    }

    public String getINN() {
        return person.getData().getOkro();
    }

    public String getIdEKB() {
        return person.getData().getId();
    }
}
