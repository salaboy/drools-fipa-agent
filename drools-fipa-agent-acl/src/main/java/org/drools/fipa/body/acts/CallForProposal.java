package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Act;
import org.drools.fipa.Encodings;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Rule;

@XmlType(name = "CallForProposal", namespace = "http://acts.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CallForProposal extends AbstractMessageBody {

    private Action action;
    private Rule condition;

    public CallForProposal() {
    }

    public CallForProposal(Action action, Rule condition) {
        this.action = action;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "CallForProposal{"
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

        CallForProposal that = (CallForProposal) o;

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
