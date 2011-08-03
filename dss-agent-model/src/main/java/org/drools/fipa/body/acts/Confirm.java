package org.drools.fipa.body.acts;

import org.drools.fipa.Act;
import org.drools.fipa.Encodings;
import org.drools.fipa.body.content.Info;


public class Confirm extends AbstractMessageBody {


    public static final Act performative = Act.CONFIRM;
    public Act getPerformative() { return performative; }

    private Info proposition;


    public Confirm(Info proposition) {
        this.proposition = proposition;
    }


    @Override
    public String toString() {
        return "Confirm{" +
                "proposition=" + proposition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Confirm that = (Confirm) o;

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

    public void encode(Encodings encoding) {
        proposition.encode(encoding);
    }

    public void decode(Encodings encoding) {
        proposition.decode(encoding);
    }

    public Object[] getArguments() {
        return new Object[] { proposition.getData() };
    }
}

