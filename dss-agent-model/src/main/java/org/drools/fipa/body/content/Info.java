package org.drools.fipa.body.content;

import org.drools.fipa.ACLMessageFactory;

/**
 * Created by IntelliJ IDEA.
 * Date: 5/9/11
 * Time: 1:43 AM
 */
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


    public void encode(ACLMessageFactory.Encodings encoding) {
        if (! isEncoded()) {
            setEncodedContent(encode(data,encoding));
            data = null;
            setEncoded(true);
        }
    }

    public void decode(ACLMessageFactory.Encodings encoding) {
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
