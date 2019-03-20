
package com.jinglangtech.cardiy.entity.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("list")
    @Expose
    private java.util.List<NewsList> list = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public java.util.List<NewsList> getList() {
        return list;
    }

    public void setList(java.util.List<NewsList> list) {
        this.list = list;
    }

}
