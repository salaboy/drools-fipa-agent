package org.drools.fipa.body.content;

import org.drools.fipa.ACLMessageFactory;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 5/9/11
 * Time: 2:29 AM
 */
public class Ref extends AbstractMessageContent {

    private Map<String,Object> references;

    public Ref(Map<String, Object> references) {
        this.references = references;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ref ref = (Ref) o;

        if (references != null ? !references.equals(ref.references) : ref.references != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return references != null ? references.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Ref{" +
                "references=" + references +
                ", encoded= " + getEncodedContent() +
                '}';
    }

    public Map<String, Object> getReferences() {
        return references;
    }

    public void setReferences(Map<String, Object> references) {
        this.references = references;
    }

    public void encode(ACLMessageFactory.Encodings encoding) {
        if (! isEncoded()) {
            setEncodedContent(encode(references,encoding));
            references = null;
            setEncoded(true);
        }
    }

    public void decode(ACLMessageFactory.Encodings encoding) {
        if (isEncoded()) {
            references = (Map<String,Object>) decodeContent(getEncodedContent(),encoding);
//            setEncodedContent(null);
            setEncoded(false);
        }
    }


}
