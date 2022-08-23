package com.example.userFrontend.controller;

import com.example.userFrontend.dto.UserDto;
import com.example.userFrontend.request.CreateUserRequest;
import com.example.userFrontend.request.UpDateAvatarRequest;
import com.example.userFrontend.request.UpdatePasswordResquest;
import com.example.userFrontend.request.UpdateUserRequest;
import com.example.userFrontend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
//@ResponseStatus(Http.status.CREATED) là để cài mã trạng thái
@RestController //mặc dịnh trả về kiểu dữ liệu json cong nếu dùng @controller thì phải thêm @ResponseBody để trả kiểu json
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    //Lay danh sach user
    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

//    @GetMapping("users")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<UserDto> getUsers(){
//        return userService.getAll();
//    }
    //Tim kiem user theo ten
        @GetMapping("/users/search")
        public ResponseEntity<?> searchUser(@RequestParam String name){
        return ResponseEntity.ok(userService.searchUser(name));
        }
     //Tim kiem theo id
        @GetMapping("users/{id}")
        public ResponseEntity<?> getUserId(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserId(id));
        }
    //Xoa User
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    //Tao user
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }
    //Cap nhat User
    @PutMapping("/users/{id}/update-user")
    public ResponseEntity<?> updateUser(@PathVariable int id,@RequestBody UpdateUserRequest request){
        return ResponseEntity.ok(userService.updateUser(id,request));
    }
    //QUên mật khẩu
    @GetMapping("/users/{id}/forgot-password")
    public ResponseEntity<?> forgotPassword(@PathVariable int id){
        userService.forgotPassword(id);
        return ResponseEntity.noContent().build();
    }
    //Doi mat khau
    @PutMapping("/users/{id}/update-password")
    public ResponseEntity<?> updatePassword(@PathVariable int id,@Valid @RequestBody UpdatePasswordResquest request){
        userService.updatePassWord(id,request);
        return ResponseEntity.noContent().build();
    }
    //Upload file
    @PostMapping("/users/{id}/files")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file")MultipartFile file,@PathVariable int id){
        String filePath= userService.uploadFile(id,file);
        return ResponseEntity.ok(filePath);
    }
    //Xem file
    @GetMapping("/users/{id}/files/{fileId}")//produces={MediaType.IMAGE_JPEG_VALUE}
    public ResponseEntity<?> readFile(@PathVariable int id,@PathVariable String fileId){
        byte[] bytes=userService.readFile(id,fileId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
    //Lay danh sach file
    @GetMapping("/users/{id}/files")
    public ResponseEntity<?> getFiles(@PathVariable int id){
        List<String> files=userService.getFiles(id);
        return ResponseEntity.ok(files);
    }
    //Xoa file
    @DeleteMapping("/users/{id}/files/{fileId}")
    public ResponseEntity<?> getFiles(@PathVariable int id,@PathVariable String fileId){
        userService.deleteFile(id,fileId);
        return ResponseEntity.noContent().build();
    }
    //thay doi hinh anh
    @PutMapping("/users/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable int id, @RequestBody UpDateAvatarRequest request){
        userService.updateAvatar(id,request);
        return ResponseEntity.noContent().build();
    }
}
