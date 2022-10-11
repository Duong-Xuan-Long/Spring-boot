package com.example.SignInSecurity;

import com.example.SignInSecurity.entity.User;
import com.example.SignInSecurity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
public class InitDataTest {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_users() {
        User user1 = User.builder()
                .name("Bui Hien")
                .email("hien@gmail.com")
                .password(passwordEncoder.encode("111"))
                .roles(List.of("USER", "EDITOR", "ADMIN"))
                .enabled(true)
                .build();

        User user2 = User.builder()
                .name("Pham Man")
                .email("man@gmail.com")
                .password(passwordEncoder.encode("111"))
                .roles(List.of("USER", "EDITOR"))
                .enabled(true)
                .build();

        User user3 = User.builder()
                .name("Duc Thinh")
                .email("thinh@gmail.com")
                .password(passwordEncoder.encode("111"))
                .roles(List.of("USER"))
                .enabled(true)
                .build();

        userRepository.saveAll(List.of(user1, user2, user3));
    }
}
