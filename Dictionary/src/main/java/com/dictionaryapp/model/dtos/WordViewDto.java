package com.dictionaryapp.model.dtos;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;

import java.time.LocalDate;

public class WordViewDto {

    private Long id;

    private String term;

    private String translation;

    private String example;

    private Language language;

    private LocalDate inputDate;

    private User addedBy;

    public WordViewDto() {
    }

    public Long getId() {
        return id;
    }

    public WordViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public WordViewDto setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public WordViewDto setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public WordViewDto setExample(String example) {
        this.example = example;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public WordViewDto setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public WordViewDto setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public WordViewDto setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}
