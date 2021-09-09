package com.olaolu.database.model;

import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author akano.olanrewaju  @on 27/01/2020
 */
public class UserRole implements GrantedAuthority {
    @NotBlank
    @Size(min = 1, message = "Id can not be empty")
    private Long ID;
    @NotBlank
    @Size(min = 1, message = "Name can not be empty")
    private String NAME;

    UserRole() {
    }

    public UserRole(String name) {
        this.NAME = name;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    @Override
    public String getAuthority() {
        return NAME;
    }
}
