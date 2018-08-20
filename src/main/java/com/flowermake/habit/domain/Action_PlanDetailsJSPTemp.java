package com.flowermake.habit.domain;

//plandetails页面的临时action展示视图
public class Action_PlanDetailsJSPTemp {

	/**
	 * Action id
	 */
	private long aid;

	/**
	 * PlanLog id
	 */
	private long plid;

	/**
	 * Plan id
	 */
	private long pid;

	/**
	 * ActionLog id
	 */
	private long alid;

	/**
	 * Action 名称
	 */
	private String aname;

	/**
	 * 本次动作的组数
	 */
	private int num;

	/**
	 * 本次动作到第几组
	 */
	private int innum;

	/**
	 * 动作类型，0计次，1计时
	 */
	private byte type;

	/**
	 * 动作单位
	 */
	private String unit;
	
	/**
	 * 训练状态，0未训练，1训练中
	 */
	private byte state;

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public long getPlid() {
		return plid;
	}

	public void setPlid(long plid) {
		this.plid = plid;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getAlid() {
		return alid;
	}

	public void setAlid(long alid) {
		this.alid = alid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getInnum() {
		return innum;
	}

	public void setInnum(int innum) {
		this.innum = innum;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

}
