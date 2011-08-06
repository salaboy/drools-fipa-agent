package org.drools.fipa.body.content;


import java.util.Map;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Encodings;

@XmlType(name = "Ref", namespace="http://content.body.fipa.drools.org/")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Ref extends AbstractMessageContent {
  //  @XmlElement(required = true)
    private Map<String,Object> references;

    public Ref() {
    }

    
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

//    public void encode(Encodings encoding) {
//        if (! isEncoded()) {
//            setEncodedContent(encode(references,encoding));
//            references = null;
//            setEncoded(true);
//        }
//    }
//
//    public void decode(Encodings encoding) {
//        if (isEncoded()) {
//            references = (Map<String,Object>) decodeContent(getEncodedContent(),encoding);
////            setEncodedContent(null);
//            setEncoded(false);
//        }
//    }


}
