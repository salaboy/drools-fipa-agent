/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa;

/**
 *
 * @author esteban
 */
public interface DroolsAgentResponseInformer {

    void informResponse(ACLMessage originalMessage, ACLMessage reponse);

}