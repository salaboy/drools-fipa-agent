package org.drools.fipa.body.content;

import java.io.*;
import org.drools.fipa.Encodings;

/**
 * Actual mesasge content, i.e. the object of an ACL communicative act.
 */
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
    
    public static NamedVariable variable(String ref) {
        return new NamedVariable(ref);
    }
}
