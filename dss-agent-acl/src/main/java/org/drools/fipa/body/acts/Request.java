package org.drools.fipa.body.acts;

import org.drools.fipa.Act;
import org.drools.fipa.Encodings;
import org.drools.fipa.body.content.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 5/7/11
 * Time: 8:03 PM
 */
public class Request extends AbstractMessageBody {


   
    
    
    private Action action;

    public Request() {
    }

    
    public Request(Action action) {
        this.action = action;
    }

   
    @Override
    public String toString() {
        return "Request{" +
                "action=" + action +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request that = (Request) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = action != null ? action.hashCode() : 0;
        return result;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
// THIS METHOD SHOULD GO TO A HELPER
//    public Object[] getArguments() {
//        Object[] ans = new Object[action.getArgs().size()+2];
//        Object[] actionParams = action.getArgs().values().toArray();
//        ans[0] = action.getActionName();
//        for (int j = 0; j < actionParams.length; j++) {
//            ans[j+1] = actionParams[j];
//        }
//        return ans;
//    }


}
