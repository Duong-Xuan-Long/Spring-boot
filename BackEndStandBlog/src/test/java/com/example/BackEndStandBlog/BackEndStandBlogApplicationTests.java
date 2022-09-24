package com.example.BackEndStandBlog;

import com.example.BackEndStandBlog.dto.BlogInfo;
import com.example.BackEndStandBlog.entity.*;
import com.example.BackEndStandBlog.repository.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@SpringBootTest
class BackEndStandBlogApplicationTests {
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private IdentityCardRepository identityCardRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Faker faker;
	@Autowired
	private Random rd;

	@Test
	void save_user() {
		for(int i=0;i<5;i++){
			User user=User.builder()
					.name(faker.name().fullName())
					.email(faker.internet().emailAddress())
					.identityCard(new IdentityCard())
					.build();
			userRepository.save(user);
		}
	}
	@Test
	void Save_image(){
		//Lay danh sach user
		List<User> users=userRepository.findAll();
		for(int i=0;i<50;i++){
			//Random ra 1 user
			User rUser=users.get(rd.nextInt(users.size()));
			//Tao image
			Image image= Image.builder()
					.url(faker.company().logo())
					.user(rUser)
					.build();
			imageRepository.save(image);
		}
	}
	@Test
	void save_avatar_user(){
		List<User> users=userRepository.findAll();
		users.forEach(user->{
			//Lay danh sach image cua user do
			List<Image> images=imageRepository.getByUser_id((user.getId()));
			//Random ra 1 image
			String link=images.get(rd.nextInt(images.size())).getUrl();
			//Set avatar cho user
			user.setAvatar(link);
			userRepository.save(user);
		});
	}
	@Test
	void save_category(){
		for(int i=0;i<5;i++){
			Category category=Category.builder()
					.name(faker.leagueOfLegends().champion())
					.build();
			categoryRepository.save(category);
		}
	}
	@Test
	void save_blog(){
		for(int i=0;i<30;i++){
			List<User> users=userRepository.findAll();
			List<Category> categories=categoryRepository.findAll();
				//Random ra 1 user
				User rUser=users.get(rd.nextInt(users.size()));
			//Random 1 set category
			Set<Category> rdCategories=new HashSet<>();
			for(int j=0;j<3;j++){
				Category rdCategory=categories.get(rd.nextInt(categories.size()));
				rdCategories.add(rdCategory);
			}
			List<Image> images=imageRepository.getByUser_id((rUser.getId()));
			//Random ra 1 image
			String link=images.get(rd.nextInt(images.size())).getUrl();

			Blog blog=Blog.builder()
					.title(faker.lorem().sentence(10))
					.description(faker.lorem().sentence(30))
					.content(faker.lorem().sentence(100))
					.status(rd.nextInt(2)==0)
					.categories(rdCategories)
					.user(rUser)
					.thumbnail(link)
					.build();
			blogRepository.save(blog);
		}
	}
	@Test
	void save_comment(){
		List<User> users=userRepository.findAll();

		List<Blog> blogs=blogRepository.findAll();

		for(int i=0;i<100;i++){
			User rdUser=users.get(rd.nextInt(users.size()));
			Blog rdblog=blogs.get(rd.nextInt(blogs.size()));
			Comment comment=Comment.builder()
					.content(faker.lorem().sentence(20))
					.blog(rdblog)
					.user(rdUser)
					.build();
			commentRepository.save(comment);
		}
	}
//	@Test
//	void getBlogsByStatusDESC(){
//		List<Blog> blogs=blogRepository.test();
//		blogs.forEach(blog->System.out.println(blog.getTitle()));
////		Page<Blog> page=blogRepository.findAll(PageRequest.of(0,5, Sort.by("id").descending()));
////		page.getContent().forEach(System.out::println);
//	}
	@Test
	void getTopCommentBlog(){
		List<Blog> blogs=blogRepository.test1();
		blogs.forEach(blog->System.out.println(blog.getTitle()));
	}
	@Test
	void getTop5Categories(){
		List<Category> catregory=categoryRepository.getTop5Categories();
		catregory.forEach(c->System.out.println(c));
	}
	@Test
	void getBlogByCategoryId(){
		List<Blog> blogs=blogRepository.getBlogByCategoryId(1);
		blogs.forEach(blog->System.out.println(blog.getTitle()));
	}
	@Test
	void getUserByBlogId(){
		User user=userRepository.getUserByBlogId(1);
		System.out.println(user);
	}
	@Test
	void getBlogByUserId(){
		List<Blog> blogs=blogRepository.getBlogByUserId(1);
		blogs.forEach(b-> System.out.println(b.getTitle()));
	}
	@Test
	void getAllCommentByBlogId(){
		List<Comment> comments=commentRepository.getAllCommentByBlogId(1);
		comments.forEach(c-> System.out.println(c.toString()));
	}
	@Test
	void getBlogByid(){
		Blog blog=blogRepository.getBlogBy_Id(1);
		System.out.println(blog.getTitle());
	}
	@Test
	void getBlogByIdWithUserAndComment(){
		List<Blog> blogs=blogRepository.getBlogByIdWithUserAndComment(1);
	}
	@Test
	void getAllBlogInfo(){
		List<BlogInfo> blogInfos=blogRepository.getAllBlogInfo();
		blogInfos.forEach(System.out::println);
	}

}
