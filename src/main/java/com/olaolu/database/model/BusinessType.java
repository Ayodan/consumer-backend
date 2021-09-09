package com.olaolu.database.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BusinessType {
    @Id
    @GeneratedValue
    private int id;

    @NotEmpty(message = "Provide Business type name")
    @Size(min = 2)
    private String name;

    @NotEmpty(message = "Provide business type description")
    @Size(min = 2)
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
