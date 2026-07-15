package com.example.vulnlogindemo.config;

import com.example.vulnlogindemo.model.User;
import com.example.vulnlogindemo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername(AppConstants.DEMO_USERNAME).isEmpty()) {
            userRepository.save(new User(AppConstants.DEMO_USERNAME, AppConstants.DEMO_PASSWORD));
        }
    }
}
