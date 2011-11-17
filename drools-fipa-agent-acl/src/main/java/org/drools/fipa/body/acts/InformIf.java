package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Act;
import org.drools.fipa.Encodings;
import org.drools.fipa.body.content.Info;

@XmlType(name = "InformIf", namespace = "http://acts.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class InformIf extends AbstractMessageBody {

    
    @XmlElement(required = true)
    private Info proposition;

    public InformIf() {
    }

    public InformIf(Info proposition) {
        this.proposition = proposition;
    }

   

    @Override
    public String toString() {
        return "InformIf{"
                + "proposition=" + proposition
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InformIf that = (InformIf) o;

        if (proposition != null ? !proposition.equals(that.proposition) : that.proposition != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return proposition != null ? proposition.hashCode() : 0;
    }

    public Info getProposition() {
        return proposition;
    }

    public void setProposition(Info proposition) {
        this.proposition = proposition;
    }

    public Object[] getArguments() {
        return new Object[]{proposition.getData()};
    }
}
