package com.example.BackEndStandBlog.repository;

import com.example.BackEndStandBlog.entity.IdentityCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityCardRepository extends JpaRepository<IdentityCard, Integer> {
}