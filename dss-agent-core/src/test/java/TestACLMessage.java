import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import org.drools.fipa.*;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Query;
import org.drools.fipa.body.content.Ref;
import org.drools.fipa.body.content.Rule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.kmr2.mock.MockFact;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Collections;
import java.util.Map;

import static junit.framework.Assert.*;

public class TestACLMessage {





    @Test
    @Ignore
    public void testSerializeDeserializeArray() {
        XStream json = new XStream(new JettisonMappedXmlDriver());

        Object[] test = new Object[] { new String("aaa") , new Integer( 233), new String("bbb"), new String("aaa") };



        String js = json.toXML(test);

        Object o = json.fromXML(js);
        Object[] rec = (Object[]) o;

        assertEquals("aaa",rec[0]);
        assertEquals(new Integer(233),rec[1]);
        assertEquals("bbb",rec[2]);
        assertEquals("aaa",rec[3]);

        // Unfortunately, XStream cannot deserialize mixed-type arrays in the proper order!!!
    }



    @Test
    public void testSerializeDeserialize() {
        ACLMessageFactory factory = new ACLMessageFactory(ACLMessageFactory.Encodings.JSON);

        Object source = new MockFact("x",24);
        ACLMessage msg = factory.newInformMessage("dav","sot",source);
        XStream cd = new XStream();

        String xml = cd.toXML(msg);
        System.out.println(xml);
        ACLMessage msg2 = (ACLMessage) cd.fromXML(xml);
        msg2.getBody().decode(msg2.getEncoding());
        System.out.println(msg2);

        Object target = msg2.getBody().getArguments()[0];
        assertEquals(source,target);

    }



    @Test
    public void testJsonInspection() {
        ACLMessageFactory factory = new ACLMessageFactory(ACLMessageFactory.Encodings.JSON);
        ACLMessage msg = factory.newInformMessage("dav","sot",new MockFact("x",24));

        try {
            String s = msg.inspect("$..name[0]");
            assertEquals("x", s);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    public void testXpathInspection() {
        ACLMessageFactory factory = new ACLMessageFactory(ACLMessageFactory.Encodings.JSON);
        ACLMessageFactory.Encodings def = factory.getDefaultEncoding();
        factory.setDefaultEncoding(ACLMessageFactory.Encodings.XML);
        ACLMessage msg = factory.newInformMessage("dav","sot",new MockFact("x",24));
        factory.setDefaultEncoding(def);

        try {
            String s = msg.inspect("//name");
            assertEquals("x",s);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }



    @Test
    public void testMessageConstruction() {

        ACLMessageFactory factory = new ACLMessageFactory(ACLMessageFactory.Encodings.JSON);


        Object obj = new String("x");
        Action act = new Action("act",null);
        Query qry = new Query("test",1,2,3);
        Rule rule = new Rule("when String( this == \"test\" )");
        Map<String,Object> map = Collections.emptyMap();
        Ref ref = new Ref(map);
        AgentID[] tgts = new AgentID[0];


        ACLMessage msg;

        msg = factory.newAcceptProposalMessage("D", "S", act, rule);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.ACCEPT);

        msg = factory.newAgreeMessage("D", "S", act, rule);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.AGREE);

        msg = factory.newCancelMessage("D", "S", act);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.CANCEL);

        msg = factory.newCallForProposalMessage("D", "S", act, rule);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.CALL_FOR_PROPOSAL);

        msg = factory.newConfirmMessage("D", "S", obj);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.CONFIRM);

        msg = factory.newDisconfirmMessage("D", "S", obj);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.DISCONFIRM);

        msg = factory.newFailureMessage("D", "S", act, obj);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.FAILURE);

        msg = factory.newInformMessage("D", "S", obj);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.INFORM);

        msg = factory.newInformIfMessage("D", "S", obj);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.INFORM_IF);

        msg = factory.newInformRefMessage("D", "S", ref);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.INFORM_REF);

        msg = factory.newNotUnderstoodMessage("D", "S", act, obj);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.NOT_UNDERSTOOD);

        msg = factory.newPropagateMessage("D", "S", tgts, msg, rule);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.PROPAGATE);

        msg = factory.newProposeMessage("D","S", act, rule);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.PROPOSE);

        msg = factory.newProxyMessage("D","S", tgts, msg, rule);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.PROXY);

        msg = factory.newQueryIfMessage("D","S",obj);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.QUERY_IF);

        msg = factory.newQueryRefMessage("D","S",qry);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.QUERY_REF);

        msg = factory.newRefuseMessage("D","S", act, obj);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.REFUSE);

        msg = factory.newRejectProposalMessage("D","S", act, act, obj);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.REJECT);

        msg = factory.newRequestMessage("D","S",act);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.REQUEST);

        msg = factory.newRequestWhenMessage("D","S",act,rule);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.REQUEST_WHEN);

        msg = factory.newRequestWheneverMessage("D","S",act,rule);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.REQUEST_WHENEVER);

        msg = factory.newSubscribeMessage("D","S",qry);
        assertTrue(checkMessageIntegrity(msg));
        assertEquals(msg.getPerformative(), ACLMessage.Act.SUBSCRIBE);

    }


    private static boolean checkMessageIntegrity(ACLMessage msg) {
        return msg.getId() != null &&
                msg.getId().length() > 0 &&
                ACLMessage.DEFAULT_FIPA_MESSAGE_TYPE.equals(msg.getMessageType()) &&
                msg.getConversationId() != null &&
                msg.getConversationId().length() > 0 &&
                msg.getSender() != null &&
                msg.getReceiver() != null &&
                msg.getPerformative() != null &&
                msg.getBody() != null;
    }










}
