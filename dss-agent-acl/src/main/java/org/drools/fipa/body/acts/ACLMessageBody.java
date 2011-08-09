package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Act;

/**
 * Interface for ACL Message body types, i.e. communicative acts
 */
@XmlType(name = "ACLMessageBody", namespace="http://acts.body.fipa.drools.org/")
public interface ACLMessageBody {

    // Predicate-style representation of the message body, e.g. inform( fact ), request-when( action, condition )

    // functor
    public Act getPerformative();
    public void setPerformative(Act act);
    // args
    public Object[] getArguments();



//    // String representation of the message body, serialized e.g. in JSON or XML
//    public String getEncodedContent();
//
//    public boolean isEncoded();
//
//    public void encode(Encodings enc);
//
//    public void decode(Encodings enc);



}
