package org.drools.fipa.body.content;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import org.drools.fipa.ACLMessageFactory;

import java.io.*;

/**
 * Actual mesasge content, i.e. the object of an ACL communicative act.
 */
public abstract class AbstractMessageContent implements IEncodable, Serializable {



    public static NamedVariable variable(String ref) {
        return new NamedVariable(ref);
    }



    //TODO: Use provider interfaces to decouple
    private static XStream xmlConverter;
    private static XStream jsonConverter;
    private static Gson gsonConverter;

    private String encodedContent;
    private boolean encoded;

    public String getEncodedContent() {
        return encodedContent;
    }

    public void setEncodedContent(String encodedContent) {
        this.encodedContent = encodedContent;
    }

    public boolean isEncoded() {
        return encoded;
    }

    public void setEncoded(boolean encoded) {
        this.encoded = encoded;
    }

    protected String encode(Object obj, ACLMessageFactory.Encodings encoding)  {

        switch (encoding) {
            case BYTE :
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(obj);
                    oos.flush();
                    oos.close();
                    bos.close();
                    return new String(bos.toByteArray());
                } catch (IOException ioe) {
                    return null;
                }
            case JSON :
                String bodyString = getJsonConverter().toXML(obj);
                //TODO : Check - bug in XStream ?? Class name has one \" too much
                bodyString = bodyString.replaceAll("\"\"","\"");
                return bodyString;
            case GSON :
                return getGsonConverter().toJson(obj);
            case XML  :
                return getXmlConverter().toXML(obj);
            default :
                return null;
        }
    }

    private XStream getJsonConverter() {
        if (jsonConverter == null) {
            jsonConverter = new XStream(new JettisonMappedXmlDriver());
        }
        return jsonConverter;
    }

    private Gson getGsonConverter() {
        if (gsonConverter == null) {
            gsonConverter = new Gson();
        }
        return gsonConverter;
    }

    protected XStream getXmlConverter() {
        if (xmlConverter == null) {
            xmlConverter = new XStream();
        }
        return xmlConverter;
    }


    protected Object decodeContent(String encodedContent, ACLMessageFactory.Encodings encoding)  {

        switch (encoding) {
            case BYTE :
                try {
                    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(encodedContent.getBytes()));
                    return ois.readObject();
                } catch (Exception e) {
                    return null;
                }
            case JSON :

                return getJsonConverter().fromXML(encodedContent);
            case XML  :
            default   :
                return getXmlConverter().fromXML(encodedContent);
        }
    }





}
