
package com.jinglangtech.cardiy.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeadList {

    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("viewNum")
    @Expose
    private Integer viewNum;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("state")
    @Expose
    private Integer state;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("shareNum")
    @Expose
    private Integer shareNum;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

}
