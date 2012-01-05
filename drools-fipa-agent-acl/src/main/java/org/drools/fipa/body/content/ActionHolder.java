package org.drools.fipa.body.content;


public class ActionHolder {

    private String  refId;
    private Action  action;
    private String  condition;
    private boolean continual;


    public ActionHolder(String refId, Action action, String condition, boolean continual) {
        this.refId = refId;
        this.action = action;
        this.condition = condition;
        this.continual = continual;
    }


    @Override
    public String toString() {
        return "ActionHolder{" +
                "refId='" + refId + '\'' +
                ", action=" + action +
                ", condition='" + condition + '\'' +
                ", continual=" + continual +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionHolder that = (ActionHolder) o;

        if (continual != that.continual) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (condition != null ? !condition.equals(that.condition) : that.condition != null) return false;
        if (refId != null ? !refId.equals(that.refId) : that.refId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refId != null ? refId.hashCode() : 0;
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (continual ? 1 : 0);
        return result;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isContinual() {
        return continual;
    }

    public void setContinual(boolean continual) {
        this.continual = continual;
    }
}
