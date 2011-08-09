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
public class Action extends AbstractMessageContent {
    
    @XmlElement(required = true)
    public String RETURN = "?return";
    @XmlElement(required = true)
    private String actionName;
    
//    @XmlJavaTypeAdapter(MyMapReferencesAdapter.class)
//    private Map<Integer, String> references = new HashMap<Integer, String>();
    @XmlElement(required = true)
    public List<MyMapReferenceEntryType> references = new ArrayList<MyMapReferenceEntryType>(); 
    
    
//    @XmlJavaTypeAdapter(MyMapArgsAdapter.class)
//    private Map<String,Object> args = new HashMap<String, Object>();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action = (Action) o;

        if (actionName != null ? !actionName.equals(action.actionName) : action.actionName != null) return false;
        if (args != null ? !args.equals(action.args) : action.args != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actionName != null ? actionName.hashCode() : 0;
        result = 31 * result + (args != null ? args.hashCode() : 0);
        return result;
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
}
