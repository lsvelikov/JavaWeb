package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enums.LanguageEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity {

    @Column(unique = true, nullable = false)
    private LanguageEnum name;
    @Column(nullable = false)
    private String description;

    public Language() {
    }

    public LanguageEnum getName() {
        return name;
    }

    public Language setName(LanguageEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Language setDescription(String description) {
        this.description = description;
        return this;
    }
}
