/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa;

import java.util.ArrayList;
import org.drools.fipa.body.content.Query;
import org.drools.fipa.body.acts.InformIf;
import mock.MockFact;
import org.drools.dssagentserver.SynchronousDroolsAgentServiceImpl;
import org.drools.dssagentserver.SynchronousDroolsAgentServiceImplService;

import org.drools.fipa.body.acts.Inform;
import org.drools.fipa.body.acts.QueryIf;
import org.drools.fipa.body.content.Info;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salaboy
 */
public class SynchronousDroolsAgentServiceServiceTest {

    public SynchronousDroolsAgentServiceServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    //The following test simulate the following ACLMessage:
    //    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
    //   <soapenv:Header/>
    //   <soapenv:Body>
    //      <message>
    //	<org.drools.fipa.ACLMessage>
    //  <id>0</id>
    //  <messageType>DEFAULT_FIPA_MESSAGE_TYPE</messageType>
    //  <conversationId>0</conversationId>
    //  <replyBy>0</replyBy>
    //  <ontology>KMR2</ontology>
    //  <language>DROOLS_DRL</language>
    //  <encoding>JSON</encoding>
    //  <sender>
    //    <name>me@org.DROOLS</name>
    //  </sender>
    //  <receiver>
    //    <org.drools.fipa.AgentID>
    //      <name>you@org.DROOLS</name>
    //    </org.drools.fipa.AgentID>
    //  </receiver>
    //  <performative>INFORM</performative>
    //  <body class="org.drools.fipa.body.acts.Inform">
    //    <proposition>
    //      <encodedContent>{&quot;org.kmr2.mock.MockFact&quot;:{&quot;name&quot;:&quot;patient1&quot;,&quot;age&quot;:18}}</encodedContent>
    //      <encoded>true</encoded>
    //    </proposition>
    //  </body>
    //</org.drools.fipa.ACLMessage>
    //      </message>
    //   </soapenv:Body>
    //</soapenv:Envelope>
    @Test
    public void hello() {
        SynchronousDroolsAgentServiceImpl synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceImplService().getSynchronousDroolsAgentServiceImplPort();

        ACLMessage informMessage = new ACLMessage();
        informMessage.setId("0");
        informMessage.setPerformative(Act.INFORM);
        informMessage.setMessageType("DEFAULT_FIPA_MESSAGE_TYPE");
        informMessage.setConversationId("0");
        informMessage.setReplyBy(0);
        informMessage.setOntology("KMR2");
        informMessage.setLanguage("DROOLS_DRL");
        informMessage.setEncoding(Encodings.JSON);
        AgentID sender = new AgentID();
        sender.setName("me@org.DROOLS");
        informMessage.setSender(sender);

        Inform inform = new Inform();
        inform.setPerformative(Act.INFORM);
        Info info = new Info();
        info.setEncodedContent("{\"mock.MockFact\":{\"name\":\"patient1\",\"age\":18}}");
        info.setEncoded(true);
        info.setEncoding(Encodings.JSON);
        inform.setProposition(info);
        informMessage.setBody(inform);

        AgentID receiver = new AgentID();
        receiver.setName("you@org.DROOLS");

        List<AgentID> receivers = informMessage.getReceiver();
        receivers.add(receiver);

        List<ACLMessage> answers = synchronousDroolsAgentServicePort.tell(informMessage);

        assertNotNull(answers);
        assertEquals(0, answers.size());

        ACLMessage queryIfMessage = new ACLMessage();
        queryIfMessage.setId("1");
        queryIfMessage.setPerformative(Act.QUERY_IF);
        queryIfMessage.setMessageType("DEFAULT_FIPA_MESSAGE_TYPE");
        queryIfMessage.setConversationId("1");
        queryIfMessage.setReplyBy(0);
        queryIfMessage.setOntology("KMR2");
        queryIfMessage.setLanguage("DROOLS_DRL");
        queryIfMessage.setEncoding(Encodings.JSON);

        //I'm using the same sender
        queryIfMessage.setSender(sender);

        QueryIf queryIf = new QueryIf();
        queryIf.setPerformative(Act.QUERY_IF);
        info = new Info();

        info.setEncodedContent("{\"mock.MockFact\":{\"name\":\"patient1\",\"age\":18}}");
        info.setEncoded(true);
        info.setEncoding(Encodings.JSON);
        queryIf.setProposition(info);
        queryIfMessage.setBody(queryIf);

        //I'm using the same receiver
        receivers = queryIfMessage.getReceiver();
        receivers.add(receiver);
        answers = synchronousDroolsAgentServicePort.tell(queryIfMessage);



        assertNotNull(answers);
        assertEquals(1, answers.size());


    }

    @Test
    public void testSimpleInform() {
        SynchronousDroolsAgentServiceImpl synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceImplService().getSynchronousDroolsAgentServiceImplPort();
        MockFact fact = new MockFact("patient1", 18);
        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);

        ACLMessage info = factory.newInformMessage("me", "you", fact);

        List<ACLMessage> answers = synchronousDroolsAgentServicePort.tell(info);

        assertNotNull(answers);
        assertEquals(0, answers.size());
//        assertNull(mainResponseInformer.getResponses(info));
//        StatefulKnowledgeSession target = mainAgent.getInnerSession("session1");
//        assertTrue(target.getObjects().contains(fact));

    }

    @Test
    public void testInformAsTrigger() {
        MockFact fact = new MockFact("patient1", 22);
        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);


        SynchronousDroolsAgentServiceImpl synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceImplService().getSynchronousDroolsAgentServiceImplPort();
        ACLMessage info = factory.newInformMessage("me", "you", fact);
        List<ACLMessage> answers = synchronousDroolsAgentServicePort.tell(info);

        assertNotNull(answers);
        assertEquals(0, answers.size());

        
//        assertNull(mainResponseInformer.getResponses(info));
//        StatefulKnowledgeSession target = mainAgent.getInnerSession("session2");
//        for (Object o : target.getObjects())
//            System.err.println("\t Inform-Trigger test : " + o);
//        assertTrue(target.getObjects().contains(new Double(22.0)));
//        assertTrue(target.getObjects().contains(new Integer(484)));
    }

    @Test
    public void testQueryIf() {
        SynchronousDroolsAgentServiceImpl synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceImplService().getSynchronousDroolsAgentServiceImplPort();
        MockFact fact = new MockFact("patient1", 18);
        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);

        ACLMessage info = factory.newInformMessage("me", "you", fact);
        List<ACLMessage> answers = synchronousDroolsAgentServicePort.tell(info);
        assertEquals(0, answers.size());
        ACLMessage qryif = factory.newQueryIfMessage("me", "you", fact);
        answers = synchronousDroolsAgentServicePort.tell(qryif);
        assertEquals(1, answers.size());

        ACLMessage answer = answers.get(0);
        MessageContentEncoder.decodeBody(answer.getBody(), answer.getEncoding());
        assertEquals(Act.INFORM_IF, answer.getPerformative());
        assertEquals(((InformIf) answer.getBody()).getProposition().getData(), fact);

    }
     @Test
    public void testQueryRef() {
        SynchronousDroolsAgentServiceImpl synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceImplService().getSynchronousDroolsAgentServiceImplPort(); 
        MockFact fact = new MockFact("patient1",18);
        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);

        ACLMessage info = factory.newInformMessage("me","you",fact);
            synchronousDroolsAgentServicePort.tell(info);
        Query query = MessageContentFactory.newQueryContent("ageOfPatient", new Object[] {MessageContentHelper.variable("?mock"), "patient1", MessageContentHelper.variable("?age")} );
        ACLMessage qryref = factory.newQueryRefMessage("me","you",query);
        List<ACLMessage> answers = synchronousDroolsAgentServicePort.tell(qryref);

        assertNotNull(answers);
        assertEquals(2,answers.size());

        ACLMessage answer = answers.get(0);
        assertEquals(Act.AGREE,answer.getPerformative());
        ACLMessage answer2 = answers.get(1);
        assertEquals(Act.INFORM_REF,answer2.getPerformative());
        
    }
//
//
//
//
//
//
//    @Ignore
//    public void testRequest() {
//
//        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);
//
//        Map<String,Object> args = new LinkedHashMap<String,Object>();
//        args.put("x",new Double(36));
//
//
//        ACLMessage req = factory.newRequestMessage("me","you",new Action("squareRoot", args));
//
//
//
//        mainAgent.tell(req);
//
//        assertNotNull(mainResponseInformer.getResponses(req));
//        assertEquals(2,mainResponseInformer.getResponses(req).size());
//
//        ACLMessage answer = mainResponseInformer.getResponses(req).get(0);
//        assertEquals(Act.AGREE,answer.getPerformative());
//        ACLMessage answer2 = mainResponseInformer.getResponses(req).get(1);
//        assertEquals(Act.INFORM,answer2.getPerformative());
//
//     //   assertTrue(answer2.getBody().getEncodedContent().contains("6.0"));
//
//    }
//
//
//
//
// 
//    @Ignore
//    public void testRequestWhen() {
//
//        Double in = new Double(36);
//
//        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);
//
//        Map<String,Object> args = new LinkedHashMap<String,Object>();
//        args.put("x", in);
//
//
//        Rule condition = new Rule("String( this == \"actionTrigger\" || this == \"actionTrigger2\")");
//
//        ACLMessage req = factory.newRequestWhenMessage("me", "you", new Action("squareRoot", args), condition);
//        mainAgent.tell(req);
//
//        ACLMessage info = factory.newInformMessage("me","you",new String("actionTrigger"));
//        mainAgent.tell(info);
//
//
//        ACLMessage info2 = factory.newInformMessage("me","you",new String("actionTrigger2"));
//        mainAgent.tell(info2);
//
//
//        StatefulKnowledgeSession s2 = mainAgent.getInnerSession("session2");
//        QueryResults ans = s2.getQueryResults("squareRoot", in, Variable.v);
//        assertEquals(1, ans.size());
//        assertEquals(6.0, (Double) ans.iterator().next().get("$return"), 1e-6);
//
//
//
//
//    }
//
//
//
//
//
//    
//    @Ignore
//    public void testRequestWhenever() {
//
//        Double in = new Double(36);
//
//        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);
//
//        Map<String,Object> args = new LinkedHashMap<String,Object>();
//        args.put("x", in);
//
//
//        Rule condition = new Rule("String( this == \"actionTrigger\" || this == \"actionTrigger2\")");
//
//        ACLMessage req = factory.newRequestWheneverMessage("me", "you", new Action("squareRoot", args), condition);
//        mainAgent.tell(req);
//
//        ACLMessage info = factory.newInformMessage("me","you",new String("actionTrigger"));
//        mainAgent.tell(info);
//
//
//        ACLMessage info2 = factory.newInformMessage("me","you",new String("actionTrigger2"));
//        mainAgent.tell(info2);
//
//
//        StatefulKnowledgeSession s2 = mainAgent.getInnerSession("session2");
//        QueryResults ans = s2.getQueryResults("squareRoot", in, Variable.v);
//        assertEquals(2, ans.size());
//        Iterator<QueryResultsRow> iter = ans.iterator();
//        assertEquals(6.0,(Double) iter.next().get("$return"),1e-6);
//        assertEquals(6.0,(Double) iter.next().get("$return"),1e-6);
//
//
//        fail("INCOMPLETE TEST : Needs open queries to send answer back with a message, but keep trigger rule!");
//
//    }
//
//
//
//
//
//
//
//    @Ignore
//    public void testRequestWithMultipleOutputs() {
//
//        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);
//
//        Map<String,Object> args = new LinkedHashMap<String,Object>();
//        Double x = 32.0;
//
//        args.put("x",x);
//        args.put("?y",Variable.v);
//        args.put("?inc",Variable.v);
//
//
//
//        ACLMessage req = factory.newRequestMessage("me","you",new Action("randomSum", args));
//
//
//
//        mainAgent.tell(req);
//
//        assertNotNull(mainResponseInformer.getResponses(req));
//        assertEquals(2,mainResponseInformer.getResponses(req).size());
//
//        ACLMessage answer = mainResponseInformer.getResponses(req).get(0);
//        assertEquals(Act.AGREE,answer.getPerformative());
//        ACLMessage answer2 = mainResponseInformer.getResponses(req).get(1);
//        assertEquals(Act.INFORM_REF,answer2.getPerformative());
//
//        //answer2.getBody().decode(answer2.getEncoding());
//        MessageContentEncoder.decodeBody(answer2.getBody(), answer2.getEncoding());
//        assertEquals(InformRef.class,answer2.getBody().getClass());
//
//        Ref ref = ((InformRef) answer2.getBody()).getReferences();
//        assertNotNull(ref.getReferences());
//
//        assertTrue(ref.getReferences().containsKey("?inc"));
//        assertTrue(ref.getReferences().containsKey("?y"));
//        assertEquals(Double.class, ref.getReferences().get("?inc").getClass());
//        assertEquals(Double.class, ref.getReferences().get("?y").getClass());
//
//        Double z = (Double) ref.getReferences().get("?inc");
//        Double y = (Double) ref.getReferences().get("?y");
//
//        assertEquals(y, x + z,1e-6);
//
//    }
//
//
//
//
//
//
//
    @Test
    public void testSimpleInformInNewSession() {
        MockFact fact = new MockFact("patient3",18);
        MockFact fact2 = new MockFact("patient3",44);
        SynchronousDroolsAgentServiceImpl synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceImplService().getSynchronousDroolsAgentServiceImplPort(); 
        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);

        ACLMessage info = factory.newInformMessage("me","you",fact);
        List<ACLMessage> answers =  synchronousDroolsAgentServicePort.tell(info);

        assertEquals(0,answers.size());
//        StatefulKnowledgeSession target = mainAgent.getInnerSession("patient3");
//        assertNotNull(target);
//        assertTrue(target.getObjects().contains(fact));

        ACLMessage info2 = factory.newInformMessage("me","you",fact2);
        answers =  synchronousDroolsAgentServicePort.tell(info2);
        assertEquals(0,answers.size());
        //assertTrue(target.getObjects().contains(fact2));

    }
//  
}
