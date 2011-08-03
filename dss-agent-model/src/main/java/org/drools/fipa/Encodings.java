/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author salaboy
 */
@XmlType(name = "Encodings", namespace="http://fipa.drools.org/")
public enum Encodings {

    XML("text/xml"),
    JSON("application/json"),
    GSON("application/json"),
    BYTE("application/octet-stream"),
    NONE("application/x-java-serialized-object");
    private String encoding;

    private Encodings(String enc) {
        encoding = enc;
    }

    public String getEncoding() {
        return encoding;
    }
}
