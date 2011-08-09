package org.drools.fipa.body.content;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Rule", namespace="http://content.body.fipa.drools.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rule extends AbstractMessageContent {
    @XmlElement(required = true)
    private String drl;

    public Rule() {
    }

    
    
//    public Rule(String drl) {
//        this.drl = drl;
//    }

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

  


}
