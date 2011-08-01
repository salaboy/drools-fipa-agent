package org.drools.fipa;

import com.jayway.jsonpath.JsonPath;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import org.drools.fipa.body.acts.AbstractMessageBody;



/**
 * Agent Communication Language Message, as defined by the FIPA standard
 *
 * A Message represents a communicative act (aka "performative"), chosen from a predefined standard set.
 * A performative will have a content (e.g. Info, Query), wrapping the actual arguments.
 *
 * Other than that, the message will contain sender and receiver references, in addition to
 * context and metadata information
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ACLMessage implements Serializable {

    public static final String DEFAULT_FIPA_MESSAGE_TYPE = "DEFAULT_FIPA_MESSAGE_TYPE";
    public static final String DROOLS_DRL = "DROOLS_DRL";
    public static final String KMR2 = "KMR2";


    public enum Act {
        ACCEPT              ("accept-proposal"),
        AGREE               ("agree"),
        CANCEL              ("cancel"),
        CALL_FOR_PROPOSAL   ("cfp"),
        CONFIRM             ("confirm"),
        DISCONFIRM          ("disconfirm"),
        FAILURE             ("failure"),
        INFORM              ("inform"),
        INFORM_IF           ("inform-if"),
        INFORM_REF          ("inform-ref"),
        NOT_UNDERSTOOD      ("not-understood"),
        PROPOSE             ("propose"),
        QUERY_IF            ("query-if"),
        QUERY_REF           ("query-ref"),
        REFUSE              ("refuse"),
        REJECT              ("reject-proposal"),
        REQUEST             ("request"),
        REQUEST_WHEN        ("request-when"),
        REQUEST_WHENEVER    ("request-whenever"),
        SUBSCRIBE           ("subscribe"),
        PROXY               ("proxy"),
        PROPAGATE           ("propagate");

        private String name;

        private Act(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }

        public static Act getPerformative(String name) {
            return map.get(name);
        }

        private static Map<String, Act> map;
        static {
            map = new HashMap<String, Act>();
            for (Act performative : Act.values()) {
                map.put(performative.toString(), performative);
            }
        }

        Set<String> getPerformativeNames() {
            return map.keySet();
        }


    }

    private String id;
    private String version;

    private String messageType = DEFAULT_FIPA_MESSAGE_TYPE;


    @XmlElement(required = true) 
    private String protocol;
    @XmlElement(required = true) 
    private String conversationId;
    @XmlElement(required = true) 
    private String replyWith;
    @XmlElement(required = true) 
    private String inReplyTo;
    @XmlElement(required = true) 
    private long replyBy;

    @XmlElement(required = true) 
    private String ontology = KMR2;
    @XmlElement(required = true) 
    private String language = DROOLS_DRL;
    @XmlElement(required = true) 
    private ACLMessageFactory.Encodings encoding;
    @XmlElement(required = true) 
    private AgentID sender;
    @XmlElement(required = true) 
    private Set<AgentID> receiver;
    @XmlElement(required = true) 
    private Set<AgentID> replyTo;
    @XmlElement(required = true) 
    private Act performative;
    @XmlElement(required = true) 
    private AbstractMessageBody body;


    ACLMessage() {

    }

    ACLMessage(String id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ACLMessage that = (ACLMessage) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : -1;
    }


    @Override
    public String toString() {
        return "ACLMessage{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", messageType='" + messageType + '\'' +
                ", protocol='" + protocol + '\'' +
                ", conversationId=" + conversationId +
                ", replyWith='" + replyWith + '\'' +
                ", inReplyTo='" + inReplyTo + '\'' +
                ", replyBy='" + replyBy + '\'' +
                ", ontology='" + ontology + '\'' +
                ", language='" + language + '\'' +
                ", encoding='" + encoding + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", replyTo=" + replyTo +
                ", performative=" + performative +
                ", body='" + body + '\'' +
                '}';
    }



    public String inspect(String path) throws ParseException, XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        switch (encoding) {
            case JSON:
                Object res = JsonPath.read(getBody().getEncodedContent(),path);
                return (res != null) ? res.toString() : null;
            case XML:
                XPath accessor = XPathFactory.newInstance().newXPath();
                InputStream inStream = new ByteArrayInputStream(getBody().getEncodedContent().getBytes());
                Document dox = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream);
                return (String) accessor.evaluate(path, dox, XPathConstants.STRING);
            default :
                throw new ParseException("Unable to access byte-encoded message body",0);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getReplyWith() {
        return replyWith;
    }

    public void setReplyWith(String replyWith) {
        this.replyWith = replyWith;
    }

    public String getInReplyTo() {
        return inReplyTo;
    }

    public void setInReplyTo(String inReplyTo) {
        this.inReplyTo = inReplyTo;
    }

    public long getReplyBy() {
        return replyBy;
    }

    public void setReplyBy(long replyBy) {
        this.replyBy = replyBy;
    }

    public String getOntology() {
        return ontology;
    }

    public void setOntology(String ontology) {
        this.ontology = ontology;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ACLMessageFactory.Encodings getEncoding() {
        return encoding;
    }

    public void setEncoding(ACLMessageFactory.Encodings encoding) {
        this.encoding = encoding;
    }

    public AgentID getSender() {
        return sender;
    }

    public void setSender(AgentID sender) {
        this.sender = sender;
    }

    public Set<AgentID> getReceiver() {
        return receiver;
    }

    public void setReceiver(Set<AgentID> receiver) {
        this.receiver = receiver;
    }

    public Set<AgentID> getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Set<AgentID> replyTo) {
        this.replyTo = replyTo;
    }

    public Act getPerformative() {
        return performative;
    }

    public void setPerformative(Act performative) {
        this.performative = performative;
    }


    public AbstractMessageBody getBody() {
        return body;
    }

    public void setBody(AbstractMessageBody body) {
        this.body =  body;
    }
}
