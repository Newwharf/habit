package com.flowermake.habit.domain;

import java.util.Date;

public class PlanLog {
	private Long id;

	private Date dtCdate;

	private Date dtLedate;

	private Byte tiState;

	private Long iPlanid;

	private long iUserid;

	public long getiUserid() {
		return iUserid;
	}

	public void setiUserid(long iUserid) {
		this.iUserid = iUserid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtCdate() {
		return dtCdate;
	}

	public void setDtCdate(Date dtCdate) {
		this.dtCdate = dtCdate;
	}

	public Date getDtLedate() {
		return dtLedate;
	}

	public void setDtLedate(Date dtLedate) {
		this.dtLedate = dtLedate;
	}

	public Byte getTiState() {
		return tiState;
	}

	public void setTiState(Byte tiState) {
		this.tiState = tiState;
	}

	public Long getiPlanid() {
		return iPlanid;
	}

	public void setiPlanid(Long iPlanid) {
		this.iPlanid = iPlanid;
	}
}