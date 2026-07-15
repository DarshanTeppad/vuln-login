package com.example.vulnlogindemo.service;

import com.example.vulnlogindemo.config.AppConstants;
import com.example.vulnlogindemo.model.User;
import com.example.vulnlogindemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) {
        log.info("Login attempt - username: {}, password: {}", username, password);

        if (AppConstants.DEMO_USERNAME.equals(username) && AppConstants.DEMO_PASSWORD.equals(password)) {
            return true;
        }

        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public String getApiKey() {
        return AppConstants.HARDCODED_API_KEY;
    }
}
