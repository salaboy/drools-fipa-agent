package org.drools.fipa.body.acts;

import org.drools.fipa.ACLMessage;
import org.drools.fipa.ACLMessageFactory;
import org.drools.fipa.body.content.Ref;


public class InformRef extends AbstractMessageBody {


    public static final ACLMessage.Act performative = ACLMessage.Act.INFORM_REF;
    public ACLMessage.Act getPerformative() { return performative; }

    private Ref references;


    public InformRef(Ref references) {
        this.references = references;
    }

    @Override
    public String toString() {
        return "InformRef{" +
                "references=" + references +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InformRef informRef = (InformRef) o;

        if (references != null ? !references.equals(informRef.references) : informRef.references != null) return false;

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

    public String getEncodedContent() {
        return references.getEncodedContent();
    }

    public boolean isEncoded() {
        return references.isEncoded();
    }


    public void encode(ACLMessageFactory.Encodings encoding) {
        references.encode(encoding);
    }

    public void decode(ACLMessageFactory.Encodings encoding) {
        references.decode(encoding);
    }


    public Object[] getArguments() {
        return new Object[] { references.getReferences() };
    }
}

