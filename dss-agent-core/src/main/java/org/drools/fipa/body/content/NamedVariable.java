package org.drools.fipa.body.content;


import org.drools.runtime.rule.Variable;

public class NamedVariable {


    private String ref;
    private Variable variable;

    public NamedVariable(String name) {
        variable = Variable.v;
        ref = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

}
