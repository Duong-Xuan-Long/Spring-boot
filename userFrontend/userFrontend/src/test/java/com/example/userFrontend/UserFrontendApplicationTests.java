package com.example.userFrontend;

import com.example.userFrontend.dto.UserDto;
import com.example.userFrontend.service.FileService;
import com.example.userFrontend.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserFrontendApplicationTests {
	@Autowired
	private UserService userService;
	@Autowired
	FileService fileService;
	@Test
	void get_all_user(){
		List<UserDto> userDtos=userService.getAll();
		userDtos.forEach(System.out::println);
		Assertions.assertThat(userDtos).isNotNull();
		Assertions.assertThat(userDtos.size()).isEqualTo(25);
	}
	@Test
	void get_extension_file_test(){
		String rs=fileService.getExtensionFIle("avatar.png");
		System.out.println(rs);
	}
}
