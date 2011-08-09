/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa.mappers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author salaboy
 */
@XmlType(name = "MyMapReferenceEntryType", namespace="http://mappers.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyMapReferenceEntryType {

    @XmlAttribute
    private Integer key;
    @XmlValue
    private String value;

    public MyMapReferenceEntryType() {
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
