package com.liguoxi.androiddemo.entity;

/**
 * Created by Li Guoxi on 2016/8/1.
 */
public class LocationInfoEntity {
    private String province;
    private String city;
    private String district;
    private String address;

    private String title;

    public LocationInfoEntity() {
    }

    public LocationInfoEntity(String title,String province, String city, String district, String address) {
        this.title = title;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
