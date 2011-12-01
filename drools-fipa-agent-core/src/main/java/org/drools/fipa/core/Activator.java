/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.drools.fipa.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
/**
 *
 * @author salaboy
 */
public class Activator implements
    BundleActivator {

    public void start(BundleContext bc) throws Exception {
        System.out.println( "registering fipa core  services" );
       
        
        System.out.println( "fipa core services registered" );
    }

    public void stop(BundleContext bc) throws Exception {
        System.out.println( "stoping fipa core  services" );
    }
    
}
