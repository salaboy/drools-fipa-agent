package org.drools.fipa.body.content;

import org.drools.fipa.Encodings;

/**
 * Created by IntelliJ IDEA.
 * Date: 5/9/11
 * Time: 2:00 AM
 */
public interface IEncodable {

    public void encode(Encodings encoding);

    public void decode(Encodings encoding);

    public String getEncodedContent();

}
