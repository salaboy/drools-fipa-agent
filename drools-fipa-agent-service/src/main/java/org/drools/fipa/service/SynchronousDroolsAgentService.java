/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa.service;

import java.util.List;
import org.drools.fipa.ACLMessage;

/**
 *
 * @author salaboy
 */
public interface SynchronousDroolsAgentService {

    List<ACLMessage> tell(ACLMessage message);
    
}
