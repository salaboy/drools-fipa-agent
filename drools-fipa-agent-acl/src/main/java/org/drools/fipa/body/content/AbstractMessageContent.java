package org.drools.fipa.body.content;

import java.io.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Encodings;

/**
 * Actual mesasge content, i.e. the object of an ACL communicative act.
 */
@XmlType(name = "AbstractMessageContent", namespace = "http://acts.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractMessageContent implements Serializable {

    private String encodedContent;
    private boolean encoded;
    private Encodings encoding;
    
    public String getEncodedContent() {
        return encodedContent;
    }

    public void setEncodedContent(String encodedContent) {
        this.encodedContent = encodedContent;
    }

    public boolean isEncoded() {
        return encoded;
    }

    public Encodings getEncoding() {
        return encoding;
    }

    public void setEncoding(Encodings encoding) {
        this.encoding = encoding;
    }

    public void setEncoded(boolean encoded) {
        this.encoded = encoded;
    }
    
   
}
