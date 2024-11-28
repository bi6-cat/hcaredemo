package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.dto.auth.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String login(Model model) {
        if (!model.containsAttribute("loginDTO")) {
            model.addAttribute("loginDTO", new LoginDTO());
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginPost(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        if (!authService.login(loginDTO)) {
            model.addAttribute("error", "Invalid username or password");
            return "auth/login";
        }
        return "redirect:/";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "auth/register";
    }

    // Post Mapping for register form submission is handled by Spring Security
    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid RegisterDTO registerDTO,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        // Register user
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
}