/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbpm.task;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author salaboy
 */
public class HumanTaskServiceLookup {
    private static HumanTaskServiceLookup instance;
    
    public static HumanTaskServiceLookup getInstance(){
        if(instance == null){
            instance = new HumanTaskServiceLookup();
        }
        return instance;
    }
    
    public static TaskService lookup() throws NamingException{
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        TaskService service = (TaskService) envCtx.lookup("bean/HumanTaskService");
        System.out.println("GETTING JNDI TASK SERVICE INSTANCE = " + service);
        return service;
    }
    
}
