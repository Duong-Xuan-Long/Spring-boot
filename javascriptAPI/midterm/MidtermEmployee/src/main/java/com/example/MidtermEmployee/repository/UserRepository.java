package com.example.MidtermEmployee.repository;

import com.example.MidtermEmployee.dto.UserDto;
import com.example.MidtermEmployee.dto.UserInfo;
import com.example.MidtermEmployee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findById(Long aLong);
    @Query("select new com.example.MidtermEmployee.dto.UserDto(u.id,u.name,u.email) from User u where u.id = ?1")
    UserDto getUserDtoById(Long id);
    @Query
    UserInfo getUserInfoById(Long id);

    @Query(nativeQuery = true,name="getUserByIdUsingNativeQuery")
    UserDto getUserByIdUsingNativeQuery(Long id);
}
