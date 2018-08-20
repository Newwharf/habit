package com.flowermake.habit.domain;

import java.util.Date;

public class BodyDataLog {
    private Long iId;

    private Long iUserid;

    private Byte tiIndex;

    private Float fScore;

    private Date dtCdate;

    private String vComments;

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

    public Float getfScore() {
        return fScore;
    }

    public void setfScore(Float fScore) {
        this.fScore = fScore;
    }

    public Date getDtCdate() {
        return dtCdate;
    }

    public void setDtCdate(Date dtCdate) {
        this.dtCdate = dtCdate;
    }

    public String getvComments() {
        return vComments;
    }

    public void setvComments(String vComments) {
        this.vComments = vComments == null ? null : vComments.trim();
    }
}