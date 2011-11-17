/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.drools.fipa.mappers.MyMapArgsEntryType;

/**
 *
 * @author salaboy
 */
public class MapArgsAdapterHelper  {
  
  
   public static List<MyMapArgsEntryType> marshal(Map<String, Object> arg0)  {
      List<MyMapArgsEntryType> entries = new ArrayList<MyMapArgsEntryType>();
      for(Entry<String, Object> entry : arg0.entrySet()) {
         MyMapArgsEntryType myMapEntryType = 
            new MyMapArgsEntryType();
         myMapEntryType.setKey(entry.getKey());
         myMapEntryType.setValue( entry.getValue());
         entries.add(myMapEntryType);
      }
      return entries;
   }
  
   
   public static Map<String, Object> unmarshal(List<MyMapArgsEntryType> arg0)  {
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      for(MyMapArgsEntryType myEntryType : arg0) {
         hashMap.put(myEntryType.getKey(), myEntryType.getValue());
      }
      return hashMap;
   }
  
}
    
