
package com.jinglangtech.cardiy.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityResult {

    @SerializedName("zipCode")
    @Expose
    private String zipCode;
    @SerializedName("pinyin")
    @Expose
    private String pinyin;
    @SerializedName("cityName")
    @Expose
    private String cityName;
    @SerializedName("mergerName")
    @Expose
    private String mergerName;
    @SerializedName("levelType")
    @Expose
    private String levelType;
    @SerializedName("cityCode")
    @Expose
    private String cityCode;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("state")
    @Expose
    private Integer state;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("parentId")
    @Expose
    private Integer parentId;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMergerName() {
        return mergerName;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
