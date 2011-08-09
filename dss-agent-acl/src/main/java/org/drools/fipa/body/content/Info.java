package org.drools.fipa.body.content;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Info", namespace="http://content.body.fipa.drools.org/")
public class Info extends AbstractMessageContent  {

    private transient Object data;

    public Info() {
    }
    
//    public Info(Object data) {
//        this.data = data;
//    }


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
