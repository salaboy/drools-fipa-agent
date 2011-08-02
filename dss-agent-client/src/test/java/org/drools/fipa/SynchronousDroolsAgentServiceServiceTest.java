/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa;

import java.util.List;
import org.drools.dssagentserver.AclMessage;
import org.drools.dssagentserver.SynchronousDroolsAgentService;
import org.drools.dssagentserver.SynchronousDroolsAgentServiceService;
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
   
    @Test
    public void hello() {
        SynchronousDroolsAgentService synchronousDroolsAgentServicePort = new SynchronousDroolsAgentServiceService().getSynchronousDroolsAgentServicePort();
        List<AclMessage> answers = synchronousDroolsAgentServicePort.tell(new AclMessage());
        
        assertNotNull(answers);
    }
}
