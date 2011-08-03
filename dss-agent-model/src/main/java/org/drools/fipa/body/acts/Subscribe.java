package org.drools.fipa.body.acts;

import org.drools.fipa.ACLMessage;
import org.drools.fipa.ACLMessageFactory;
import org.drools.fipa.body.content.Query;


public class Subscribe extends AbstractMessageBody {


    public static final ACLMessage.Act performative = ACLMessage.Act.SUBSCRIBE;
    public ACLMessage.Act getPerformative() { return performative; }

    private Query query;


    public Subscribe(Query query) {
        this.query = query;
    }


    @Override
    public String toString() {
        return "Subscribe{" +
                "query=" + query +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscribe that = (Subscribe) o;

        if (query != null ? !query.equals(that.query) : that.query != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return query != null ? query.hashCode() : 0;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String getEncodedContent() {
        return query.getEncodedContent();
    }

    public boolean isEncoded() {
        return query.isEncoded();
    }


    public void encode(ACLMessageFactory.Encodings encoding) {
        query.encode(encoding);
    }

    public void decode(ACLMessageFactory.Encodings encoding) {
        query.decode(encoding);
    }

    public Object[] getArguments() {
       return query.getFullArguments();
    }
}
