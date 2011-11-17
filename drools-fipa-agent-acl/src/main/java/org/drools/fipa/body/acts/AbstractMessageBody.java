package org.drools.fipa.body.acts;

import java.io.Serializable;
import javax.xml.bind.annotation.*;
import org.drools.fipa.Act;

@XmlType(name = "AbstractMessageBody", namespace = "http://acts.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
    Inform.class,
    RequestWhen.class,
    InformIf.class,
    InformRef.class,
    Failure.class,
    Request.class,
    Agree.class,
    QueryRef.class,
    QueryIf.class
})
public abstract class AbstractMessageBody implements Serializable {

    @XmlElement(required = true)
    public Act performative;
    // functor

    public void setPerformative(Act performative) {
        this.performative = performative;
    }

    public Act getPerformative() {
        return performative;
    }
    // args

//    public abstract Object[] getArguments();
    
    
}
