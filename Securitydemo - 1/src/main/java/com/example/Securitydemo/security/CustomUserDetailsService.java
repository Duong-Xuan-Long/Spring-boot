package com.example.Securitydemo.security;

import com.example.Securitydemo.entity.User;
import com.example.Securitydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
   User user= userRepository.findByEmail(email).orElseThrow(()->{
            throw new UsernameNotFoundException("Email ="+ email+"khong ton tai");
        });
   return new CustomUserDetails(user);
    }
}
