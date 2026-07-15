package com.example.vulnlogindemo.controller;

import com.example.vulnlogindemo.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password.");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "You have been logged out.");
        }
        return "login";
    }

    @PostMapping("/api/login-check")
    public String loginCheck(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        authService.authenticate(username, password);
        model.addAttribute("username", username);
        model.addAttribute("apiKey", authService.getApiKey());
        return "login";
    }
}
