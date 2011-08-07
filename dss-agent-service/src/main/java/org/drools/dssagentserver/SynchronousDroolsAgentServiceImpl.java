/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.dssagentserver;


import java.util.List;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.drools.fipa.ACLMessage;
import org.drools.fipa.DroolsAgent;
import org.drools.fipa.body.acts.Agree;
import org.drools.fipa.body.acts.Failure;
import org.drools.fipa.body.acts.Inform;
import org.drools.fipa.body.acts.InformIf;
import org.drools.fipa.body.acts.QueryIf;
import org.drools.fipa.body.content.Action;
import org.drools.fipa.body.content.Rule;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author salaboy
 * @author esteban
 */

@WebService()
@XmlSeeAlso(value={Inform.class, QueryIf.class, InformIf.class, Agree.class, Failure.class, Action.class, Rule.class})
public class SynchronousDroolsAgentServiceImpl implements SynchronousDroolsAgentService {
    
    private DroolsAgent agent;
    private SynchronousDroolsAgentResponseInformer responseInformer;

    public SynchronousDroolsAgentServiceImpl() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dss-agents.xml");
        this.agent =  (DroolsAgent) ctx.getBean("agent1");
        this.responseInformer = (SynchronousDroolsAgentResponseInformer) ctx.getBean("syncResponseInformer");
    }

    
    @Override
    public List<ACLMessage> tell( ACLMessage message) {
        
        try {
            agent.tell(message);
        } catch (Throwable t) {
            
            t.printStackTrace();
        }
        List<ACLMessage> retrieveResponses = responseInformer.retrieveResponses(message);
       
        return retrieveResponses;
    }


}
