package com.flowermake.habit.domain;

import java.util.Date;

public class ActionType {
    private Long iId;

    private String vName;

    private Date dtCdate;

    private String tiState;

    private Long iUserid;

    public Long getiId() {
        return iId;
    }

    public void setiId(Long iId) {
        this.iId = iId;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName == null ? null : vName.trim();
    }

    public Date getDtCdate() {
        return dtCdate;
    }

    public void setDtCdate(Date dtCdate) {
        this.dtCdate = dtCdate;
    }

    public String getTiState() {
        return tiState;
    }

    public void setTiState(String tiState) {
        this.tiState = tiState == null ? null : tiState.trim();
    }

    public Long getiUserid() {
        return iUserid;
    }

    public void setiUserid(Long iUserid) {
        this.iUserid = iUserid;
    }
}