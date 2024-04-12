package com.erpinterns.java_erpinterns.models;

public class HeadMasterDepartment {
    private int id;
    private int userId;
    private String professionalEmail;
    private String protel;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getProfessionalEmail() {
        return professionalEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProfessionalEmail(String professionalEmail) {
        this.professionalEmail = professionalEmail;
    }

    public void setProtel(String protel) {
        this.protel = protel;
    }

    public String getProtel() {
        return protel;
    }

    public HeadMasterDepartment(int userId, String professionalEmail, String protel) {
        this.userId = userId;
        this.professionalEmail = professionalEmail;
        this.protel = protel;
    }

    // Getters and setters
}
