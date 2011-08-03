package org.drools.fipa.body.acts;

import org.drools.fipa.Act;
import org.drools.fipa.Encodings;

/**
 * Interface for ACL Message body types, i.e. communicative acts
 */

public interface ACLMessageBody {

    // Predicate-style representation of the message body, e.g. inform( fact ), request-when( action, condition )

    // functor
    public Act getPerformative();
    // args
    public Object[] getArguments();



    // String representation of the message body, serialized e.g. in JSON or XML
    public String getEncodedContent();

    public boolean isEncoded();

    public void encode(Encodings enc);

    public void decode(Encodings enc);



}
