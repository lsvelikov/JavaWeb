package com.dictionaryapp.controller;

import com.dictionaryapp.model.dtos.UserLoginDto;
import com.dictionaryapp.model.dtos.UserRegisterDto;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final LoggedUser loggedUser;

    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/register")
    public String register() {
        if (loggedUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
            return "redirect:register";
        }

        boolean isRegister = userService.registerUser(userRegisterDto);

        if (isRegister) {
            return "redirect:login";
        } else {
            return "redirect:register";
        }
    }

    @GetMapping("/login")
    public String login() {
        if (loggedUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDto userLoginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto", bindingResult);
            return "redirect:login";
        }
        boolean validateCredentials = this.userService.checkCredentials(userLoginDto.getUsername(), userLoginDto.getPassword());
        if (!validateCredentials) {
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto)
                    .addFlashAttribute("validCredentials", false);
            return "redirect:login";
        }

        userService.loginUser(userLoginDto.getUsername());
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logoutConfirm(HttpSession httpSession) {
        httpSession.invalidate();

        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterDto userRegisterDto() {
        return new UserRegisterDto();
    }

    @ModelAttribute
    public UserLoginDto userLoginDto() {
        return new UserLoginDto();
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }
}
