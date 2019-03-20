
package com.jinglangtech.cardiy.entity.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfoResults implements Parcelable {

    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("userid")
    @Expose
    private Integer userid;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createtime);
        dest.writeString(this.phone);
        dest.writeString(this.sex);
        dest.writeString(this.name);
        dest.writeString(this.nickname);
        dest.writeString(this.avatar);
        dest.writeValue(this.userid);
    }

    public UserInfoResults() {
    }

    protected UserInfoResults(Parcel in) {
        this.createtime = in.readString();
        this.phone = in.readString();
        this.sex = in.readString();
        this.name = in.readString();
        this.nickname = in.readString();
        this.avatar = in.readString();
        this.userid = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<UserInfoResults> CREATOR = new Creator<UserInfoResults>() {
        @Override
        public UserInfoResults createFromParcel(Parcel source) {
            return new UserInfoResults(source);
        }

        @Override
        public UserInfoResults[] newArray(int size) {
            return new UserInfoResults[size];
        }
    };
}
