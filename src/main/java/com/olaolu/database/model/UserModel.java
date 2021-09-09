package com.olaolu.database.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author akano.olanrewaju  @on 27/01/2020
 */
public class UserModel {
    @NotBlank
    @Size(min = 2, message = "Username can not be empty")
    private String username;
    @NotBlank
    @Size(min = 2, message = "Password can not be empty")
    private int id;
    private String password;
    private String NAME;
    private List<UserRole> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    UserModel() {
    }

    public UserModel(String userName, String PASSWORD, List<UserRole> roles) {
        this.username = userName;
        this.password = PASSWORD;
        this.roles = roles;
    }
}
