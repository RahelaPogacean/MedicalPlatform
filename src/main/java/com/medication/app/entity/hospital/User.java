package com.medication.app.entity.hospital;

import com.medication.app.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "role")
    private String role;


    public User(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

    public User(Long id, String name, String password, Date birthdate, String gender, String address, String role) {

        this.name = name;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.role = role;

    }

    public User() {

    }

    public User(String name, String password, Date birthdate, String gender, String address, String role) {
        this.name = name;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.role = role;

    }

    public User(String name, Date birthdate, String gender, String address) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
    }

    public User(String name, Date birthdate, String gender, String address, String role) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

}
