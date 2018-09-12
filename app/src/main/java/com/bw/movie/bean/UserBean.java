package com.bw.movie.bean;

public class UserBean {

    private String nickName;
    private String phone;
    private String pwd;
    private String pwd2;
    private Integer sex;
    private String birthday;
    private String imei;
    private String ua;
    private String screenSize;
    private String os;
    private String email;


    public UserBean() {
        super();
    }

    public UserBean(String nickName, String phone, String pwd, String pwd2, Integer sex, String birthday, String imei, String ua, String screenSize, String os, String email) {
        this.nickName = nickName;
        this.phone = phone;
        this.pwd = pwd;
        this.pwd2 = pwd2;
        this.sex = sex;
        this.birthday = birthday;
        this.imei = imei;
        this.ua = ua;
        this.screenSize = screenSize;
        this.os = os;
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
