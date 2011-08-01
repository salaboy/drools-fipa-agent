package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlRootElement;
import org.drools.fipa.ACLMessage;
import org.drools.fipa.ACLMessageFactory;

/**
 * Interface for ACL Message body types, i.e. communicative acts
 */

public interface ACLMessageBody {

    // Predicate-style representation of the message body, e.g. inform( fact ), request-when( action, condition )

    // functor
    public ACLMessage.Act getPerformative();
    // args
    public Object[] getArguments();



    // String representation of the message body, serialized e.g. in JSON or XML
    public String getEncodedContent();

    public boolean isEncoded();

    public void encode(ACLMessageFactory.Encodings enc);

    public void decode(ACLMessageFactory.Encodings enc);



}
