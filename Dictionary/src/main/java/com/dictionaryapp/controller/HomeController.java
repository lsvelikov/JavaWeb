package com.dictionaryapp.controller;

import com.dictionaryapp.model.dtos.WordViewDto;
import com.dictionaryapp.model.enums.LanguageEnum;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final WordService wordService;

    public HomeController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String index() {

        if (loggedUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        if (!loggedUser.isLoggedIn()) {
            return "redirect:/";
        }
        List<WordViewDto> germanWordsList = wordService.findAllWordsByLanguage(LanguageEnum.GERMAN);
        List<WordViewDto> spanishWordsList = wordService.findAllWordsByLanguage(LanguageEnum.SPANISH);
        List<WordViewDto> frenchWordsList = wordService.findAllWordsByLanguage(LanguageEnum.FRENCH);
        List<WordViewDto> italianWordsList = wordService.findAllWordsByLanguage(LanguageEnum.ITALIAN);

        Long allWordsCount = wordService.allWordsCount();

        model.addAttribute("germanWordsList", germanWordsList);
        model.addAttribute("spanishWordsList", spanishWordsList);
        model.addAttribute("frenchWordsList", frenchWordsList);
        model.addAttribute("italianWordsList", italianWordsList);
        model.addAttribute("allWordsCount", allWordsCount);

        return "home";

    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {

        if (!loggedUser.isLoggedIn()) {
            return "index";
        }

        wordService.deleteById(id);

        return "redirect:/home";
    }

    @GetMapping("/remove-all")
    public String removeAll() {

        if (!loggedUser.isLoggedIn()) {
            return "index";
        }

        wordService.deleteAll();
        return "redirect:/home";
    }
}
