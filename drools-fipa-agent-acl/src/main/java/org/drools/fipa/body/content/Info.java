package org.drools.fipa.body.content;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Info", namespace="http://content.body.fipa.drools.org/")
public class Info extends AbstractMessageContent  {
    @XmlElement()
    private Object data;

    public Info() {
    }

    @Override
    public String toString() {
        return "Info{" +
                "data=" + data +
                ", encoded=" + getEncodedContent() +
                '}';
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

   

    

}
