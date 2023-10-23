package com.dictionaryapp.model.dtos;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AddWordDto {

    @Size(min = 2, max = 40, message = "Term length must be between 2 and 40 characters")
    @NotNull
    private String term;
    @Size(min = 2, max = 80, message = "Term length must be between 2 and 80 characters")
    @NotNull
    private String translation;
    @Size(min = 2, max = 200, message = "Term length must be between 2 and 200 characters")
    private String example;
    @PastOrPresent(message = "The input date must be in the past or present")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inputDate;
    @NotNull(message = "You must select Language")
    private LanguageEnum language;

    public AddWordDto() {
    }

    public String getTerm() {
        return term;
    }

    public AddWordDto setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public AddWordDto setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public AddWordDto setExample(String example) {
        this.example = example;
        return this;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public AddWordDto setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public AddWordDto setLanguage(LanguageEnum language) {
        this.language = language;
        return this;
    }
}
