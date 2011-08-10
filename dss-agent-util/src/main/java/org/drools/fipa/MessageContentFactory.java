/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.NamedVariable;
import org.drools.fipa.body.content.Query;
import org.drools.fipa.mappers.MyMapArgsEntryType;
import org.drools.fipa.mappers.MyMapReferenceEntryType;
import org.drools.runtime.rule.Variable;

/**
 *
 * @author salaboy
 */
public class MessageContentFactory { 

    public static Action newActionContent(String name, Map<String, Object> args) {
        Action action = new Action();
        action.setRETURN("?return");
        boolean hasOutputArg = false;
        action.setActionName(name);
        if(args == null){
            args = new HashMap<String, Object>();
        }
        action.getArgs().addAll(MapArgsAdapterHelper.marshal(args));


        if (args != null) {
            int j = 0;
            for (String key : args.keySet()) {
                if (args.get(key) instanceof Variable) {
                    MyMapReferenceEntryType myMapReferenceEntryType = new MyMapReferenceEntryType();
                    myMapReferenceEntryType.setKey(j);
                    myMapReferenceEntryType.setValue(key);
                    action.getReferences().add(myMapReferenceEntryType);
                    hasOutputArg = true;
                }
                j++;
            }
        }
        if (!hasOutputArg && args != null) {

            MyMapReferenceEntryType myMapReferenceEntryType = new MyMapReferenceEntryType();
            myMapReferenceEntryType.setKey( action.getArgs().size());
            myMapReferenceEntryType.setValue(action.getRETURN());
            action.getReferences().add(myMapReferenceEntryType);
            MyMapArgsEntryType myMapArgsEntryType = new MyMapArgsEntryType();
            myMapArgsEntryType.setKey(action.getRETURN());
            myMapArgsEntryType.setValue(Variable.v);
            action.getArgs().add(myMapArgsEntryType);
        }
        return action;
    }

    public static Action newActionContent(Action other) {
        Action action = new Action();
        action.setRETURN("?return");
        action.setActionName(other.getActionName());
        action.setArgs(other.getArgs());
        action.setReferences(other.getReferences());
        action.setEncodedContent(other.getEncodedContent());
        action.setEncoding(other.getEncoding());
        action.setEncoded(other.isEncoded());
        return action;
    }

    public static Query newQueryContent(String queryName, Object... args) {
        Query query = new Query();
        query.setQueryName(queryName);

        query.getArgs().addAll(Arrays.asList(args));
        //query.setReferences(new HashMap<Integer,String>());

        for (int j = 0; j < args.length; j++) {
            if (args[j] instanceof NamedVariable) {
                NamedVariable var = (NamedVariable) args[j];
                query.getArgs().set(j, var.getVariable());
                MyMapReferenceEntryType entry = new MyMapReferenceEntryType();
                entry.setKey(j);
                entry.setValue(var.getRef());
                query.getReferences().add(entry);


            }
//            else {
//                this.args.add(j,args[j]);
//            }
        }
        return query;
    }
}
