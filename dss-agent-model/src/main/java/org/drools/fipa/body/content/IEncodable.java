package org.drools.fipa.body.content;

import org.drools.fipa.ACLMessageFactory;

/**
 * Created by IntelliJ IDEA.
 * Date: 5/9/11
 * Time: 2:00 AM
 */
public interface IEncodable {

    public void encode(ACLMessageFactory.Encodings encoding);

    public void decode(ACLMessageFactory.Encodings encoding);

    public String getEncodedContent();

}
