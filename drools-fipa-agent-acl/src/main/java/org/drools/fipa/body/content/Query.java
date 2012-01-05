/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.fipa.body.content;






import java.util.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.mappers.MyMapReferenceEntryType;

@XmlType(name = "Query", namespace="http://content.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Query extends AbstractMessageContent  {
    @XmlElement(required = true)
    private String queryName;
    
//    @XmlJavaTypeAdapter(MyMapReferencesAdapter.class)
//    private Map<Integer, String> references = new HashMap<Integer, String>();
    @XmlElement(required = true)
    public List<MyMapReferenceEntryType> references = new ArrayList<MyMapReferenceEntryType>(); 
    
    @XmlElement(required = true)
    private List<Object> args = new ArrayList<Object>();

    public Query() {
    }

    
//    public Query(String queryName) {
//        this.queryName = queryName;
//        this.args = new Object[0];
//    }
//
//    public Query(String queryName, Object... args) {
//        this.queryName = queryName;
//        this.args = args;
//
//        references = new HashMap<Integer,String>();
//        for (int j = 0; j < args.length; j++) {
//            if (args[j] instanceof NamedVariable) {
//                NamedVariable var = (NamedVariable) args[j];
//                this.args[j] = var.getVariable();
//                references.put(j, var.getRef());
//            }
////            else {
////                this.args.add(j,args[j]);
////            }
//        }
//    }

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

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }

   


    



//    public void encode(Encodings encoding) {
//        if (! isEncoded()) {
//            setEncodedContent(encode(this,encoding));
////            args = null;
//            setEncoded(true);
//        }
//    }
//
//    public void decode(Encodings encoding) {
//        if (isEncoded()) {
//            Query q = (Query) decodeContent(getEncodedContent(),encoding);
//            queryName = q.getQueryName();
//            args = q.getArgs();
//            
//            if (args != null){
//                for (int i = 0; i < args.length; i++) {
//                    Object argument = args[i];
//                    if (argument != null && argument instanceof Variable){
//                        Variable tmpVariable = Variable.v;
////                        if (((Variable)argument).isSet()){
////                            tmpVariable.setValue(((Variable)argument).getValue());
////                        }
//                        args[i] = tmpVariable;
//                    }
//                }
//            }
//            
////            setEncodedContent(null);
//            setEncoded(false);
//        }
//    }



    //THESE METHODS SHOULD GO TO THE HELPERS
//    public Object[] getFullArguments() {
//        Object[] full = new Object[args.length+1];
//        full[0] = queryName;
//        for (int j = 0; j < args.length; j++) {
//            full[j+1] = args[j];
//        }
//        return full;
//    }

//    public Ref getReferences(QueryResults results) {
//       Map<String,Object> map = new HashMap<String,Object>();
//        org.drools.QueryResults inner = ((NativeQueryResults) results).getResults();
//            DroolsQuery query = (DroolsQuery) inner.get(0).get(-1);
//
//        for (Integer index : references.keySet()) {
//            map.put(references.get(index), query.getElements()[index]);
//        }
//
//        return new Ref(map);
//    }

    public List<MyMapReferenceEntryType> getReferences() {
        return references;
    }

    public void setReferences(List<MyMapReferenceEntryType> references) {
        this.references = references;
    }




}
