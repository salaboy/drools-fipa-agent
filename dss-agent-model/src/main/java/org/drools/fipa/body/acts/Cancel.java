package org.drools.fipa.body.acts;

import org.drools.fipa.Act;
import org.drools.fipa.Encodings;
import org.drools.fipa.body.content.Action;


public class Cancel extends AbstractMessageBody {


    public static final Act performative = Act.CANCEL;
    public Act getPerformative() { return performative; }

    private Action action;

    public Cancel() {
    }


    
    
    public Cancel(Action action) {
        this.action = action;
    }


    @Override
    public String toString() {
        return "Cancel{" +
                "action=" + action +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cancel that = (Cancel) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return action != null ? action.hashCode() : 0;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getEncodedContent() {
        return action.getEncodedContent();
    }

    public boolean isEncoded() {
        return action.isEncoded();
    }

    public void encode(Encodings encoding) {
        action.encode(encoding);
    }

    public void decode(Encodings encoding) {
        action.decode(encoding);
    }

   public Object[] getArguments() {
        Object[] ans = new Object[action.getArgs().size()+1];
        Object[] actionParams = action.getArgs().values().toArray();
        ans[0] = action.getActionName();
        for (int j = 0; j < actionParams.length; j++) {
            ans[j+1] = actionParams[j];
        }
        return ans;
    }

}
