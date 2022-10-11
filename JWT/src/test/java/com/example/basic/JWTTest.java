package com.example.basic;

import com.example.basic.entity.User;
import com.example.basic.repository.UserRepository;
import com.example.basic.security.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JWTTest {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Test
    void generate_token_test(){
        User user=userRepository.findByEmail("hien@gmail.vn").get();
        String token=jwtUtils.generateToken(user);
        System.out.println(token);
    }
}
