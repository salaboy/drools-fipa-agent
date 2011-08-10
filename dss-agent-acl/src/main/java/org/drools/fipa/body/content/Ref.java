package org.drools.fipa.body.content;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.mappers.MyMapArgsEntryType;

@XmlType(name = "Ref", namespace="http://content.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ref extends AbstractMessageContent implements Map{
  
   @XmlElement(required = true)
    public List<MyMapArgsEntryType> references = new ArrayList<MyMapArgsEntryType>(); 
   

    public Ref() {
    }

    
//    public Ref(Map<String, Object> references) {
//        this.references = references;
//    }

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

    public List<MyMapArgsEntryType> getReferences() {
        return references;
    }

    public void setReferences(List<MyMapArgsEntryType> references) {
        this.references = references;
    }

    public int size() {
        
        return references.size();
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsKey(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsValue(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object get(Object o) {
        for(MyMapArgsEntryType entry : this.references){
            if(entry.getKey().equals(o.toString() )){
                return entry.getValue();
            }
        }
        return null;
    }

    public Object put(Object k, Object v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void putAll(Map map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
