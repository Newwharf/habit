package com.flowermake.habit.domain;

import java.util.Date;

public class PlanAction {
    private Long iPlanid;

    private Long iActionid;

    private Integer iNum;

    private Date dtCdate;

    private Byte tiState;

    public Long getiPlanid() {
        return iPlanid;
    }

    public void setiPlanid(Long iPlanid) {
        this.iPlanid = iPlanid;
    }

    public Long getiActionid() {
        return iActionid;
    }

    public void setiActionid(Long iActionid) {
        this.iActionid = iActionid;
    }

    public Integer getiNum() {
        return iNum;
    }

    public void setiNum(Integer iNum) {
        this.iNum = iNum;
    }

    public Date getDtCdate() {
        return dtCdate;
    }

    public void setDtCdate(Date dtCdate) {
        this.dtCdate = dtCdate;
    }

    public Byte getTiState() {
        return tiState;
    }

    public void setTiState(Byte tiState) {
        this.tiState = tiState;
    }
}