/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.dssagentserver;

import org.drools.fipa.ACLMessage;
import org.drools.fipa.DroolsAgent;

import java.util.List;

/**
 *
 * @author esteban
 */
public class SynchronousDroolsAgent {

    private DroolsAgent agent;
    private SynchronousDroolsAgentResponseInformer responseInformer;

    public List<ACLMessage> tell(ACLMessage message) {
        try {
            agent.tell(message);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return responseInformer.retrieveResponses(message);
    }

    public DroolsAgent getAgent() {
        return agent;
    }

    public void setAgent(DroolsAgent agent) {
        if (!(agent.getResponseInformer() instanceof SynchronousDroolsAgentResponseInformer)) {
            throw new IllegalArgumentException("Agent's response informer should be of type " + SynchronousDroolsAgentResponseInformer.class.getName() + " and not " + agent.getResponseInformer().getClass().getName());
        }
        this.agent = agent;
        this.responseInformer = (SynchronousDroolsAgentResponseInformer) agent.getResponseInformer();
    }
}
