package com.example.JPASpringboot;

import com.example.JPASpringboot.dto.UserDto;
import com.example.JPASpringboot.dto.UserInfo;
import com.example.JPASpringboot.entity.User;
import com.example.JPASpringboot.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class JpaSpringbootApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Faker faker;
	@Test
	void save_user_to_db_test() {
		User user= User.builder().name("nguyen van A")
				.email("a@gmail.com")
				.age(25)
				.birth(LocalDate.now().minusYears(20))
				.build();
		User user1= User.builder().name("Tran Van B")
				.email("b@gmail.com")
				.age(30)
				.birth(LocalDate.now().minusYears(40))
				.build();
		User user2= User.builder().name("ngo thi C")
				.email("c@gmail.com")
				.age(30)
				.birth(LocalDate.now().minusYears(30))
				.build();
		//Luu tung cai
//		userRepository.save(user);
//		userRepository.save(user1);
//		userRepository.save(user2);

		userRepository.saveAll(List.of(user,user1,user2));
	}
	@Test
	void save_fake_users(){
		for(int i=0;i<50;i++){
			int age=faker.number().numberBetween(20,40);
			User user= User.builder().name(faker.name().fullName())
					.email(faker.internet().emailAddress())
					.age(age)
					.birth(LocalDate.now().minusYears(age))
					.build();
			userRepository.save(user);
		}
	}
	@Test
	void find_By_Name_test(){
		List<User> users=userRepository.findByNameContainingIgnoreCase("văn");
		users.forEach(System.out::println);
	}
	@Test
	void find_by_age_test(){
		List<User> users=userRepository.getByAgeGreaterThanOrderByAgeAsc(25);
		users.forEach(System.out::println);
	}
	@Test
	void find_by_id_test(){
		User user=userRepository.test(1);
		System.out.println(user);
	}
	@Test
	void find_by_id_native_querry(){
		User user=userRepository.test2(1);
		System.out.println(user);
	}
	@Test
	void find_by_id_dto(){
		UserDto user=userRepository.getUserDtoById(1);
		System.out.println(user);
	}
	@Test
	void find_by_id_user_dto_using_projection(){
		UserInfo user=userRepository.getUserInfoById(1);
		System.out.println(user.getName());
	}
	@Test
	void find_by_id_user_dto_using_native(){
		UserDto user=userRepository.getUserByIdUsingNativeQuery(1);
		System.out.println(user);
	}
	@Test
	void sort_user_by_name_desc() {
		List<User> users=userRepository.findAll(Sort.by("name").descending());
		users.forEach(System.out::println);
	}
	@Test
	void pagination_user_test(){
		Page<User> page=userRepository.findAll(PageRequest.of(0,5,Sort.by("id").descending()));
		page.getContent().forEach(System.out::println);
	}
	@Test
	void update_user_by_Entity(){
		User user=userRepository.getUserById(1);
		System.out.println(user);
		user.setName("other name");
		userRepository.save(user);
	}
	@Test
	void update_user_by_querry(){
		userRepository.updateUser(2,"ok1");
	}
}
