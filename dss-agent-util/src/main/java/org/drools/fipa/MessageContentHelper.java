/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.drools.base.DroolsQuery;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.NamedVariable;
import org.drools.fipa.body.content.Query;
import org.drools.fipa.body.content.Ref;
import org.drools.fipa.mappers.MyMapArgsEntryType;
import org.drools.fipa.mappers.MyMapReferenceEntryType;
import org.drools.rule.Declaration;
import org.drools.runtime.rule.impl.NativeQueryResults;
import org.drools.runtime.rule.QueryResults;
/**
 *
 * @author salaboy
 */
public class MessageContentHelper {

    public static Object[] getActionArgsArray(Action action) {
        Object[] myArray = new Object[action.getArgs().size()];
        int i = 0;
        for(MyMapArgsEntryType entry : action.getArgs()){
            myArray[i] = entry.getValue();
            i++;
        }
        return myArray;
    }

    public static Ref getActionReferences(Action action, QueryResults results) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        org.drools.QueryResults inner = ((NativeQueryResults) results).getResults();

        List<MyMapReferenceEntryType> pointers = action.getReferences();
        Declaration[] params = inner.getParameters();

        for (MyMapReferenceEntryType entry : pointers) {
            Declaration dec = params[ entry.getKey() ];
            map.put( entry.getValue(), inner.get(0).get( dec.getIdentifier() ) );
        }
        Ref ref = new Ref();
        ref.setReferences(MapArgsAdapterHelper.marshal(map));
        return ref;
    }

    public static NamedVariable variable(String ref) {
        return new NamedVariable(ref);
    }
    
    public static Ref getQueryReferences(Query query, QueryResults results) {
       Map<String,Object> map = new HashMap<String,Object>();
        org.drools.QueryResults inner = ((NativeQueryResults) results).getResults();
        Declaration[] params = inner.getParameters();

        for (MyMapReferenceEntryType entry : query.getReferences()) {
            Declaration dec = params[ entry.getKey() ];
            map.put( entry.getValue(), inner.get(0).get( dec.getIdentifier() ) );
        }

        Ref ref = new Ref();
        ref.setReferences(MapArgsAdapterHelper.marshal(map));
        
        return ref;
    }

}
