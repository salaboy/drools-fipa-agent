package org.drools.fipa.body.acts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.drools.fipa.Act;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Info;

@XmlType(name = "Reject", namespace = "http://acts.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reject extends AbstractMessageBody {

    private Action call;
    private Action proposal;
    private Info cause;

    public Reject() {
    }

    public Reject(Action call, Action proposal, Info cause) {
        this.call = call;
        this.proposal = proposal;
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "Reject{"
                + "call=" + call
                + ", proposal=" + proposal
                + ", cause=" + cause
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

        Reject reject = (Reject) o;

        if (call != null ? !call.equals(reject.call) : reject.call != null) {
            return false;
        }
        if (cause != null ? !cause.equals(reject.cause) : reject.cause != null) {
            return false;
        }
        if (proposal != null ? !proposal.equals(reject.proposal) : reject.proposal != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = call != null ? call.hashCode() : 0;
        result = 31 * result + (proposal != null ? proposal.hashCode() : 0);
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        return result;
    }

    public Action getCall() {
        return call;
    }

    public void setCall(Action call) {
        this.call = call;
    }

    public Action getProposal() {
        return proposal;
    }

    public void setProposal(Action proposal) {
        this.proposal = proposal;
    }

    public Info getCause() {
        return cause;
    }

    public void setCause(Info cause) {
        this.cause = cause;
    }
    
}
