package com.zett.hcaredemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(Model model) {
        // Add any attributes needed for the home page
        return "home/index";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        // Add any attributes needed for the contact page
        return "home/contact";
    }

    @GetMapping("/services")
    public String services(Model model) {
        // Add any attributes needed for the services page
        return "home/services";
    }

    @GetMapping("/about")
    public String about(Model model) {
        // Add any attributes needed for the doctors page
        return "home/about";
    }
}