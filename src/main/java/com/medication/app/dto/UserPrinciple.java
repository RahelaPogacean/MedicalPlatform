package com.medication.app.dto;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class UserPrinciple extends org.springframework.security.core.userdetails.User {
    private String username;
    @JsonIgnore
    private String password;
    private String birthDate;
    private String gender;
    private String address;
    private String role;

    public UserPrinciple(String username,
                         String password,
                         boolean enabled,
                         boolean accountNonExpired,
                         boolean credentialsNonExpired,
                         boolean accountNonLocked,
                         String birthDate,
                         String gender,
                         String address,
                         String role,
                         Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.role = role;
    }

    public static UserPrinciple convert(com.medication.app.entity.hospital.User user) {
        return new UserPrinciple(
                user.getName(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                user.getBirthdate().toString(),
                user.getGender(),
                user.getAddress(),
                user.getRole(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

}

