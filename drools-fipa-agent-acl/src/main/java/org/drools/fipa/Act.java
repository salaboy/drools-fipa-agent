/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.fipa;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author salaboy
 */
@XmlType(name = "Act", namespace="http://fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public enum Act {
    ACCEPT              ("accept-proposal"),
        AGREE               ("agree"),
        CANCEL              ("cancel"),
        CALL_FOR_PROPOSAL   ("cfp"),
        CONFIRM             ("confirm"),
        DISCONFIRM          ("disconfirm"),
        FAILURE             ("failure"),
        INFORM              ("inform"),
        INFORM_IF           ("inform-if"),
        INFORM_REF          ("inform-ref"),
        NOT_UNDERSTOOD      ("not-understood"),
        PROPOSE             ("propose"),
        QUERY_IF            ("query-if"),
        QUERY_REF           ("query-ref"),
        REFUSE              ("refuse"),
        REJECT              ("reject-proposal"),
        REQUEST             ("request"),
        REQUEST_WHEN        ("request-when"),
        REQUEST_WHENEVER    ("request-whenever"),
        SUBSCRIBE           ("subscribe"),
        PROXY               ("proxy"),
        PROPAGATE           ("propagate");

        private String name;

        private Act(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }

        public static Act getPerformative(String name) {
            return map.get(name);
        }

        private static Map<String, Act> map;
        static {
            map = new HashMap<String, Act>();
            for (Act performative : Act.values()) {
                map.put(performative.toString(), performative);
            }
        }

        Set<String> getPerformativeNames() {
            return map.keySet();
        }

}
