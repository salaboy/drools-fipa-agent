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
public class Reject extends AbstractMessageBody {


    public static final ACLMessage.Act performative = ACLMessage.Act.REJECT;
    public ACLMessage.Act getPerformative() { return performative; }

    private Action call;
    private Action proposal;
    private Info cause;


    public Reject(Action call, Action proposal, Info cause) {
        this.call = call;
        this.proposal = proposal;
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "Reject{" +
                "call=" + call +
                ", proposal=" + proposal +
                ", cause=" + cause +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reject reject = (Reject) o;

        if (call != null ? !call.equals(reject.call) : reject.call != null) return false;
        if (cause != null ? !cause.equals(reject.cause) : reject.cause != null) return false;
        if (proposal != null ? !proposal.equals(reject.proposal) : reject.proposal != null) return false;

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


    public String getEncodedContent() {
        return proposal.getEncodedContent();
    }

    public boolean isEncoded() {
        return proposal.isEncoded();
    }

    public void encode(ACLMessageFactory.Encodings encoding) {
        proposal.encode(encoding);
        cause.encode(encoding);
        call.encode(encoding);
    }

    public void decode(ACLMessageFactory.Encodings encoding) {
        proposal.decode(encoding);
        cause.decode(encoding);
        call.decode(encoding);
    }


   public Object[] getArguments() {

        Object[] ans = new Object[proposal.getArgs().size() + call.getArgs().size() + 3];

        Object[] proposalParams = proposal.getArgs().values().toArray();
        Object[] callParams = call.getArgs().values().toArray();

        ans[0] = call.getActionName();
        for (int j = 0; j < callParams.length; j++) {
            ans[j+1] = callParams[j];
        }
        ans[callParams.length + 1] = proposal.getActionName();
        for (int j = 0; j < proposalParams.length; j++) {
            ans[j+1] = callParams[j];
        }


        ans[ans.length-1] = cause.getData();
        return ans;
    }


}
