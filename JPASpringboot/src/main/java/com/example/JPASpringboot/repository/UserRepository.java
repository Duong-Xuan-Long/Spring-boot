package com.example.JPASpringboot.repository;

import com.example.JPASpringboot.dto.UserDto;
import com.example.JPASpringboot.dto.UserInfo;
import com.example.JPASpringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    //1.Query method
    List<User> findByNameContainingIgnoreCase(String name);

    List<User> getByAgeGreaterThan(Integer age);

    List<User> getByAgeGreaterThanOrderByAgeAsc(Integer age);

    boolean existsByEmailIgnoreCase(String email);

    void deleteByEmailIgnoreCase(String email);

    User getUserById(Integer id);

    //JPA querry language
    @Query("select u from User u where u.id = ?1")
    User test(Integer id);

    @Query("select u from User u where u.id = :id")
    User test1(@Param("id") Integer id);
    //3.Native query
    @Query(nativeQuery = true,value="select * from user u where u.id=?1")
    User test2(Integer id);
    //2.Jpa querry language->Dto
    @Query("select new com.example.JPASpringboot.dto.UserDto(u.id,u.name,u.email) from User u where u.id = ?1")
    UserDto getUserDtoById(Integer id);
    //2.JPA QUery Language->Projection
    @Query
    UserInfo getUserInfoById(Integer id);
    //dinh nghia
    @Query(nativeQuery = true,name="getUserByIdUsingNativeQuery")
    UserDto getUserByIdUsingNativeQuery(Integer id);
    //Su dung EntityManager
    //Su dung Custom Repository

    //Update
    @Modifying
    @Transactional
    @Query("update User u set u.name=?2 where u.id = ?1")
    void updateUser(Integer id,String name);
}