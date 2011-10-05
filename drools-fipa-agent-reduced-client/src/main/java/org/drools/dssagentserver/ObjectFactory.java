
package org.drools.dssagentserver;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.drools.dssagentserver package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TellResponse_QNAME = new QName("http://dssagentserver.drools.org/", "tellResponse");
    private final static QName _Tell_QNAME = new QName("http://dssagentserver.drools.org/", "tell");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.drools.dssagentserver
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Tell }
     * 
     */
    public Tell createTell() {
        return new Tell();
    }

    /**
     * Create an instance of {@link TellResponse }
     * 
     */
    public TellResponse createTellResponse() {
        return new TellResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TellResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dssagentserver.drools.org/", name = "tellResponse")
    public JAXBElement<TellResponse> createTellResponse(TellResponse value) {
        return new JAXBElement<TellResponse>(_TellResponse_QNAME, TellResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tell }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dssagentserver.drools.org/", name = "tell")
    public JAXBElement<Tell> createTell(Tell value) {
        return new JAXBElement<Tell>(_Tell_QNAME, Tell.class, null, value);
    }

}
