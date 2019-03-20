
package com.jinglangtech.cardiy.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsResult {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("list")
    @Expose
    private java.util.List<HeadList> list = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public java.util.List<HeadList> getList() {
        return list;
    }

    public void setList(java.util.List<HeadList> list) {
        this.list = list;
    }

}
