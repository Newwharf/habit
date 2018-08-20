package com.flowermake.habit.domain;

import java.util.Date;

public class Plan {
    private Long iId;

    private String vName;

    private Date dtCdate;

    private Byte tiState;

    private Long iUserid;

    private String hPlancol;

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

    public Byte getTiState() {
        return tiState;
    }

    public void setTiState(Byte tiState) {
        this.tiState = tiState;
    }

    public Long getiUserid() {
        return iUserid;
    }

    public void setiUserid(Long iUserid) {
        this.iUserid = iUserid;
    }

    public String gethPlancol() {
        return hPlancol;
    }

    public void sethPlancol(String hPlancol) {
        this.hPlancol = hPlancol == null ? null : hPlancol.trim();
    }
}