package com.dictionaryapp.controller;

import com.dictionaryapp.model.dtos.AddWordDto;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/words")
public class WordController {

    private final LoggedUser loggedUser;
    private final WordService wordService;

    public WordController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("add")
    public String addWord() {
        if (!loggedUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "word-add";
    }

    @PostMapping("/add")
    public String addWordConfirm(@Valid AddWordDto addWordDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addWordDto", addWordDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addWordDto", bindingResult);
            return "redirect:add";
        }

        this.wordService.addWords(addWordDto, loggedUser.getId());
        return "redirect:/";
    }

    @ModelAttribute
    public AddWordDto addWordDto() {
        return new AddWordDto();
    }
}
