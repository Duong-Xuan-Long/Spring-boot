package com.example.Securitydemo;

import com.example.Securitydemo.entity.User;
import com.example.Securitydemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class SecuritydemoApplicationTests {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Test
//	@Rollback(value = false)
	void save_users(){
		User user1=User.builder()
				.name("X1")
				.email("X1@gmail.com")
				.password(passwordEncoder.encode("111"))
				.roles(List.of("USER","EDITOR","ADMIN"))
				.build();

		User user2=User.builder()
				.name("X2")
				.email("X2@gmail.com")
				.password(passwordEncoder.encode("111"))
				.roles(List.of("USER","EDITOR"))
				.build();

		User user3=User.builder()
				.name("X3")
				.email("X3@gmail.com")
				.password(passwordEncoder.encode("111"))
				.roles(List.of("USER"))
				.build();
		userRepository.saveAll(List.of(user1,user2,user3));
	}
}
