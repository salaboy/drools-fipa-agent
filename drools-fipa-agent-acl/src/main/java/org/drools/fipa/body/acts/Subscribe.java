package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Act;
import org.drools.fipa.body.content.Query;

@XmlType(name = "Subscribe", namespace = "http://acts.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subscribe extends AbstractMessageBody {

    private Query query;
     
    public Subscribe() {
    }

    public Subscribe(Query query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "Subscribe{"
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

        Subscribe that = (Subscribe) o;

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
 
}
