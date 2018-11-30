package com.flowermake.habit.domain;

/**
 * 新建计划页面，供选择展示用的分类
 * 
 * @author lihan
 *
 */
public class Action_NewPlanJSPTemp {

	/**
	 * 动作分类id
	 */
	private long actionTypeId;
	/**
	 * 动作分类名称
	 */
	private String actionTypeName;
	/**
	 * 动作id
	 */
	private long actionId;
	/**
	 * 动作名称
	 */
	private String actionName;
	/**
	 * 动作单位
	 */
	private String actionUnit;
	/**
	 * 动作类型，0计次动作，1计时动作
	 */
	private byte actionType;

	public long getActionTypeId() {
		return actionTypeId;
	}

	public void setActionTypeId(long actionTypeId) {
		this.actionTypeId = actionTypeId;
	}

	public String getActionTypeName() {
		return actionTypeName;
	}

	public void setActionTypeName(String actionTypeName) {
		this.actionTypeName = actionTypeName;
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

	public String getActionUnit() {
		return actionUnit;
	}

	public void setActionUnit(String actionUnit) {
		this.actionUnit = actionUnit;
	}

	public byte getActionType() {
		return actionType;
	}

	public void setActionType(byte actionType) {
		this.actionType = actionType;
	}

}
