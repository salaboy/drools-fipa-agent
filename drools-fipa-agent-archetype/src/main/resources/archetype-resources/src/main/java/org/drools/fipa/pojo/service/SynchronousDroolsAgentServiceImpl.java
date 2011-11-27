/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa.pojo.service;


import java.util.List;




import org.drools.fipa.ACLMessage;
import org.drools.fipa.DroolsAgent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author salaboy
 * @author esteban
 */


public class SynchronousDroolsAgentServiceImpl implements SynchronousDroolsAgentService {
    private static Logger logger = LoggerFactory.getLogger(SynchronousDroolsAgentServiceImpl.class);
    private DroolsAgent agent;
    private SynchronousDroolsAgentResponseInformer responseInformer;

    public SynchronousDroolsAgentServiceImpl() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("drools-fipa-agents.xml");
        this.agent =  (DroolsAgent) ctx.getBean("agent");
        this.responseInformer = (SynchronousDroolsAgentResponseInformer) ctx.getBean("syncResponseInformer");
    }

    
    public List<ACLMessage> tell(ACLMessage message) {
        logger.info(" >>> IN Message -> "+message.getPerformative().name());
        try {
            agent.tell(message);
        } catch (Throwable t) {
            logger.error(">>>>>>>>>>>>> exception => "+t.getMessage());
            
            t.printStackTrace();
        }
        List<ACLMessage> retrieveResponses = responseInformer.retrieveResponses(message);
        if(retrieveResponses != null ){
            logger.info(" >>> Number of OUT Messages -> "+retrieveResponses.size());
            for(ACLMessage outMessage : retrieveResponses){
                logger.info(" >>> OUT Message -> "+outMessage.getPerformative().name());
            }
        }else{
           logger.info(">>> 0 OUT Messages");
        }
        return retrieveResponses;
    }


}
