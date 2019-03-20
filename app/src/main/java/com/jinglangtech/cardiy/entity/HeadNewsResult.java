
package com.jinglangtech.cardiy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeadNewsResult implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createtime);
        dest.writeValue(this.viewNum);
        dest.writeString(this.logo);
        dest.writeValue(this.id);
        dest.writeValue(this.state);
        dest.writeString(this.title);
        dest.writeValue(this.shareNum);
    }

    public HeadNewsResult() {
    }

    protected HeadNewsResult(Parcel in) {
        this.createtime = in.readString();
        this.viewNum = (Integer) in.readValue(Integer.class.getClassLoader());
        this.logo = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.state = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.shareNum = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<HeadNewsResult> CREATOR = new Creator<HeadNewsResult>() {
        @Override
        public HeadNewsResult createFromParcel(Parcel source) {
            return new HeadNewsResult(source);
        }

        @Override
        public HeadNewsResult[] newArray(int size) {
            return new HeadNewsResult[size];
        }
    };
}
