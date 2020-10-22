package com.uc.mvvmtestvideo.model;

import com.google.gson.annotations.SerializedName;

public class Cast {

    @SerializedName("name")
    private String name;

    @SerializedName("character")
    private String role;

    @SerializedName("profile_path")
    private String pic_url;

    public Cast(){

    }

    public Cast(String name, String role, String pic_url) {
        this.name = name;
        this.role = role;
        this.pic_url = pic_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

}
