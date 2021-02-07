package com.medication.app.dto;


import java.util.Date;

public class UserDTO extends BaseDTO<Long> {

    private String name;
    private String password;
    private Date birthdate;
    private String gender;
    private String address;
    private String role;


    public UserDTO(Long id, String name, String password) {
        super(id);
        this.name = name;
        this.password = password;
    }

    public UserDTO(){

    }

    public UserDTO(String name, String password, Date birthdate, String gender, String address, String role) {
        this.name = name;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.role = role;
    }

    public UserDTO(Long id, String name, String password, Date birthdate, String gender, String address, String role) {
        super(id);
        this.name = name;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.role = role;
    }

    public UserDTO(Long id, String name, String password, Date birthdate, String gender, String address) {
        super(id);
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserDTO userDTO = (UserDTO) o;
//        return age == userDTO.age &&
//                Objects.equals(name, userDTO.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, age);
//    }
}
