package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Act;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Rule;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcceptProposal", namespace = "http://acts.body.fipa.drools.org/")
public class AcceptProposal extends AbstractMessageBody {

    private Action action;
    private Rule condition;
    
    public AcceptProposal() {
    }

    public AcceptProposal(Action action, Rule condition) {
        this.action = action;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "AcceptProposal{"
                + "action=" + action
                + ", condition=" + condition
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AcceptProposal that = (AcceptProposal) o;

        if (action != null ? !action.equals(that.action) : that.action != null) {
            return false;
        }
        if (condition != null ? !condition.equals(that.condition) : that.condition != null) {
            return false;
        }

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
 
}
