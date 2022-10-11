package com.example.SignInSecurity.repository;

import com.example.SignInSecurity.entity.TokenConfirm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenConfirmRepository extends JpaRepository<TokenConfirm,Integer> {
    Optional<TokenConfirm> findByToken(String token);
}
