package com.flowermake.habit.domain;

import java.util.Date;

public class ActionLog{
	private Long iId;

	private Long iPlanlogid;

	private Long iActionid;

	private Byte tiActiontype;

	private String vActionunit;

	private Integer iNumbyplan;

	private Float fScoreweight;

	private Integer iScorenum;

	private String vComments;

	private Integer iScoretime;

	private Date dtCdate;

	private Date dtLedate;

	private Byte tiState;

	private long iUserid;
	
	public long getiUserid() {
		return iUserid;
	}

	public void setiUserid(long iUserid) {
		this.iUserid = iUserid;
	}

	public Long getiId() {
		return iId;
	}

	public void setiId(Long iId) {
		this.iId = iId;
	}

	public Long getiPlanlogid() {
		return iPlanlogid;
	}

	public void setiPlanlogid(Long iPlanlogid) {
		this.iPlanlogid = iPlanlogid;
	}

	public Long getiActionid() {
		return iActionid;
	}

	public void setiActionid(Long iActionid) {
		this.iActionid = iActionid;
	}

	public Byte getTiActiontype() {
		return tiActiontype;
	}

	public void setTiActiontype(Byte tiActiontype) {
		this.tiActiontype = tiActiontype;
	}

	public String getvActionunit() {
		return vActionunit;
	}

	public void setvActionunit(String vActionunit) {
		this.vActionunit = vActionunit == null ? null : vActionunit.trim();
	}

	public Integer getiNumbyplan() {
		return iNumbyplan;
	}

	public void setiNumbyplan(Integer iNumbyplan) {
		this.iNumbyplan = iNumbyplan;
	}

	public Float getfScoreweight() {
		return fScoreweight;
	}

	public void setfScoreweight(Float fScoreweight) {
		this.fScoreweight = fScoreweight;
	}

	public Integer getiScorenum() {
		return iScorenum;
	}

	public void setiScorenum(Integer iScorenum) {
		this.iScorenum = iScorenum;
	}

	public String getvComments() {
		return vComments;
	}

	public void setvComments(String vComments) {
		this.vComments = vComments == null ? null : vComments.trim();
	}

	public Integer getiScoretime() {
		return iScoretime;
	}

	public void setiScoretime(Integer iScoretime) {
		this.iScoretime = iScoretime;
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

}