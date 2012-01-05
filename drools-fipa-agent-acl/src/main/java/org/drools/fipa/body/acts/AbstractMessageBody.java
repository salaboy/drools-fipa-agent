package org.drools.fipa.body.acts;



import java.io.Serializable;
import javax.xml.bind.annotation.*;

import org.drools.fipa.Act;
import org.drools.fipa.Encodings;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Info;
import org.drools.fipa.body.content.Query;
import org.drools.fipa.body.content.Ref;
import org.drools.fipa.body.content.Rule;
import org.drools.fipa.mappers.MyMapArgsEntryType;
import org.drools.fipa.mappers.MyMapReferenceEntryType;

@XmlSeeAlso(value={ Inform.class, QueryIf.class, InformIf.class, 
                    Agree.class, Failure.class, Action.class, Rule.class, 
                    QueryRef.class, Query.class, InformRef.class, Info.class,
                    Act.class, Encodings.class,
                    Ref.class, InformRef.class, Request.class, RequestWhen.class,
                    MyMapReferenceEntryType.class, MyMapArgsEntryType.class})
@XmlType(name = "AbstractMessageBody", namespace = "http://acts.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractMessageBody implements Serializable {
    @XmlElement(required = true)
    public Act performative;
    
    
    public AbstractMessageBody() {
    }

    public Act getPerformative() {
        return performative;
    }

    public void setPerformative(Act performative) {
        this.performative = performative;
    }
    
    
    
}
