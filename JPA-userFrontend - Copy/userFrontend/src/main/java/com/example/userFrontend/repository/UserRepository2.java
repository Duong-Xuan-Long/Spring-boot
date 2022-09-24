package com.example.userFrontend.repository;

import com.example.userFrontend.dto.UserDto;
import com.example.userFrontend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository2 extends JpaRepository<User,Integer> {
    List<User> findUserByNameContainingIgnorecase(String name);

    @Query("select new com.example.userFrontend.dto.UserDto(u.id,u.name,u.email,u.phone,u.address,u.avatar,u.password) from User u where u.id = ?1")
    UserDto getUserDtoById(Integer id);

    @Query("select new com.example.userFrontend.dto.UserDto(u.id,u.name,u.email,u.phone,u.address,u.avatar,u.password) from User u ")
    List<UserDto> getAllUserDto();
}
