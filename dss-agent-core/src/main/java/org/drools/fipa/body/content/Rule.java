package org.drools.fipa.body.content;

import org.drools.fipa.ACLMessageFactory;

/**
 * Created by IntelliJ IDEA.
 * Date: 5/7/11
 * Time: 8:04 PM
 */
public class Rule extends AbstractMessageContent {

    private String drl;

    public Rule() {
    }

    
    
    public Rule(String drl) {
        this.drl = drl;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "drl='" + drl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (drl != null ? !drl.equals(rule.drl) : rule.drl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return drl != null ? drl.hashCode() : 0;
    }

    public String getDrl() {
        return drl;
    }

    public void setDrl(String drl) {
        this.drl = drl;
    }

    public void encode(ACLMessageFactory.Encodings encoding) {

    }


    public void decode(ACLMessageFactory.Encodings encoding) {

    }


}
