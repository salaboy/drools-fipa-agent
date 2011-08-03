package org.drools.fipa;

import org.drools.fipa.body.acts.*;
import org.drools.fipa.body.content.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.drools.fipa.ACLMessage;
import org.drools.fipa.AgentID;
import org.drools.fipa.Encodings;


/**

 ACL Message factory
 Build messages corresponding to any standard communication act, as defined by the FIPA standard.

 accept-proposal( Action action, Rule condition )
 agree( Action action, Rule condition )
 cancel( Action action )
 call-for-proposal( Action action, Rule precondition )
 confirm( Object proposition )
 disconfirm( Object proposition )
 failure( Action action, Object cause )
 inform( Object proposition )
 inform-if( Object proposition )
 inform-ref( Map references )
 not-understood( Object act, Object cause )
 propagate( Identity[] targets, ACLMesasge message, Rule condition )
 propose( Action act, Rule precondition )
 proxy( Identity[] targets, ACLMesasge message, Rule condition )
 query-if( Object proposition )
 query-ref( Query query )
 refuse( Action act, Object cause )
 reject-proposal( Action call, Action proposal, Object cause )
 request( Action action )
 request-when( Action action, Rule condition )
 request-whenever( Action action, Rule condition )
 subscribe( Query query )


 Message content is encoded in string format, either XML or JSON
 */
public class ACLMessageFactory implements Serializable {


    private static long idCounter = 0;
    private static long convoCounter = 0;
    private String newId() { return "" + (idCounter++); }
    private String newConversationId() { return "" + (convoCounter++); }



    private Encodings defaultEncoding = Encodings.XML;
    public Encodings getDefaultEncoding() { return defaultEncoding; }
    public void setDefaultEncoding(Encodings defaultEncoding) { this.defaultEncoding = defaultEncoding; }


    public ACLMessageFactory(Encodings defEncoding) {
        this.setDefaultEncoding(defEncoding);
    }



    public ACLMessage newMessage() {
        return new ACLMessage(newId());
    }

    protected ACLMessage newMessage(String sender, String receiver) {

        ACLMessage msg = new ACLMessage(newId());
            msg.setConversationId(newConversationId());
            msg.setSender(new AgentID(sender));

            Set<AgentID> recSet = new HashSet<AgentID>();
                recSet.add(new AgentID(receiver));
            msg.setReceiver(recSet);
            msg.setEncoding(getDefaultEncoding());

        return msg;
    }


    protected ACLMessage createReply(ACLMessage inMsg, AgentID sender) {

        ACLMessage msg = new ACLMessage(newId());
            msg.setEncoding(inMsg.getEncoding());
            msg.setSender(sender);

            Set<AgentID> recSet = new HashSet<AgentID>();
                recSet.add(inMsg.getSender());
            msg.setReceiver(recSet);

            msg.setConversationId(inMsg.getConversationId());
            msg.setInReplyTo(inMsg.getId());

        return msg;
    }


    private boolean setMessageBody(ACLMessage msg, ACLMessageBody body) {

        msg.setPerformative(body.getPerformative());
        if (getDefaultEncoding() != Encodings.NONE)
            body.encode(getDefaultEncoding());
        msg.setBody((AbstractMessageBody)body);

        return true;
    }


    public ACLMessage newAcceptProposalMessage(String sender, String receiver, Action action, Rule condition) {
        ACLMessage msg = newMessage(sender,receiver);
            AcceptProposal body = new AcceptProposal(action,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newAgreeMessage(String sender, String receiver, Action action, Rule condition) {
        ACLMessage msg = newMessage(sender,receiver);
            Agree body = new Agree(action,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newCancelMessage(String sender, String receiver, Action action) {
        ACLMessage msg = newMessage(sender,receiver);
            Cancel body = new Cancel(action);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newCallForProposalMessage(String sender, String receiver, Action action, Rule condition) {
        ACLMessage msg = newMessage(sender,receiver);
            CallForProposal body = new CallForProposal(action,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newConfirmMessage(String sender, String receiver, Object proposition) {
        ACLMessage msg = newMessage(sender,receiver);
            Confirm body = new Confirm(new Info(proposition));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newDisconfirmMessage(String sender, String receiver, Object proposition) {
        ACLMessage msg = newMessage(sender,receiver);
            Disconfirm body = new Disconfirm(new Info(proposition));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newFailureMessage(String sender, String receiver, Action action, Object cause) {
        ACLMessage msg = newMessage(sender,receiver);
            Failure body = new Failure(action,new Info(cause));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newInformMessage(String sender, String receiver, Object proposition) {
        ACLMessage msg = newMessage(sender,receiver);
            Inform body = new Inform(new Info(proposition));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newInformIfMessage(String sender, String receiver, Object proposition) {
        ACLMessage msg = newMessage(sender,receiver);
            InformIf body = new InformIf(new Info(proposition));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newInformRefMessage(String sender, String receiver, Ref references) {
        ACLMessage msg = newMessage(sender,receiver);
            InformRef body = new InformRef(references);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newNotUnderstoodMessage(String sender, String receiver, Action action, Object cause) {
        ACLMessage msg = newMessage(sender,receiver);
            NotUnderstood body = new NotUnderstood(action,new Info(cause));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newPropagateMessage(String sender, String receiver, AgentID[] targets, ACLMessage innerMsg, Rule condition) {
        ACLMessage msg = newMessage(sender,receiver);
            Propagate body = new Propagate(targets,innerMsg,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newProposeMessage(String sender, String receiver, Action action, Rule precondition) {
        ACLMessage msg = newMessage(sender,receiver);
            Propose body = new Propose(action,precondition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newProxyMessage(String sender, String receiver, AgentID[] targets, ACLMessage innerMsg, Rule condition) {
        ACLMessage msg = newMessage(sender,receiver);
            Proxy body = new Proxy(targets,innerMsg,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newQueryIfMessage(String sender, String receiver, Object proposition) {
        ACLMessage msg = newMessage(sender,receiver);
            QueryIf body = new QueryIf(new Info(proposition));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newQueryRefMessage(String sender, String receiver, Query query) {
        ACLMessage msg = newMessage(sender,receiver);
            QueryRef body = new QueryRef(query);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newRefuseMessage(String sender, String receiver, Action action, Object cause) {
        ACLMessage msg = newMessage(sender,receiver);
            Refuse body = new Refuse(action,new Info(cause));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newRejectProposalMessage(String sender, String receiver, Action call, Action proposal, Object cause) {
        ACLMessage msg = newMessage(sender,receiver);
            Reject body = new Reject(call,proposal,new Info(cause));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newRequestMessage(String sender, String receiver, Action action) {
        ACLMessage msg = newMessage(sender,receiver);
            Request body = new Request(action);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newRequestWhenMessage(String sender, String receiver, Action action, Rule condition) {
        ACLMessage msg = newMessage(sender,receiver);
            RequestWhen body = new RequestWhen(action,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newRequestWheneverMessage(String sender, String receiver, Action action, Rule condition) {
        ACLMessage msg = newMessage(sender,receiver);
            RequestWhenever body = new RequestWhenever(action,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newSubscribeMessage(String sender, String receiver, Query query) {
        ACLMessage msg = newMessage(sender,receiver);
            Subscribe body = new Subscribe(query);
            setMessageBody(msg,body);
        return msg;
    }











    public ACLMessage newReplyWithAcceptProposalMessage(ACLMessage origin, AgentID sender, Action action, Rule condition) {
        ACLMessage msg = createReply(origin,sender);
            AcceptProposal body = new AcceptProposal(action,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithAgreeMessage(ACLMessage origin, AgentID sender, Action action, Rule condition) {
        ACLMessage msg = createReply(origin,sender);
            Agree body = new Agree(action,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithCancelMessage(ACLMessage origin, AgentID sender, Action action) {
        ACLMessage msg = createReply(origin,sender);
            Cancel body = new Cancel(action);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithCallForProposalMessage(ACLMessage origin, AgentID sender, Action action, Rule condition) {
        ACLMessage msg = createReply(origin,sender);
            CallForProposal body = new CallForProposal(action,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithConfirmMessage(ACLMessage origin, AgentID sender, Object proposition) {
        ACLMessage msg = createReply(origin,sender);
            Confirm body = new Confirm(new Info(proposition));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithDisconfirmMessage(ACLMessage origin, AgentID sender, Object proposition) {
        ACLMessage msg = createReply(origin,sender);
            Disconfirm body = new Disconfirm(new Info(proposition));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithFailureMessage(ACLMessage origin, AgentID sender, Action action, Object cause) {
        ACLMessage msg = createReply(origin,sender);
            Failure body = new Failure(action,new Info(cause));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithInformMessage(ACLMessage origin, AgentID sender, Object proposition) {
        ACLMessage msg = createReply(origin,sender);
            Inform body = new Inform(new Info(proposition));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithInformIfMessage(ACLMessage origin, AgentID sender, Object proposition) {
        ACLMessage msg = createReply(origin,sender);
            InformIf body = new InformIf(new Info(proposition));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithInformRefMessage(ACLMessage origin, AgentID sender, Ref references) {
        ACLMessage msg = createReply(origin,sender);
            InformRef body = new InformRef(references);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithNotUnderstoodMessage(ACLMessage origin, AgentID sender, Action action, Object cause) {
        ACLMessage msg = createReply(origin,sender);
            NotUnderstood body = new NotUnderstood(action,new Info(cause));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithPropagateMessage(ACLMessage origin, AgentID sender, AgentID[] targets, ACLMessage innerMsg, Rule condition) {
        ACLMessage msg = createReply(origin,sender);
            Propagate body = new Propagate(targets,innerMsg,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithProposeMessage(ACLMessage origin, AgentID sender, Action action, Rule precondition) {
        ACLMessage msg = createReply(origin,sender);
            Propose body = new Propose(action,precondition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithProxyMessage(ACLMessage origin, AgentID sender, AgentID[] targets, ACLMessage innerMsg, Rule condition) {
        ACLMessage msg = createReply(origin,sender);
            Proxy body = new Proxy(targets,innerMsg,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithQueryIfMessage(ACLMessage origin, AgentID sender, Object proposition) {
        ACLMessage msg = createReply(origin,sender);
            QueryIf body = new QueryIf(new Info(proposition));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithQueryRefMessage(ACLMessage origin, AgentID sender, Query query) {
        ACLMessage msg = createReply(origin,sender);
            QueryRef body = new QueryRef(query);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithRefuseMessage(ACLMessage origin, AgentID sender, Action action, Object cause) {
        ACLMessage msg = createReply(origin,sender);
            Refuse body = new Refuse(action,new Info(cause));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithRejectProposalMessage(ACLMessage origin, AgentID sender, Action call, Action proposal, Object cause) {
        ACLMessage msg = createReply(origin,sender);
            Reject body = new Reject(call,proposal,new Info(cause));
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithRequestMessage(ACLMessage origin, AgentID sender, Action action) {
        ACLMessage msg = createReply(origin,sender);
            Request body = new Request(action);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithRequestWhenMessage(ACLMessage origin, AgentID sender, Action action, Rule condition) {
        ACLMessage msg = createReply(origin,sender);
            RequestWhen body = new RequestWhen(action,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithRequestWheneverMessage(ACLMessage origin, AgentID sender, Action action, Rule condition) {
        ACLMessage msg = createReply(origin,sender);
            RequestWhenever body = new RequestWhenever(action,condition);
            setMessageBody(msg,body);
        return msg;
    }

    public ACLMessage newReplyWithSubscribeMessage(ACLMessage origin, AgentID sender, Query query) {
        ACLMessage msg = createReply(origin,sender);
            Subscribe body = new Subscribe(query);
            setMessageBody(msg,body);
        return msg;
    }















}



