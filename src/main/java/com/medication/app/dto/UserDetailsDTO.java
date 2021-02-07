package com.medication.app.dto;


import java.util.Date;

public class UserDetailsDTO extends BaseDTO<Long>{

    private String name;
    private Date birthdate;
    private String gender;
    private String address;

    public UserDetailsDTO(String name, Date birthdate, String gender, String address) {
        super();
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;

    }

    public  UserDetailsDTO(){

    }

    public UserDetailsDTO(String name, String gender, Date birthdate, String gender1, String address) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
