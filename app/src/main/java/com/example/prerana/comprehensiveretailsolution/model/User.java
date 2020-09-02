package com.example.prerana.comprehensiveretailsolution.model;

public class User {

    private String _id;
    private String businessName;
    private String fullName;
    private String gender;
    private String country;
    private String contactNumber;
    private String address;
    private String emailId;
    private String password;
    private String image;

    String status;
    boolean sucess=false;

    public User()
    {

    }

    public User(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public User(String businessName, String fullName, String gender, String country, String contactNumber, String address, String emailId, String password) {
        this.businessName = businessName;
        this.fullName = fullName;
        this.gender = gender;
        this.country = country;
        this.contactNumber = contactNumber;
        this.address = address;
        this.emailId = emailId;
        this.password = password;
    }

    public User(String id, String businessName, String fullName, String gender, String country, String contactNumber, String address, String emailId, String password, String image) {
        this._id = id;
        this.businessName = businessName;
        this.fullName = fullName;
        this.gender = gender;
        this.country = country;
        this.contactNumber = contactNumber;
        this.address = address;
        this.emailId = emailId;
        this.password = password;
        this.image = image;
    }

    public User(String businessName, String fullName, String gender, String country, String contactNumber, String address, String emailId, String password, String image) {
        this.businessName = businessName;
        this.fullName = fullName;
        this.gender = gender;
        this.country = country;
        this.contactNumber = contactNumber;
        this.address = address;
        this.emailId = emailId;
        this.password = password;
        this.image = image;
    }

    public User(String businessName, String fullName, String gender, String country, String contactNumber, String address, String emailId) {
        this.businessName = businessName;
        this.fullName = fullName;
        this.gender = gender;
        this.country = country;
        this.contactNumber = contactNumber;
        this.address = address;
        this.emailId = emailId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
