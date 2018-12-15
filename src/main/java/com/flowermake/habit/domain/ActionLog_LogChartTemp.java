package com.flowermake.habit.domain;

import java.util.Date;

/**
 * 历史记录查询中的动作历史视图
 * 
 * @author lihan
 *
 */
public class ActionLog_LogChartTemp {
	/**
	 * 训练id
	 */
	private long planId;
	/**
	 * 训练名称
	 */
	private String planName;
	/**
	 * 训练日志id
	 */
	private long planLogId;
	/**
	 * 训练日志开始时间
	 */
	private Date planLogCdate;
	/**
	 * 训练日志结束时间
	 */
	private Date planLogEdate;
	/**
	 * 动作id
	 */
	private long actionId;
	/**
	 * 动作名称
	 */
	private String actionName;
	/**
	 * 动作类型
	 */
	private byte actionType;
	/**
	 * 动作单位
	 */
	private String actionUnit;
	/**
	 * 动作日志id
	 */
	private long actionLogId;
	/**
	 * 动作在训练中位于第几组
	 */
	private int actionLogNumByPlan;
	/**
	 * 动作持续次数
	 */
	private int actionLogScorenum;
	/**
	 * 动作持续时间
	 */
	private int actionLogScoretime;
	/**
	 * 动作负重
	 */
	private float actionLogScoreweight;
	/**
	 * 动作感悟
	 */
	private String actionLogComments;

	public long getPlanId() {
		return planId;
	}

	public void setPlanId(long planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public long getPlanLogId() {
		return planLogId;
	}

	public void setPlanLogId(long planLogId) {
		this.planLogId = planLogId;
	}

	public Date getPlanLogCdate() {
		return planLogCdate;
	}

	public void setPlanLogCdate(Date planLogCdate) {
		this.planLogCdate = planLogCdate;
	}

	public Date getPlanLogEdate() {
		return planLogEdate;
	}

	public void setPlanLogEdate(Date planLogEdate) {
		this.planLogEdate = planLogEdate;
	}

	public long getActionId() {
		return actionId;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public byte getActionType() {
		return actionType;
	}

	public void setActionType(byte actionType) {
		this.actionType = actionType;
	}

	public String getActionUnit() {
		return actionUnit;
	}

	public void setActionUnit(String actionUnit) {
		this.actionUnit = actionUnit;
	}

	public long getActionLogId() {
		return actionLogId;
	}

	public void setActionLogId(long actionLogId) {
		this.actionLogId = actionLogId;
	}

	public int getActionLogNumByPlan() {
		return actionLogNumByPlan;
	}

	public void setActionLogNumByPlan(int actionLogNumByPlan) {
		this.actionLogNumByPlan = actionLogNumByPlan;
	}

	public int getActionLogScorenum() {
		return actionLogScorenum;
	}

	public void setActionLogScorenum(int actionLogScorenum) {
		this.actionLogScorenum = actionLogScorenum;
	}

	public int getActionLogScoretime() {
		return actionLogScoretime;
	}

	public void setActionLogScoretime(int actionLogScoretime) {
		this.actionLogScoretime = actionLogScoretime;
	}

	public float getActionLogScoreweight() {
		return actionLogScoreweight;
	}

	public void setActionLogScoreweight(float actionLogScoreweight) {
		this.actionLogScoreweight = actionLogScoreweight;
	}

	public String getActionLogComments() {
		return actionLogComments;
	}

	public void setActionLogComments(String actionLogComments) {
		this.actionLogComments = actionLogComments;
	}
}
