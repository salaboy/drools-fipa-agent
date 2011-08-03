/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa;

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
        Info info = new Info();
        info.setEncodedContent("{\"org.mock.MockFact\":{\"name\":\"patient1\",\"age\":18}}");
        info.setEncoded(true);
        inform.setProposition(info);
        informMessage.setBody(inform);
        
        AgentID receiver = new AgentID();
        receiver.setName("you@org.DROOLS");
        
        List<AgentID> receivers = informMessage.getReceiver();
        receivers.add(receiver);
        
        List<ACLMessage> answers = synchronousDroolsAgentServicePort.tell(informMessage);
        
        
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
        info = new Info();
        //info.setEncodedContent("{&quot;org.kmr2.mock.MockFact&quot;:{&quot;name&quot;:&quot;patient1&quot;,&quot;age&quot;:18}}");
        info.setEncodedContent("{\"org.mock.MockFact\":{\"name\":\"patient1\",\"age\":18}}");
        info.setEncoded(true);
        queryIf.setProposition(info);
        queryIfMessage.setBody(queryIf);
        
        //I'm using the same receiver
        receivers = queryIfMessage.getReceiver();
        receivers.add(receiver);
        answers = synchronousDroolsAgentServicePort.tell(queryIfMessage);
        
        
        
        assertNotNull(answers);
        
        
        
    }
}