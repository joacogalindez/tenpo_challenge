package com.challenge.application.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table (name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "names")
    private String names;
    @Column(name = "last_names")
    private String lastNames;
    @Column(name = "phone_number")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("names")
    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    @JsonProperty("last_names")
    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isInvalidUser() {
        if (this.email == null || this.password == null) {
            return true;
        } else if(this.email.length() == 0 || this.password.length() == 0 ) {
            return true;
        }
        return false;
    }
}
