package org.drools.fipa.body.content;




import java.util.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.mappers.MyMapArgsEntryType;
import org.drools.fipa.mappers.MyMapReferenceEntryType;

@XmlType(name = "Action", namespace="http://content.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Action extends AbstractMessageContent implements Map{
    
    @XmlElement(required = true)
    public String RETURN = "?return";
    @XmlElement(required = true)
    private String actionName;
    
    @XmlElement(required = true)
    public List<MyMapReferenceEntryType> references = new ArrayList<MyMapReferenceEntryType>(); 
    
    
    @XmlElement(required = true)
    private List<MyMapArgsEntryType> args = new ArrayList<MyMapArgsEntryType>();
    
    public Action() {
    }



//    public Action(String name, Map<String,Object> args) {
//        boolean hasOutputArg = false;
//        this.actionName = name;
//        this.args = args == null ? Collections.<String, Object>emptyMap() : new LinkedHashMap<String,Object>(args);
//        this.references = new HashMap<Integer,String>();
//        if (args != null) {
//            int j = 0;
//            for (String key : args.keySet()) {
//                if (args.get(key) instanceof Variable) {
//                    references.put(j,key);
//                    hasOutputArg = true;
//                }
//                j++;
//            }
//        }
//        if (! hasOutputArg && args != null) {
//            references.put(this.args.size(),RETURN);
//            this.args.put(RETURN, Variable.v);
//        }
//
//    }
//
//    public Action(Action other) {
//        this.actionName = other.getActionName();
//        this.args = new LinkedHashMap<String,Object>(other.args);
//        this.references = new HashMap<Integer,String>(other.references);
//        this.setEncodedContent(other.getEncodedContent());
//    }


    @Override
    public String toString() {
        return "Action{" +
                "actionName='" + actionName + '\'' +
                ", args=" + (args == null ? null : Arrays.asList(args)) +
                ", encoded=" + getEncodedContent() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Action other = (Action) obj;
        if ((this.RETURN == null) ? (other.RETURN != null) : !this.RETURN.equals(other.RETURN)) {
            return false;
        }
        if ((this.actionName == null) ? (other.actionName != null) : !this.actionName.equals(other.actionName)) {
            return false;
        }
        if (this.references != other.references && (this.references == null || !this.references.equals(other.references))) {
            return false;
        }
        if (this.args != other.args && (this.args == null || !this.args.equals(other.args))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.RETURN != null ? this.RETURN.hashCode() : 0);
        hash = 83 * hash + (this.actionName != null ? this.actionName.hashCode() : 0);
        hash = 83 * hash + (this.references != null ? this.references.hashCode() : 0);
        hash = 83 * hash + (this.args != null ? this.args.hashCode() : 0);
        return hash;
    }

  
    

//    public void encode(Encodings encoding) {
//        if (! isEncoded()) {
//            setEncodedContent(encode(this,encoding));
//            args = null;
//            setEncoded(true);
//        }
//    }
//
//    public void decode(Encodings encoding) {
//        if (isEncoded()) {
//            Action act = (Action) decodeContent(getEncodedContent(),encoding);
//            actionName = act.getActionName();
//            args = act.getArgs();
////            setEncodedContent(null);
//            setEncoded(false);
//        }
//    }

    public List<MyMapReferenceEntryType> getReferences() {
        return references;
    }

    public void setReferences(List<MyMapReferenceEntryType> references) {
        this.references = references;
    }

    

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public List<MyMapArgsEntryType> getArgs() {
        return args;
    }

    public void setArgs(List<MyMapArgsEntryType> args) {
        this.args = args;
    }

   

    public String getRETURN() {
        return RETURN;
    }

    public void setRETURN(String RETURN) {
        this.RETURN = RETURN;
    }

    public int size() {
        return args.size();
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
        for(MyMapArgsEntryType entry : this.args){
            System.out.println("o.toString()"+o);
            if(entry.getKey().equals(o.toString())){
                System.out.println("entry.getKey()="+entry.getKey()+"====="+o);
                System.out.println("VALUE -> "+entry.getValue());
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
