package org.drools.fipa.body.acts;

import org.drools.fipa.ACLMessage;
import org.drools.fipa.ACLMessageFactory;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Rule;

/**
 * Created by IntelliJ IDEA.
 * Date: 5/7/11
 * Time: 8:03 PM
 */
public class RequestWhen extends AbstractMessageBody {


    public static final ACLMessage.Act performative = ACLMessage.Act.REQUEST_WHEN;
    public ACLMessage.Act getPerformative() { return performative; }

    private Action action;
    private Rule condition;


    public RequestWhen(Action action, Rule condition) {
        this.action = action;
        this.condition = condition;
    }


    @Override
    public String toString() {
        return "RequestWhen{" +
                "action=" + action +
                ", condition=" + condition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestWhen that = (RequestWhen) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (condition != null ? !condition.equals(that.condition) : that.condition != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = action != null ? action.hashCode() : 0;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Rule getCondition() {
        return condition;
    }

    public void setCondition(Rule condition) {
        this.condition = condition;
    }


    public String getEncodedContent() {
        return action.getEncodedContent();
    }

    public boolean isEncoded() {
        return action.isEncoded();
    }

    public void encode(ACLMessageFactory.Encodings encoding) {
        action.encode(encoding);
        condition.encode(encoding);
    }

    public void decode(ACLMessageFactory.Encodings encoding) {
        action.decode(encoding);
        condition.decode(encoding);
    }

    public Object[] getArguments() {
        Object[] ans = new Object[action.getArgs().size()+2];
        Object[] actionParams = action.getArgs().values().toArray();
        ans[0] = action.getActionName();
        for (int j = 0; j < actionParams.length; j++) {
            ans[j+1] = actionParams[j];
        }
        ans[ans.length-1] = condition.getDrl();
        return ans;
    }
}