package com.flowermake.habit.domain;

import java.util.Date;

public class ActionType {
    private Long iId;

    private String vName;

    private Date dtCdate;

    private byte tiState;

    private Long iUserid;
    
    private String vRemarks;

    public String getvRemarks() {
		return vRemarks;
	}

	public void setvRemarks(String vRemarks) {
		this.vRemarks = vRemarks;
	}

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

    public byte getTiState() {
        return tiState;
    }

    public void setTiState(byte tiState) {
        this.tiState = tiState;
    }

    public Long getiUserid() {
        return iUserid;
    }

    public void setiUserid(Long iUserid) {
        this.iUserid = iUserid;
    }
    
}