package com.medication.app.dto;

public class LoginDTO{

    private String name;
    private String password;
    private String role;

    public LoginDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public LoginDTO() {
    }

    public LoginDTO(String name, String password, String role) {
        this.name = name;
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
