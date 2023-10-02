package com.softuni.mobile.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    public String getName() {
        return name;
    }

    public Brand setName(String name) {
        this.name = name;
        return this;
    }
}
