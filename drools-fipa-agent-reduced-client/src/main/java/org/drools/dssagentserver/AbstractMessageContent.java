
package org.drools.dssagentserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Encodings;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Info;
import org.drools.fipa.body.content.Query;
import org.drools.fipa.body.content.Ref;
import org.drools.fipa.body.content.Rule;


/**
 * <p>Java class for abstractMessageContent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="abstractMessageContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="encoded" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="encodedContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="encoding" type="{http://fipa.drools.org/}Encodings" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "abstractMessageContent", propOrder = {
    "encoded",
    "encodedContent",
    "encoding"
})
@XmlSeeAlso({
    Ref.class,
    Action.class,
    Query.class,
    Rule.class,
    Info.class
})
public abstract class AbstractMessageContent {

    protected boolean encoded;
    protected String encodedContent;
    protected Encodings encoding;

    /**
     * Gets the value of the encoded property.
     * 
     */
    public boolean isEncoded() {
        return encoded;
    }

    /**
     * Sets the value of the encoded property.
     * 
     */
    public void setEncoded(boolean value) {
        this.encoded = value;
    }

    /**
     * Gets the value of the encodedContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncodedContent() {
        return encodedContent;
    }

    /**
     * Sets the value of the encodedContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncodedContent(String value) {
        this.encodedContent = value;
    }

    /**
     * Gets the value of the encoding property.
     * 
     * @return
     *     possible object is
     *     {@link Encodings }
     *     
     */
    public Encodings getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link Encodings }
     *     
     */
    public void setEncoding(Encodings value) {
        this.encoding = value;
    }

}
