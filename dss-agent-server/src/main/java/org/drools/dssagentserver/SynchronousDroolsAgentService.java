/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.dssagentserver;

import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.drools.fipa.ACLMessage;
import org.drools.fipa.DroolsAgent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author salaboy
 * @author esteban
 */

@WebService()
public class SynchronousDroolsAgentService {
    
    private DroolsAgent agent;
    private SynchronousDroolsAgentResponseInformer responseInformer;

    public SynchronousDroolsAgentService() {
        System.out.println("Calling the Constructor!!");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dss-agents.xml");
        this.agent =  (DroolsAgent) ctx.getBean("agent1");
        this.responseInformer = (SynchronousDroolsAgentResponseInformer) ctx.getBean("syncResponseInformer");
    }

    
    public List<ACLMessage> tell( ACLMessage message) {
        try {
            agent.tell(message);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return responseInformer.retrieveResponses(message);
    }

//    public DroolsAgent getAgent() {
//        return agent;
//    }
//
//    public void setAgent(DroolsAgent agent) {
//        if (!(agent.getResponseInformer() instanceof SynchronousDroolsAgentResponseInformer)) {
//            throw new IllegalArgumentException("Agent's response informer should be of type " + SynchronousDroolsAgentResponseInformer.class.getName() + " and not " + agent.getResponseInformer().getClass().getName());
//        }
//        this.agent = agent;
//        this.responseInformer = (SynchronousDroolsAgentResponseInformer) agent.getResponseInformer();
//    }
}
