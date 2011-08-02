package org.drools.fipa.body.content;

import org.drools.QueryResult;
import org.drools.base.ArrayElements;
import org.drools.base.DroolsQuery;
import org.drools.fipa.ACLMessageFactory;
import org.drools.rule.Declaration;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.Variable;
import org.drools.runtime.rule.impl.NativeQueryResults;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 5/7/11
 * Time: 10:32 PM
 */
public class Query extends AbstractMessageContent  {

    private String queryName;
    private Map<Integer, String> references;
    private Object[] args;

    public Query() {
    }

    
    public Query(String queryName) {
        this.queryName = queryName;
        this.args = new Object[0];
    }

    public Query(String queryName, Object... args) {
        this.queryName = queryName;
        this.args = args;

        references = new HashMap<Integer,String>();
        for (int j = 0; j < args.length; j++) {
            if (args[j] instanceof NamedVariable) {
                NamedVariable var = (NamedVariable) args[j];
                this.args[j] = var.getVariable();
                references.put(j, var.getRef());
            }
//            else {
//                this.args.add(j,args[j]);
//            }
        }
    }

    @Override
    public String toString() {
        return "Query{" +
                "queryName='" + queryName + '\'' +
                ", args=" + (args == null ? null : Arrays.asList(args)) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Query query = (Query) o;

        if (args != null ? !args.equals(query.args) : query.args != null) return false;
        if (queryName != null ? !queryName.equals(query.queryName) : query.queryName != null) return false;
        if (references != null ? !references.equals(query.references) : query.references != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = queryName != null ? queryName.hashCode() : 0;
        result = 31 * result + (references != null ? references.hashCode() : 0);
        result = 31 * result + (args != null ? args.hashCode() : 0);
        return result;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }




    public Object[] getFullArguments() {
        Object[] full = new Object[args.length+1];
        full[0] = queryName;
        for (int j = 0; j < args.length; j++) {
            full[j+1] = args[j];
        }
        return full;
    }

    public void encode(ACLMessageFactory.Encodings encoding) {
        if (! isEncoded()) {
            setEncodedContent(encode(this,encoding));
//            args = null;
            setEncoded(true);
        }
    }

    public void decode(ACLMessageFactory.Encodings encoding) {
        if (isEncoded()) {
            Query q = (Query) decodeContent(getEncodedContent(),encoding);
            queryName = q.getQueryName();
            args = q.getArgs();
            
            if (args != null){
                for (int i = 0; i < args.length; i++) {
                    Object argument = args[i];
                    if (argument != null && argument instanceof Variable){
                        Variable tmpVariable = Variable.v;
//                        if (((Variable)argument).isSet()){
//                            tmpVariable.setValue(((Variable)argument).getValue());
//                        }
                        args[i] = tmpVariable;
                    }
                }
            }
            
//            setEncodedContent(null);
            setEncoded(false);
        }
    }





    public Ref getReferences(QueryResults results) {
       Map<String,Object> map = new HashMap<String,Object>();
        org.drools.QueryResults inner = ((NativeQueryResults) results).getResults();
            DroolsQuery query = (DroolsQuery) inner.get(0).get(-1);

        for (Integer index : references.keySet()) {
            map.put(references.get(index), query.getElements()[index]);
        }

        return new Ref(map);
    }




}
