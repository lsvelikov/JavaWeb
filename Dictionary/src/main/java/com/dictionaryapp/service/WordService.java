package com.dictionaryapp.service;

import com.dictionaryapp.model.dtos.*;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageEnum;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WordService {


    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public WordService(WordRepository wordRepository, LanguageRepository languageRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void addWords(AddWordDto addWordDto, Long id) {
        Word word = new Word();
        word.setTerm(addWordDto.getTerm());
        word.setTranslation(addWordDto.getTranslation());
        word.setExample(addWordDto.getExample());
        word.setInputDate(addWordDto.getInputDate());
        Language language = this.languageRepository.findLanguage(addWordDto.getLanguage());
        word.setLanguage(language);
        Optional<User> user = this.userRepository.findById(id);
        word.setAddedBy(user.get());
        this.wordRepository.save(word);
    }


    public List<WordViewDto> findAllWordsByLanguage(LanguageEnum languageEnum) {
        return wordRepository.findAllByLanguage_Name(languageEnum)
                .stream()
                .map(word -> modelMapper.map(word, WordViewDto.class))
                .collect(Collectors.toList());
    }

    public Long allWordsCount() {
        return wordRepository.count();
    }

    public void deleteById(Long id) {
        wordRepository.deleteById(id);
    }

    public void deleteAll() {
        wordRepository.deleteAll();
    }
}
