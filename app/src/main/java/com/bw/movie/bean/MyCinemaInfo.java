package com.bw.movie.bean;

public class MyCinemaInfo {
    private String address;
    private String businessHoursContent;
    private int commentTotal;
    private int distance;
    private boolean followCinema;
    private int id;
    private String logo;
    private String name;
    private String phone;
    private String vehicleRoute;

    public MyCinemaInfo(String address, String businessHoursContent, int commentTotal, int distance, boolean followCinema, int id, String logo, String name, String phone, String vehicleRoute) {
        this.address = address;
        this.businessHoursContent = businessHoursContent;
        this.commentTotal = commentTotal;
        this.distance = distance;
        this.followCinema = followCinema;
        this.id = id;
        this.logo = logo;
        this.name = name;
        this.phone = phone;
        this.vehicleRoute = vehicleRoute;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessHoursContent() {
        return businessHoursContent;
    }

    public void setBusinessHoursContent(String businessHoursContent) {
        this.businessHoursContent = businessHoursContent;
    }

    public int getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(int commentTotal) {
        this.commentTotal = commentTotal;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isFollowCinema() {
        return followCinema;
    }

    public void setFollowCinema(boolean followCinema) {
        this.followCinema = followCinema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicleRoute() {
        return vehicleRoute;
    }

    public void setVehicleRoute(String vehicleRoute) {
        this.vehicleRoute = vehicleRoute;
    }
}
