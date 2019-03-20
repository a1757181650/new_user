
package com.jinglangtech.cardiy.entity.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("userguid")
    @Expose
    private Integer userguid;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("userid")
    @Expose
    private Integer userid;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getUserGuid() {
        return userguid;
    }

    public void setUserguid(Integer userguid) {
        this.userguid = userguid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

}
