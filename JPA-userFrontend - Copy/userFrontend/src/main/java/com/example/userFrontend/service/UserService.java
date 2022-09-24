package com.example.userFrontend.service;

import com.example.userFrontend.database.FakeDB;
import com.example.userFrontend.dto.UserDto;
import com.example.userFrontend.exception.BadRequestException;
import com.example.userFrontend.exception.NotFoundException;
import com.example.userFrontend.entity.User;
import com.example.userFrontend.repository.UserRepository;
import com.example.userFrontend.repository.UserRepository2;
import com.example.userFrontend.request.CreateUserRequest;
import com.example.userFrontend.request.UpDateAvatarRequest;
import com.example.userFrontend.request.UpdatePasswordResquest;
import com.example.userFrontend.request.UpdateUserRequest;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRepository2 userRepository2;
    private final Faker faker;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    private final FileService fileService;
    public UserService(UserRepository userRepository,UserRepository2 userRepository2, Faker faker, ModelMapper modelMapper,EmailService emailService,FileService fileService){
        this.userRepository = userRepository;
        this.faker = faker;
        this.modelMapper = modelMapper;
        this.emailService=emailService;
        this.fileService=fileService;
        this.userRepository2=userRepository2;
    }

    public List<UserDto> getAll(){
        return userRepository2.getAllUserDto();
    }

    public Object searchUser(String name) {
        List<User> users=userRepository.findUserByNameContainingIgnorecase(name);
        return users.stream().map(user->modelMapper
                        .map(user,UserDto.class))
                .collect(Collectors.toList());
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }

    public UserDto createUser(CreateUserRequest request) {
        //kiem tra email,xem co trung nhau voi user nao do hay khong
        //Neu co Bao loi
        if(!checkEmail(request)) {
            throw new BadRequestException("email" +request.getEmail()+"nay da ton tai");
        }
        //Tao user voi cac thong tin tu request
        Random rd=new Random();
        User user=new User(rd.nextInt(26,1000),request.getName(),request.getEmail(),request.getPhone()
        , request.getAddress(), null, request.getPassword());
        FakeDB.users.add(0,user);
        //tra ve userDto sau khi tao
        return modelMapper.map(user,UserDto.class);
    }
    public boolean checkEmail(CreateUserRequest request){
        for(User user:FakeDB.users){
            if(user.getEmail().equals(request.getEmail())) return false;
        }
        return true;
    }

    public void forgotPassword(int id) {
        User user=userRepository.findByid(id).orElseThrow(()->{
           throw  new NotFoundException("khong tim thay user co id ="+id);
        });
        //Generate password
        Random rd=new Random();
        String newPassword=String.valueOf(rd.nextInt(1000));
        user.setPassword(newPassword);
        //send EMail;
        emailService.send(user.getEmail(),"Quen mat khau","Mat khau moi: "+newPassword);
    }

    public void updatePassWord(int id, UpdatePasswordResquest request) {
        User user=userRepository.findByid(id).orElseThrow(()->{
            throw  new NotFoundException("khong tim thay user co id ="+id);
        });
        if(!request.getOldPassword().equals(user.getPassword())){
            throw new BadRequestException("Mat khau cu khong dung");
        }else{
            if(request.getOldPassword().equals(request.getNewPassword())){
                throw new BadRequestException("Mat khau moi dang giong mat khau cu");
            }
            else{
                user.setPassword(request.getNewPassword());
            }
        }
    }

    public String uploadFile(int id, MultipartFile file) {
        User user=userRepository.findByid(id).orElseThrow(()->{
            throw  new NotFoundException("khong tim thay user co id ="+id);
        });
        return fileService.uploadFile(id,file);
    }

    public byte[] readFile(int id, String fileId) {
        User user=userRepository.findByid(id).orElseThrow(()->{
            throw  new NotFoundException("khong tim thay user co id ="+id);
        });
        return fileService.readFile(id,fileId);
    }

    public List<String> getFiles(int id) {
        User user=userRepository.findByid(id).orElseThrow(()->{
            throw  new NotFoundException("khong tim thay user co id ="+id);
        });
        return fileService.getFiles(id);
    }

    public void deleteFile(int id, String fileId) {
        User user=userRepository.findByid(id).orElseThrow(()->{
            throw  new NotFoundException("khong tim thay user co id ="+id);
        });
        fileService.deleteFile(id,fileId);
    }

    public void updateAvatar(int id, UpDateAvatarRequest request) {
        User user=userRepository.findByid(id).orElseThrow(()->{
            throw  new NotFoundException("khong tim thay user co id ="+id);
        });
        user.setAvatar(request.getAvatar());
    }

    public User updateUser(int id, UpdateUserRequest request) {
        User user=userRepository.findByid(id).orElseThrow(()->{
            throw  new NotFoundException("khong tim thay user co id ="+id);
        });
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        return user;
    }

    public UserDto getUserId(int id) {
        User user=userRepository.findByid(id).orElseThrow(()->{
            throw  new NotFoundException("khong tim thay user co id ="+id);
        });
        return modelMapper.map(user,UserDto.class);
    }
}
