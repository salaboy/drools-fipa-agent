package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Act;
import org.drools.fipa.Encodings;
import org.drools.fipa.body.content.Ref;

@XmlType(name = "InformRef", namespace = "http://acts.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class InformRef extends AbstractMessageBody {

    
    @XmlElement(required = true)
    private Ref references;
    
    public InformRef() {
    }

    @Override
    public String toString() {
        return "InformRef{"
                + "references=" + references
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

        InformRef informRef = (InformRef) o;

        if (references != null ? !references.equals(informRef.references) : informRef.references != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return references != null ? references.hashCode() : 0;
    }

    public Ref getReferences() {
        return references;
    }

    public void setReferences(Ref references) {
        this.references = references;
    }

}
