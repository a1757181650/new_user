
package com.jinglangtech.cardiy.entity.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendCodeModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("results")
    @Expose
    private Results results;

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

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

}
