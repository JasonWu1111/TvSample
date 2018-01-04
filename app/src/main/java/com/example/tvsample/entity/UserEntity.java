package com.example.tvsample.entity;

/**
 * Created by JasonWu on 04/01/2018
 */

public class UserEntity {
    /**
     * avatar : https://newscdn.accimg.com/avatar/personal_default.jpg
     * city :
     * country :
     * isBindFb : 1
     * isBindGoogle : 0
     * memberId : 42783616
     * nickname : 用戶42783616
     * prov :
     * registerTime : 1515033155000
     * status : 1
     * token : 62c74cefbcfb4a88869229ef50d9f1a8
     */

    private String avatar;
    private String city;
    private String country;
    private int isBindFb;
    private int isBindGoogle;
    private String memberId;
    private String nickname;
    private String prov;
    private long registerTime;
    private int status;
    private String token;
    private String birth;
    private String email;
    private int sex;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getIsBindFb() {
        return isBindFb;
    }

    public void setIsBindFb(int isBindFb) {
        this.isBindFb = isBindFb;
    }

    public int getIsBindGoogle() {
        return isBindGoogle;
    }

    public void setIsBindGoogle(int isBindGoogle) {
        this.isBindGoogle = isBindGoogle;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
