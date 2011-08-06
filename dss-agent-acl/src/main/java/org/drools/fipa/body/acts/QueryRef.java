package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Act;
import org.drools.fipa.Encodings;
import org.drools.fipa.body.content.Query;

@XmlType(name = "QueryRef", namespace = "http://acts.body.fipa.drools.org/")
public class QueryRef extends AbstractMessageBody {

    
    @XmlElement(required = true)
    private Query query;

    public QueryRef() {
    }

    public QueryRef(Query query) {
        this.query = query;
    }

   

    @Override
    public String toString() {
        return "QueryRef{"
                + "query=" + query
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

        QueryRef that = (QueryRef) o;

        if (query != null ? !query.equals(that.query) : that.query != null) {
            return false;
        }

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


    public Object[] getArguments() {
        return query.getFullArguments();
    }
}
