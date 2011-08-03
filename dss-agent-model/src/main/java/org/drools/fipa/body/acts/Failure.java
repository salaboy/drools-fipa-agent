package org.drools.fipa.body.acts;

import org.drools.fipa.ACLMessage;
import org.drools.fipa.ACLMessageFactory;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Info;

/**
 * Created by IntelliJ IDEA.
 * Date: 5/7/11
 * Time: 8:03 PM
 */
public class Failure extends AbstractMessageBody {


    public static final ACLMessage.Act performative = ACLMessage.Act.FAILURE;
    public ACLMessage.Act getPerformative() { return performative; }

    private Action action;
    private Info cause;


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

    public void encode(ACLMessageFactory.Encodings encoding) {
        action.encode(encoding);
        cause.encode(encoding);
    }

    public void decode(ACLMessageFactory.Encodings encoding) {
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