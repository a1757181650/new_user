
package com.jinglangtech.cardiy.entity.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsContentResultModel {

    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("viewNum")
    @Expose
    private Integer viewNum;
    @SerializedName("isCollect")
    @Expose
    private Boolean isCollect;
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
    @SerializedName("content")
    @Expose
    private String content;

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

    public Boolean getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Boolean isCollect) {
        this.isCollect = isCollect;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
