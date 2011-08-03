package org.drools.fipa.body.acts;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AbstractMessageBody", namespace="http://acts.body.fipa.drools.org/")
public abstract class AbstractMessageBody implements ACLMessageBody, Serializable {

}
