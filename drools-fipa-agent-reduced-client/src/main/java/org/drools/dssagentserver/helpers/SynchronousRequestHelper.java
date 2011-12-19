package org.drools.dssagentserver.helpers;

import java.net.MalformedURLException;
import java.net.URL;
import org.drools.dssagentserver.SynchronousDroolsAgentServiceImpl;
import org.drools.dssagentserver.SynchronousDroolsAgentServiceImplService;
import org.drools.fipa.*;
import org.drools.fipa.body.acts.AbstractMessageBody;
import org.drools.fipa.body.acts.Inform;
import org.drools.fipa.body.acts.InformRef;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Info;
import org.drools.runtime.rule.Variable;

import java.util.LinkedHashMap;
import java.util.List;
import javax.xml.namespace.QName;

public class SynchronousRequestHelper {

    boolean multiReturnValue = false;

    private AbstractMessageBody returnBody;

    private Encodings encode = Encodings.XML;

    private URL endpointURL;
    private QName qname;

    private boolean initialized = false;

    public SynchronousRequestHelper() {

    }

    public SynchronousRequestHelper(String url) {
        this( toURL(url) );
    }

    private static URL toURL(String url) {
        try {
            return new URL( url );
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public SynchronousRequestHelper(URL url) {
        if ( url == null ) {
            System.err.println("FATAL : Creating helper with no remote endpoint ");
            return;
        }
        try {
            this.endpointURL = url;
            this.qname = new QName("http://dssagentserver.drools.org/", "SynchronousDroolsAgentServiceImplService");
            initialized = true;
        } catch (Exception e ){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public SynchronousRequestHelper( Encodings enc ) {
        encode = enc;
    }

    public boolean isInitialized() {
        return initialized;
    }


    public void invokeRequest( String methodName, LinkedHashMap<String,Object> args ) throws UnsupportedOperationException {
        if ( !initialized ) {
            System.err.println( "FATAL ANOMALY, the helper can't connect to the remote endpoint" );
            return;
        }
        invokeRequest( "", "", methodName, args );
    }

    public void invokeRequest( String sender, String receiver, String methodName, LinkedHashMap<String,Object> args ) throws UnsupportedOperationException {
        multiReturnValue = false;
        for (Object o : args.values()) {
            if ( o == Variable.v ) {
                multiReturnValue = true;
                break;
            }
        }
        SynchronousDroolsAgentServiceImpl synchronousDroolsAgentServicePort = null;
        try {
            if( this.endpointURL == null || this.qname == null ){
                synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceImplService().getSynchronousDroolsAgentServiceImplPort();
            } else{
                synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceImplService(this.endpointURL, this.qname).getSynchronousDroolsAgentServiceImplPort();
            }
        } catch ( Exception e ) {
            System.err.println( e.getMessage() );
            e.printStackTrace( System.out );
            initialized = false;
            return;
        }
        ACLMessageFactory factory = new ACLMessageFactory( encode );

        Action action = MessageContentFactory.newActionContent(methodName, args);
        ACLMessage req = factory.newRequestMessage(sender, receiver, action);

        List<ACLMessage> answers = synchronousDroolsAgentServicePort.tell(req);

        ACLMessage answer = answers.get(0);
        if ( ! Act.AGREE.equals( answer.getPerformative() ) ) {
            throw new UnsupportedOperationException(" Request " + methodName + " was not agreed with args " + args );
        }

        if ( ! multiReturnValue ) {
            returnBody = answers.size() == 2 ? ((Inform) answers.get(1).getBody()) : null;
        } else {
            returnBody = answers.size() == 2 ? ((InformRef) answers.get(1).getBody()) : null;
        }

    }


    public Object getReturn( boolean decode ) throws UnsupportedOperationException {
        if ( !initialized ) return "";
        if ( returnBody == null ) return null;
        if ( decode ) {

            MessageContentEncoder.decodeBody( returnBody, encode );
            return ( (Inform) returnBody).getProposition().getData();

        } else {

            return ( (Inform) returnBody).getProposition().getEncodedContent();

        }
    }






    public void invokeInform( Object payload ) throws UnsupportedOperationException {
        if ( !initialized ) {
            System.err.println( "FATAL ANOMALY, the helper can't connect to the remote endpoint" );
            return;
        }
        invokeInform( "", "", payload );
    }

    public void invokeInform( String sender, String receiver, Object payload ) throws UnsupportedOperationException {

        SynchronousDroolsAgentServiceImpl synchronousDroolsAgentServicePort = null;
        try {
            if( this.endpointURL == null || this.qname == null ){
                synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceImplService().getSynchronousDroolsAgentServiceImplPort();
            } else{
                synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceImplService(this.endpointURL, this.qname).getSynchronousDroolsAgentServiceImplPort();
            }
        } catch ( Exception e ) {
            System.err.println( e.getMessage() );
            e.printStackTrace( System.out );
            initialized = false;
            return;
        }
        ACLMessageFactory factory = new ACLMessageFactory( encode );

        Info info = MessageContentFactory.newInfoContent( payload );
        ACLMessage inform = factory.newInformMessage( sender, receiver, info );

        List<ACLMessage> answers = synchronousDroolsAgentServicePort.tell( inform );

    }


}