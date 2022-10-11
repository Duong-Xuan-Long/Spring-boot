package com.example.MidtermEmployee;

import com.example.MidtermEmployee.dto.CourseInfo;
import com.example.MidtermEmployee.dto.UserDto;
import com.example.MidtermEmployee.dto.UserInfo;
import com.example.MidtermEmployee.entity.*;
import com.example.MidtermEmployee.repository.*;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MidtermEmployeeApplicationTests {
	@Autowired
	private Faker faker;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	Random rd;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	CourseStudentRepository courseStudentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
//	@Autowired
//	Test1 test;
	@Test
	void buildEmployee(){
		for(int i=0;i<10;i++){
			Employee employee=Employee.builder()
					.emailAddress(faker.internet().emailAddress())
					.firstName(faker.name().firstName())
					.lastName(faker.name().lastName()).build();
			employeeRepository.save(employee);
		}
	}
	@Test
	void buildCategory(){
		for(int i=0;i<5;i++){
			Category category=Category.builder()
					.name(faker.name().fullName())
					.build();
			categoryRepository.save(category);
		}
	}
	@Test
	void buildProduct(){
		List<Category> list=categoryRepository.findAll();
		Category c=list.get(rd.nextInt(list.size()));
		for(int i=0;i<10;i++){
			Product p=Product.builder()
					.name(faker.name().fullName())
					.category(c).build();
			productRepository.save(p);
		}
	}
	@Test
	void builStudent(){
		for(int i=0;i<10;i++){
			Student student=Student.builder().name(faker.name().fullName()).build();
			studentRepository.save(student);
		}
	}
	@Test
	void builCourse(){
		for(int i=0;i<10;i++){
			Course course=Course.builder().name(faker.educator().course()).build();
			courseRepository.save(course);
		}
	}
	@Test
	void builCourseStudent(){
		List<Course> courseList=courseRepository.findAll();
		List<Student> studentList=studentRepository.findAll();
		for(int i=0;i<100;i++){
			Course course=courseList.get(rd.nextInt(courseList.size()));
			Student student=studentList.get(rd.nextInt(studentList.size()));
			Integer rdd=rd.nextInt(11);
			CourseStudent courseStudent=CourseStudent.builder()
					.course(course)
					.student(student)
					.score(rdd).build();
			courseStudentRepository.save(courseStudent);
		}
	}
	@Test
	void findByEmailAddressAndLastName(){
		List<Employee> list=employeeRepository.
				findByEmailAddressAndLastName("hang.bahringer@yahoo.com","Runte");
		list.stream().forEach(e-> System.out.println(e.getFirstName()));
	}
	@Test
	void findDistinctByFirstNameOrLastName(){
		List<Employee> list=employeeRepository.findDistinctByFirstNameOrLastName("Lazaro","Runte");
		list.stream().forEach(e-> System.out.println(e.getFirstName()));
	}
	@Test
	void findByLastNameOrderByFirstNameAsc(){
		List<Employee> list=employeeRepository.findByLastNameOrderByFirstNameAsc("Runte");
		list.stream().forEach(e-> System.out.println(e.getFirstName()));
	}
	@Test
	void findByFirstNameAllIgnoreCase(){
		List<Employee> list=employeeRepository.findByFirstNameAllIgnoreCase("Lazaro");
		list.stream().forEach(e-> System.out.println(e.getFirstName()));
	}
	@Test
	void getAVG(){
		Double avgScore=courseRepository.getAVG("history");
		System.out.println(avgScore);
	}
	@Test
	void getMList(){
		List<Student> list=studentRepository.getMathList();
		list.stream().forEach(s-> System.out.println(s.getName()));
	}
	@Test
	void buildUser(){
		for(int i=0;i<10;i++){
			User user=User.builder()
					.email(faker.internet().emailAddress())
					.name(faker.name().fullName())
					.password(faker.internet().password())
					.build();
			userRepository.save(user);
		}
	}
	@Test
	void getUserBy_Dto(){
		Optional<User> user=userRepository.findById(2L);
		//UserDto userDto=modelMapper.map(user,UserDto.class);
		System.out.println(user.get().getEmail());
	}
	@Test
	void getUserBy_DtoJPQL(){
		UserDto userDto=userRepository.getUserDtoById(1L);
		System.out.println(userDto.getName());
	}
	@Test
	void getUserBy_ifoprojection(){
		UserInfo userinfo=userRepository.getUserInfoById(1L);
		System.out.println(userinfo.getName());
	}
	@Test
	void getUserBy_dtoProjection(){
		UserDto userDto=userRepository.getUserByIdUsingNativeQuery(1L);
		System.out.println(userDto);
	}
	@Test
	void generateUUDI(){
		String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
		String alphaUpperCase = alpha.toUpperCase(); // A-Z
		String digits = "0123456789"; // 0-9
		String specials = "~=+%^*/()[]{}/!@#$?|";
		String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
		String ALL = alpha + alphaUpperCase + digits + specials;
		Random generator = new Random();
		StringBuilder sb = new StringBuilder();
		int min=0;
		int max=ALPHA_NUMERIC.length() - 1;
		for (int i = 0; i < 5; i++) {
			int number = generator.nextInt((max - min) + 1);
			char ch = ALPHA_NUMERIC.charAt(number);
			sb.append(ch);
		}
	}
	@Test
	void getAllCourseInfo(){
		List<CourseInfo> list=courseRepository.getAllCourseInfo();
		list.forEach(c-> System.out.println(c.getStudentList()[0].getName()));
	}
//	@Test
//	void getMap(){
//		Map<String,List<Student>> map=studentRepository.getMap();
//	}

//	@Test
//	void contextLoads() {
//		int a=test.sum();
//		System.out.println(a);
//	}
	@Test
	void TestGetList(){
		Map<String,List<Student>> map=courseRepository.TestGetList();
	}

}
