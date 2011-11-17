package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Rule;

@XmlType(name = "RequestWhen", namespace = "http://acts.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestWhen extends AbstractMessageBody {

    @XmlElement(required = true)
    private Action action;
    @XmlElement(required = true)
    private Rule condition;

    public RequestWhen() {
    }

    

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

// THIS METHOD SHOULD GO TO A HELPER
//    public Object[] getArguments() {
//        Object[] ans = new Object[action.getArgs().size()+2];
//        Object[] actionParams = action.getArgs().values().toArray();
//        ans[0] = action.getActionName();
//        for (int j = 0; j < actionParams.length; j++) {
//            ans[j+1] = actionParams[j];
//        }
//        ans[ans.length-1] = condition.getDrl();
//        return ans;
//    }
}
