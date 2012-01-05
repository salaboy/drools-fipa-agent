/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa.mappers;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author salaboy
 */
@XmlType(name = "MyMapArgsEntryType", namespace="http://mappers.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyMapArgsEntryType implements Serializable{

    @XmlElement(required = true)
    private String key;
    @XmlElement(required = true)
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
