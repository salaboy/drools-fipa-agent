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
public class Action extends AbstractMessageContent implements Map<String, Object> {
    
    @XmlElement(required = true)
    public String returnVariable = "?return";
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
                ", args=" + (args == null ? null : argsToString()) +
                ", refs=" + (args == null ? null : referencesToString()) +
                ", encoded=" + getEncodedContent() +
                '}';
    }

    private String referencesToString() {
        StringBuilder sb = new StringBuilder();
        for (MyMapReferenceEntryType ref : references) {
            sb.append( ref.getKey() ).append( "=").append( ref.getValue() );
        }
        return sb.toString();
    }

    private String argsToString() {
        StringBuilder sb = new StringBuilder();
        for ( MyMapArgsEntryType arg : args ) {
            sb.append( arg.getKey() ).append( "=").append( arg.getValue() );
        }
        return sb.toString();
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
        if ((this.returnVariable == null) ? (other.returnVariable != null) : !this.returnVariable.equals(other.returnVariable)) {
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
        hash = 83 * hash + (this.returnVariable != null ? this.returnVariable.hashCode() : 0);
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

   

    public String getReturnVariable() {
        return returnVariable;
    }

    public void setReturnVariable(String RETURN) {
        this.returnVariable = RETURN;
    }




    public int size() {
        return args.size();
    }

    public boolean isEmpty() {
        return size() > 0;
    }

    public boolean containsKey(Object o) {
        for(MyMapArgsEntryType entry : this.args){
            if( entry.getKey().equals(o.toString() ) ){
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object o) {
        for(MyMapArgsEntryType entry : this.args){
            if( entry.getValue().equals(o.toString()) ){
                return true;
            }
        }
        return false;
    }

    public Object get(Object o) {
        for(MyMapArgsEntryType entry : this.args){
            System.out.println("o.toString()"+o);
            if( entry.getKey().equals(o.toString()) ){
                System.out.println("entry.getKey()="+entry.getKey()+"====="+o);
                System.out.println("VALUE -> "+entry.getValue());
                return entry.getValue();
            }
        }
        return null;
    }

    public Object put(String key, Object value) {
        throw new UnsupportedOperationException("Read-only : put not allowed");
    }


    public Object remove(Object o) {
        throw new UnsupportedOperationException("Read-only : remove not allowed");
    }

    public void putAll(Map<? extends String, ? extends Object> map) {
        throw new UnsupportedOperationException("Read-only : putAll not allowed");
    }

    public void clear() {
        this.references.clear();
        this.args.clear();
    }

    public Set keySet() {
        HashSet<String> set = new HashSet<String>();
        for ( MyMapArgsEntryType entry : args ) {
            set.add( entry.getKey() );
        }
        return set;
    }

    public Collection values() {
        Collection<Object> list = new ArrayList<Object>();
        for ( MyMapArgsEntryType entry : args ) {
            list.add( entry.getValue() );
        }
        return list;
    }

    public Set entrySet() {
        HashSet<Entry<String,Object>> set = new HashSet<Entry<String,Object>>();
        for ( MyMapArgsEntryType entry : args ) {
            final String k = entry.getKey();
            final Object v = entry.getValue();
            set.add( new Entry<String,Object>() {

                {
                    key = k;
                    value = v;
                }

                private String key;
                private Object value;

                public String getKey() {
                    return key;
                }

                public Object getValue() {
                    return value;
                }

                public Object setValue(Object value) {
                    this.value = value;
                    return value;
                }

                public String toString() {
                    return "[" + key + " = " + value +"]";
                }
            } );
        }
        return set;
    }
}
