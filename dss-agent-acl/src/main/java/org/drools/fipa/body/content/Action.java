package org.drools.fipa.body.content;



import org.drools.base.DroolsQuery;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.Variable;
import org.drools.runtime.rule.impl.NativeQueryResults;

import java.util.*;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Encodings;

@XmlType(name = "Action", namespace="http://content.body.fipa.drools.org/")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Action extends AbstractMessageContent {

    public static final String RETURN = "?return";
//    @XmlElement(required = true)
    private String actionName;
//    @XmlElement(required = true)
    private Map<Integer, String> references;
//    @XmlElement(required = true)
    private Map<String,Object> args;

    public Action() {
    }



    public Action(String name, Map<String,Object> args) {
        boolean hasOutputArg = false;
        this.actionName = name;
        this.args = args == null ? Collections.<String, Object>emptyMap() : new LinkedHashMap<String,Object>(args);
        this.references = new HashMap<Integer,String>();
        if (args != null) {
            int j = 0;
            for (String key : args.keySet()) {
                if (args.get(key) instanceof Variable) {
                    references.put(j,key);
                    hasOutputArg = true;
                }
                j++;
            }
        }
        if (! hasOutputArg && args != null) {
            references.put(this.args.size(),RETURN);
            this.args.put(RETURN, Variable.v);
        }

    }

    public Action(Action other) {
        this.actionName = other.getActionName();
        this.args = new LinkedHashMap<String,Object>(other.args);
        this.references = new HashMap<Integer,String>(other.references);
        this.setEncodedContent(other.getEncodedContent());
    }


    @Override
    public String toString() {
        return "Action{" +
                "actionName='" + actionName + '\'' +
                ", args=" + (args == null ? null : Arrays.asList(args)) +
//                ", enc=" + getEncodedContent() +
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


    public Ref getReferences(QueryResults results) {
        Map<String,Object> map = new HashMap<String,Object>();
        org.drools.QueryResults inner = ((NativeQueryResults) results).getResults();
            DroolsQuery query = (DroolsQuery) inner.get(0).get(-1);

        for (Integer index : references.keySet()) {
            map.put(references.get(index), query.getElements()[index]);
        }

        return new Ref(map);
    }


    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    public Object[] getArgsArray() {
//        return args != null ? args.values().toArray() : new Object[0];
        return args.values().toArray();
    }
}
