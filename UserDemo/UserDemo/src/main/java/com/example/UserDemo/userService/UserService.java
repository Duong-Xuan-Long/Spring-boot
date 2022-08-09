package com.example.UserDemo.userService;

import com.example.UserDemo.exception.BadRequestException;
import com.example.UserDemo.exception.NotFoundException;
import com.example.UserDemo.model.User;
import com.example.UserDemo.request.SignInRequest;
import com.example.UserDemo.userDto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    ModelMapper modelMapper;
    private List<User> list;
    public UserService(){
        list=new ArrayList<>();
        list.add(new User(1,"user1","123","user1@gmail.com","user1avatar"));
        list.add(new User(2,"user2","456","user2@gmail.com","user2avatar"));
    }
    public UserDto signIn(SignInRequest signInRequest) {
        Optional<User> userOptional=list.stream()
                .filter(u->u.getUsername().equals(signInRequest.getUsername())
                        &&u.getPassword().equals(signInRequest.getPassword()))
                .findFirst()
                ;
        if(userOptional.isPresent()){
            User user=userOptional.get();
            return modelMapper.map(user,UserDto.class);
        }
        throw new BadRequestException("username hoặc password chưa chính xác");
    }

}
