package org.drools.fipa.body.content;

import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Encodings;

@XmlType(name = "Info", namespace="http://content.body.fipa.drools.org/")
public class Info extends AbstractMessageContent  {

    private transient Object data;

    public Info() {
    }
    
    public Info(Object data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Info{" +
                "data=" + data +
                ", encoded=" + getEncodedContent() +
                '}';
    }


    public void encode(Encodings encoding) {
        if (! isEncoded()) {
            setEncodedContent(encode(data,encoding));
            data = null;
            setEncoded(true);
        }
    }

    public void decode(Encodings encoding) {
        if (isEncoded()) {
            data = decodeContent(getEncodedContent(),encoding);
//            setEncodedContent(null);
            setEncoded(false);
        }
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
