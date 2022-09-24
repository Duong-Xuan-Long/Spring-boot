package com.example.userFrontend.repository;

import com.example.userFrontend.database.FakeDB;
import com.example.userFrontend.exception.NotFoundException;
import com.example.userFrontend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
@Autowired
private UserRepository2 userRepository2;
    public List<User> getAll(){
        return userRepository2.findAll();
    }

    public List<User> findUserByNameContainingIgnorecase(String name) {
        return userRepository2.findUserByNameContainingIgnorecase(name);
    }
    //Lay thiing tin user theo= id
    public Optional<User> findByid(int id){
        return userRepository2.findById(id);
    }
    //Xoa user
    public void delete(User user){
        userRepository2.delete(user);
    }
    //Xoa theo id
    public void delete(Integer id){
        Optional<User> userOptional=userRepository2.findById(id);
        if(!userOptional.isPresent()){
            throw new NotFoundException("khong ton tai id="+id);
        }
       //userRepository2.deleteById(id);
        userRepository2.delete(userOptional.get());
    }
}
