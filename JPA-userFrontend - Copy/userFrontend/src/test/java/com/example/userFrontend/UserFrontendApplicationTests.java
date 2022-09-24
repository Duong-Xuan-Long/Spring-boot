package com.example.userFrontend;

import com.example.userFrontend.dto.UserDto;
import com.example.userFrontend.entity.User;
import com.example.userFrontend.repository.UserRepository2;
import com.example.userFrontend.service.FileService;
import com.example.userFrontend.service.UserService;
import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class UserFrontendApplicationTests {
	@Autowired
	private UserService userService;
	@Autowired
	FileService fileService;
	@Autowired
	private Faker faker;
	@Autowired
	UserRepository2 userRepository2;
	@Test
	void save_user(){
		Random rd=new Random();
		List<String> cities=new ArrayList<>(List.of("Thành phố Hà Nội","Thành phố Đà Nẵng","Thành phố Hồ Chí Minh"));
		for(int i=1;i<=25;i++){
			int rdIndex=rd.nextInt(cities.size());
			String rdCity=cities.get(rdIndex);
			User user=User.builder()
					.id(i)
					.name(faker.name().fullName())
					.email(faker.internet().emailAddress())
					.phone(faker.phoneNumber().phoneNumber())
					.password("111")
					.avatar(faker.company().logo())
					.address(rdCity)
					.build();
			userRepository2.save(user);
		}
	}
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
