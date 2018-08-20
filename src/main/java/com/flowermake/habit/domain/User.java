package com.flowermake.habit.domain;

import java.util.Date;

public class User {
    private Long iId;

    private String vWechatuid;

    private String vName;

    private Byte tiSex;

    private Date dBirthday;

    private String vTel;

    private Date dtCdate;

    private String vImgurl;

    public Long getiId() {
        return iId;
    }

    public void setiId(Long iId) {
        this.iId = iId;
    }

    public String getvWechatuid() {
        return vWechatuid;
    }

    public void setvWechatuid(String vWechatuid) {
        this.vWechatuid = vWechatuid == null ? null : vWechatuid.trim();
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName == null ? null : vName.trim();
    }

    public Byte getTiSex() {
        return tiSex;
    }

    public void setTiSex(Byte tiSex) {
        this.tiSex = tiSex;
    }

    public Date getdBirthday() {
        return dBirthday;
    }

    public void setdBirthday(Date dBirthday) {
        this.dBirthday = dBirthday;
    }

    public String getvTel() {
        return vTel;
    }

    public void setvTel(String vTel) {
        this.vTel = vTel == null ? null : vTel.trim();
    }

    public Date getDtCdate() {
        return dtCdate;
    }

    public void setDtCdate(Date dtCdate) {
        this.dtCdate = dtCdate;
    }

    public String getvImgurl() {
        return vImgurl;
    }

    public void setvImgurl(String vImgurl) {
        this.vImgurl = vImgurl == null ? null : vImgurl.trim();
    }
}