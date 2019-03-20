
package com.jinglangtech.cardiy.entity.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfoModel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("results")
    @Expose
    private UserInfoResults results;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public UserInfoResults getResults() {
        return results;
    }

    public void setResults(UserInfoResults results) {
        this.results = results;
    }

}
