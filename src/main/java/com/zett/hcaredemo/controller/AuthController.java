package com.zett.hcaredemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zett.hcaredemo.dto.auth.ChangePasswordDTO;
import com.zett.hcaredemo.dto.auth.RegisterDTO;
import com.zett.hcaredemo.service.AuthService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid RegisterDTO registerDTO,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        var user = authService.register(registerDTO);
        
        if(user == null) {
            model.addAttribute("error", "An error occurred while registering the user");
            return "auth/register";
        }

        return "redirect:/auth/login";
    }

    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "auth/403";
    }

    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("changePasswordDTO", new ChangePasswordDTO());
        return "auth/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@ModelAttribute @Valid ChangePasswordDTO changePasswordDTO,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/change-password";
        }

        try {
            authService.changePassword(changePasswordDTO);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/change-password";
        }

        return "redirect:/auth/login";
    }
}