package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.body.content.Action;

@XmlType(name = "Request", namespace = "http://acts.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Request extends AbstractMessageBody {

    @XmlElement(required = true)
    private Action action;

    public Request() {
    }

    
//    public Request(Action action) {
//        this.action = action;
//    }

   
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
