/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.drools.fipa.mappers.MyMapArgsEntryType;
import org.drools.fipa.mappers.MyMapReferenceEntryType;

/**
 *
 * @author salaboy
 */
public class MapReferencesAdapterHelper  {
  
  
   public static List<MyMapReferenceEntryType> marshal(Map<Integer, String> arg0)  {
      List<MyMapReferenceEntryType> entries = new ArrayList<MyMapReferenceEntryType>();
      for(Entry<Integer, String> entry : arg0.entrySet()) {
         MyMapReferenceEntryType myMapEntryType = 
            new MyMapReferenceEntryType();
         myMapEntryType.setKey(entry.getKey());
         myMapEntryType.setValue( entry.getValue());
         entries.add(myMapEntryType);
      }
      return entries;
   }
  
   
   public static Map<Integer, String> unmarshal(List<MyMapReferenceEntryType> arg0)  {
      HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
      for(MyMapReferenceEntryType myEntryType : arg0) {
         hashMap.put(myEntryType.getKey(), myEntryType.getValue());
      }
      return hashMap;
   }
  
}
    
