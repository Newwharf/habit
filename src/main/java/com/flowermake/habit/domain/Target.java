package com.flowermake.habit.domain;

import java.util.Date;

import com.flowermake.habit.tools.Commons;

public class Target {
	private Long iId;

	private Long iUserid;

	private Byte tiIndex;

	private Byte tiNexus;

	private Float fValue;

	private Byte tiFlag;

	private Byte tiState;

	private Date dtCdate;

	private String vMsg;

	public String getvMsg() {
		return vMsg;
	}

	public void setvMsg(String vMsg) {
		this.vMsg = vMsg;
	}

	public Long getiId() {
		return iId;
	}

	public void setiId(Long iId) {
		this.iId = iId;
	}

	public Long getiUserid() {
		return iUserid;
	}

	public void setiUserid(Long iUserid) {
		this.iUserid = iUserid;
	}

	public Byte getTiIndex() {
		return tiIndex;
	}

	public void setTiIndex(Byte tiIndex) {
		this.tiIndex = tiIndex;
	}

	public Byte getTiNexus() {
		return tiNexus;
	}

	public void setTiNexus(Byte tiNexus) {
		this.tiNexus = tiNexus;
	}

	public Float getfValue() {
		return fValue;
	}

	public void setfValue(Float fValue) {
		this.fValue = fValue;
	}

	public Byte getTiFlag() {
		return tiFlag;
	}

	public void setTiFlag(Byte tiFlag) {
		this.tiFlag = tiFlag;
	}

	public Byte getTiState() {
		return tiState;
	}

	public void setTiState(Byte tiState) {
		this.tiState = tiState;
	}

	public Date getDtCdate() {
		return dtCdate;
	}

	public void setDtCdate(Date dtCdate) {
		this.dtCdate = dtCdate;
	}

	/**
	 * 判断本目标是否达成
	 * 
	 * @param bodyData
	 *            用于比较的当前身体数据
	 * @return 如果达成，则返回true，否则返回false
	 */
	public boolean isTargetComplate(BodyData bodyData) {
		byte index = this.getTiIndex();
		boolean boo = false;
		switch (index) {
		case 0:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastheight(), this.getTiNexus());
			break;
		case 1:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastweight(), this.getTiNexus());
			break;
		case 2:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastbodyfat(), this.getTiNexus());
			break;
		case 3:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastshouldersize(), this.getTiNexus());
			break;
		case 4:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastbust(), this.getTiNexus());
			break;
		case 5:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastwaistline(), this.getTiNexus());
			break;
		case 6:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastabdominalsize(), this.getTiNexus());
			break;
		case 7:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLasthipline(), this.getTiNexus());
			break;
		case 8:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastlarmsize(), this.getTiNexus());
			break;
		case 9:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastrarmsize(), this.getTiNexus());
			break;
		case 10:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastlforearmsize(), this.getTiNexus());
			break;
		case 11:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastrforearmsize(), this.getTiNexus());
			break;
		case 12:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastlthighsize(), this.getTiNexus());
			break;
		case 13:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastrthighsize(), this.getTiNexus());
			break;
		case 14:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastlcrussize(), this.getTiNexus());
			break;
		case 15:
			boo = !Commons.value1CompareValue2(this.getfValue(), bodyData.getfLastrcrussize(), this.getTiNexus());
			break;
		}
		return boo;
	}

	/**
	 * 取得本目标的成绩文案
	 * 
	 * @param bodyData
	 *            当前的身体数据
	 * @return 目标的成绩文案
	 */
	public String getTargetScore(BodyData bodyData) {
		String score = "";
		switch (this.getTiIndex()) {
		case 0:
			score = "目前成绩" + bodyData.getfLastheight() + "cm";
			break;
		case 1:
			score = "目前成绩" + bodyData.getfLastweight() + "kg";
			break;
		case 2:
			score = "目前成绩" + bodyData.getfLastbodyfat() + "%";
			break;
		case 3:
			score = "目前成绩" + bodyData.getfLastshouldersize() + "cm";
			break;
		case 4:
			score = "目前成绩" + bodyData.getfLastbust() + "cm";
			break;
		case 5:
			score = "目前成绩" + bodyData.getfLastwaistline() + "cm";
			break;
		case 6:
			score = "目前成绩" + bodyData.getfLastabdominalsize() + "cm";
			break;
		case 7:
			score = "目前成绩" + bodyData.getfLasthipline() + "cm";
			break;
		case 8:
			score = "目前成绩" + bodyData.getfLastlarmsize() + "cm";
			break;
		case 9:
			score = "目前成绩" + bodyData.getfLastrarmsize() + "cm";
			break;
		case 10:
			score = "目前成绩" + bodyData.getfLastlforearmsize() + "cm";
			break;
		case 11:
			score = "目前成绩" + bodyData.getfLastrforearmsize() + "cm";
			break;
		case 12:
			score = "目前成绩" + bodyData.getfLastlthighsize() + "cm";
			break;
		case 13:
			score = "目前成绩" + bodyData.getfLastrthighsize() + "cm";
			break;
		case 14:
			score = "目前成绩" + bodyData.getfLastlcrussize() + "cm";
			break;
		case 15:
			score = "目前成绩" + bodyData.getfLastrcrussize() + "cm";
			break;
		}
		return score;
	}

	/**
	 * 得到目标的msg文案
	 * 
	 * @return msg文案
	 */
	public String getTargetMsg() {
		String msg = "";
		switch (this.getTiNexus()) {
		case 0:
			msg = "大于等于";
			break;
		case 1:
			msg = "小于等于";
			break;
		}
		switch (this.getTiIndex()) {
		case 0:
			msg = "身高" + msg + this.getfValue() + "cm";
			break;
		case 1:
			msg = "体重" + msg + this.getfValue() + "kg";
			break;
		case 2:
			msg = "体脂" + msg + this.getfValue() + "%";
			break;
		case 3:
			msg = "肩围" + msg + this.getfValue() + "cm";
			break;
		case 4:
			msg = "胸围" + msg + this.getfValue() + "cm";
			break;
		case 5:
			msg = "腰围" + msg + this.getfValue() + "cm";
			break;
		case 6:
			msg = "腹围" + msg + this.getfValue() + "cm";
			break;
		case 7:
			msg = "臀围" + msg + this.getfValue() + "cm";
			break;
		case 8:
			msg = "左大臂围" + msg + this.getfValue() + "cm";
			break;
		case 9:
			msg = "右大臂围" + msg + this.getfValue() + "cm";
			break;
		case 10:
			msg = "左小臂围" + msg + this.getfValue() + "cm";
			break;
		case 11:
			msg = "右小臂围" + msg + this.getfValue() + "cm";
			break;
		case 12:
			msg = "左大腿围" + msg + this.getfValue() + "cm";
			break;
		case 13:
			msg = "右大腿围" + msg + this.getfValue() + "cm";
			break;
		case 14:
			msg = "左小腿围" + msg + this.getfValue() + "cm";
			break;
		case 15:
			msg = "右小腿围" + msg + this.getfValue() + "cm";
			break;
		}
		return msg;
	}

}