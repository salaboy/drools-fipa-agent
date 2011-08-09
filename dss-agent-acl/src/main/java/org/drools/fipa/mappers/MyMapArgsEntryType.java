/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa.mappers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author salaboy
 */
@XmlType(name = "MyMapArgsEntryType", namespace="http://mappers.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyMapArgsEntryType {

    @XmlAttribute
    private String key;
    @XmlAnyElement
    private Object value;

    public MyMapArgsEntryType() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    
}
