
package com.jinglangtech.cardiy.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("results")
    @Expose
    private List<CityResult> results = null;

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

    public List<CityResult> getResults() {
        return results;
    }

    public void setResults(List<CityResult> results) {
        this.results = results;
    }

}
