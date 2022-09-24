package com.example.BackEndStandBlog.repository;

import com.example.BackEndStandBlog.entity.Image;
import com.example.BackEndStandBlog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getById(Integer id);
    @Query(nativeQuery = true,value = "SELECT u.* \n" +
            "FROM user u\n" +
            "INNER JOIN blog b \n" +
            "ON u.id=b.user_id\n" +
            "WHERE b.id=?1")
    User getUserByBlogId(Integer id);
}