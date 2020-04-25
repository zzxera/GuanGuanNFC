package com.example.guanguannfc.view.management;

public class Act {
    private String actType;
    private String [] act;
    public Act(String actType,String [] act){
        this.act=act;
        this.actType=actType;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String[] getAct() {
        return act;
    }

    public void setAct(String[] act) {
        this.act = act;
    }
}
