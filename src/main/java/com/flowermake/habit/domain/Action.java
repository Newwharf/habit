package com.flowermake.habit.domain;

import java.util.Date;

public class Action {
    private Long iId;

    private String vName;

    private Byte tiType;

    private String vUnit;

    private Long iUserid;

    private Byte sState;

    private Date dtCdate;
    
    private Long iActionTypeid;

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

    public Byte getTiType() {
        return tiType;
    }

    public void setTiType(Byte tiType) {
        this.tiType = tiType;
    }

    public String getvUnit() {
        return vUnit;
    }

    public void setvUnit(String vUnit) {
        this.vUnit = vUnit == null ? null : vUnit.trim();
    }

    public Long getiUserid() {
        return iUserid;
    }

    public void setiUserid(Long iUserid) {
        this.iUserid = iUserid;
    }

    public Byte getsState() {
        return sState;
    }

    public void setsState(Byte sState) {
        this.sState = sState;
    }

    public Date getDtCdate() {
        return dtCdate;
    }

    public void setDtCdate(Date dtCdate) {
        this.dtCdate = dtCdate;
    }

	public Long getiActionTypeid() {
		return iActionTypeid;
	}

	public void setiActionTypeid(Long iActionTypeid) {
		this.iActionTypeid = iActionTypeid;
	}
    
}