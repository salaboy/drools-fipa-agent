/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa;

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
        MockFact fact = new MockFact("patient1",18);
        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);

        ACLMessage info = factory.newInformMessage("me","you",fact);

         List<ACLMessage> answers = synchronousDroolsAgentServicePort.tell(info);

          assertNotNull(answers);
        assertEquals(0, answers.size());
//        assertNull(mainResponseInformer.getResponses(info));
//        StatefulKnowledgeSession target = mainAgent.getInnerSession("session1");
//        assertTrue(target.getObjects().contains(fact));

    }
     
     @Test
    public void testInformAsTrigger() {
        MockFact fact = new MockFact("patient1",22);
        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);


         SynchronousDroolsAgentServiceImpl synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceImplService().getSynchronousDroolsAgentServiceImplPort();
        ACLMessage info = factory.newInformMessage("me","you",fact);
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
        MockFact fact = new MockFact("patient1",18);
        ACLMessageFactory factory = new ACLMessageFactory(Encodings.XML);

        ACLMessage info = factory.newInformMessage("me","you",fact);
        List<ACLMessage> answers =   synchronousDroolsAgentServicePort.tell(info);
        assertEquals(0, answers.size());
        ACLMessage qryif = factory.newQueryIfMessage("me","you",fact);
        //assertNull(mainResponseInformer.getResponses(qryif));
        answers =   synchronousDroolsAgentServicePort.tell(qryif);
        assertEquals(1, answers.size());

//        assertNotNull(mainResponseInformer.getResponses(qryif));
//        assertEquals(1, mainResponseInformer.getResponses(qryif).size());
//
//        ACLMessage answer = mainResponseInformer.getResponses(qryif).get(0);
//            //answer.getBody().decode(answer.getEncoding());
//            MessageContentEncoder.decodeBody(answer.getBody(), answer.getEncoding());
//            assertEquals(Act.INFORM_IF,answer.getPerformative());
//            assertEquals(answer.getBody().getArguments()[0],fact);
    }
}

