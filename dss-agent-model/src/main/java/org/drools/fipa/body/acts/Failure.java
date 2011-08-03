package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Act;
import org.drools.fipa.Encodings;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Info;

@XmlType(name = "Failure", namespace = "http://acts.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Failure extends AbstractMessageBody {


    public static final Act performative = Act.FAILURE;
    public Act getPerformative() { return performative; }
    @XmlElement(required = true)
    private Action action;
    @XmlElement(required = true)
    private Info cause;

    public Failure() {
    }


    
    public Failure(Action action, Info cause) {
        this.action = action;
        this.cause = cause;
    }


    @Override
    public String toString() {
        return "Agree{" +
                "action=" + action +
                ", cause=" + cause +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Failure that = (Failure) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (cause != null ? !cause.equals(that.cause) : that.cause != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = action != null ? action.hashCode() : 0;
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        return result;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Info getCause() {
        return cause;
    }

    public void setCause(Info cause) {
        this.cause = cause;
    }

    public String getEncodedContent() {
        return action.getEncodedContent();
    }

    public boolean isEncoded() {
        return action.isEncoded();
    }

    public void encode(Encodings encoding) {
        action.encode(encoding);
        cause.encode(encoding);
    }

    public void decode(Encodings encoding) {
        action.decode(encoding);
        cause.decode(encoding);
    }

    public Object[] getArguments() {
        Object[] ans = new Object[action.getArgs().size()+2];
        Object[] actionParams = action.getArgs().values().toArray();
        ans[0] = action.getActionName();
        for (int j = 0; j < actionParams.length; j++) {
            ans[j+1] = actionParams[j];
        }
        ans[ans.length-1] = cause.getData();
        return ans;
    }
}
