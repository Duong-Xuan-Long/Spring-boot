package com.example.userFrontend.repository;

import com.example.userFrontend.database.FakeDB;
import com.example.userFrontend.exception.NotFoundException;
import com.example.userFrontend.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    public User save(User user){
        FakeDB.users.add(user);
        return user;
    }
    public List<User> getAll(){
        return FakeDB.users;
    }

    public List<User> findUserByNameContainingIgnorecase(String name) {
        return FakeDB.users.stream()
                .filter(user->user.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    //Lay thiing tin user theo= id
    public Optional<User> findByid(int id){
        return FakeDB.users.stream()
                .filter(user->user.getId()==id)
                .findFirst();
    }
    //Xoa user
    public void delete(User user){
        FakeDB.users.remove(user);
    }
    //Xoa theo id
    public void delete(int id){
        Optional<User> userOptional=findByid(id);
        if(!userOptional.isPresent()){
            throw new NotFoundException("khong ton tai id="+id);
        }
        FakeDB.users.removeIf(user->user.getId()==id);
    }
}
