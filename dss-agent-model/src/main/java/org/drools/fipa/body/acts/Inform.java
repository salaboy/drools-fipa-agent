package org.drools.fipa.body.acts;

import org.drools.fipa.ACLMessage;
import org.drools.fipa.ACLMessageFactory;
import org.drools.fipa.body.content.Info;


public class Inform extends AbstractMessageBody {


    public static final ACLMessage.Act performative = ACLMessage.Act.INFORM;
    public ACLMessage.Act getPerformative() { return performative; }

    private Info proposition;

    public Inform() {
    }


    
    public Inform(Info proposition) {
        this.proposition = proposition;
    }


    @Override
    public String toString() {
        return "Inform{" +
                "proposition=" + proposition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inform that = (Inform) o;

        if (proposition != null ? !proposition.equals(that.proposition) : that.proposition != null) return false;

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

    public String getEncodedContent() {
        return proposition.getEncodedContent();
    }

    public boolean isEncoded() {
        return proposition.isEncoded();
    }


    public void encode(ACLMessageFactory.Encodings encoding) {
        proposition.encode(encoding);
    }

    public void decode(ACLMessageFactory.Encodings encoding) {
        proposition.decode(encoding);
    }

    public Object[] getArguments() {
        return new Object[] { proposition.getData() };
    }
}
