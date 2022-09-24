#### 1. Thuộc tính name trong annotation @Entity khác với thuộc tính name trong @Table như thế nào? Hãy giải thích rõ cần thì minh hoạ

```java
@Entity(name = "someThing") => Được dùng để đặt tên Entity
@Table(name = "someThing")  => Được dùng để đặt tên một bảng DB
```

Trong trường hợp đâu tiên table và entity sẽ có cùng tên, điều này sẽ cho phép bạn truy cập table với tên giống với tên của entity trong khi viết HQL và JPQL.

And in second case while writing queries you have to use the name given in @Entity and the name given in @Table will be used to name the table in the DB.
Và trong trường hợp thứ hai trong khi viết query bạn có thể dùng "name" ở @Entity và "name" ở trong @Table sẽ được sử dụng để đặt tên bảng trong database.

So in HQL your someThing will refer to otherThing in the DB.
Vì vậy HQL something sẽ đề cập tới otherThing trong database.

#### 2.Để debug câu lệnh SQL mà Hibernate sẽ sinh ra trong quá trình thực thi, cần phải bổ sung lệnh nào vào file application.properties?

Dùng câu này : 
```java
spring.jpa.hibernate.use-new-id-generator-mappings=false
```
#### 3.Annotation @Column dùng để bổ sung tính chất cho cột ứng với một thuộc tính. Tham số nào trong @Column sẽ đổi lại tên cột nếu muốn khác với tên thuộc tính, tham số nào chỉ định yêu cầu duy nhất, không được trùng lặp dữ liệu, tham số nào buộc trường không được null?

Tham số trong @Column sẽ đổi lại tên cột:"name";
Tham số sẽ chỉ định yêu cầu duy nhất:"unique";
Tham số sẽ buộc trường không được null:nullable;

#### 4. Có 2 sự kiện mà JPA có thể bắt được, viết logic bổ sung:

#### Ngay trước khi đối tượng Entity lưu xuống CSDL (ngay trước lệnh INSERT)

#### Ngay trước khi đối tượng Entity cập nhật xuống CSDL (ngay trước lệnh UPDATE)
#### Vậy 2 annotation này là gì

Hai annotation này là 
```java
@PrePersist và @PreUpdate
```
#### 5.JpaRepository là một interface có sẵn trong thư viện JPA, nó cũng cấp các mẫu hàm thuận tiện cho thao tác dữ liệu. Cụ thể JpaRepository kế thừa từ interface nào?
Từ interface: 
```java
PagingAndSortingRepository<T, ID>
```
#### 6.Hãy viết khai báo một interface repository thao tác với một Entity tên là Post, kiểu dữ liệu trường Identity là long, tuân thủ interface JpaRepository.
```java
public interface PostRepository extends JpaRepository<Post, Integer> {}
```
#### 7.Khi đã chọn một cột là Identity dùng @Id để đánh dấu, thì có cần phải dùng xác định unique dùng annotation @Column(unique=true) không?
Không
#### 8.
```java
List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);

    List<Employee> findDistinctByFirstNameOrLastName(String firstName, String lastName);

    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);

    List<Employee> findByFirstNameAllIgnoreCase(String firstName);
```
#### 9.Hãy nêu cách sử dụng của @NamedQuery và @Query. Cho ví dụ
@NameQuery được dùng để định nghĩa mooth query được đặt tên.
Ví dụ: 
```java
@NamedQuery(  
        name = "findEmployeeByName",  
        query = "from Employee e where e.name = :name"  
        )  
```
#### 10.Làm thế nào để có thể viết custom method implemetations cho Jpa Repository. Cho ví dụ
Tạo customRepo
```java
public interface CustomRepo<T>{  
  void refresh(T entity);  
}
```
Tạo class để triển khai phương thức
```java
public class CustomRepoImpl<T> implements CustomRepo<T>{
  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional
  public void refresh(T entity) {
    em.refresh(entity);    
  }  
}
Tạo ra interface  repository ứng với từng Entity cụ thể
```java
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, 
                                          CustomRepo<Person>{
  Optional<Person> findByName(String name);
}
@Repository
public interface CarRepository extends JpaRepository<Car, Long>, 
CustomRepo<Car>{
  
}
```
inject bean
```java
class RepositoryTest {
	@Autowired
	PersonRepository personRepo;

	@Autowired
	CarRepository carRepo;
}
```
#### 11.Hãy nêu 1 ví dụ sử dụng sorting và paging khi query đối tượng Employee ở trên
```java
@Test
	void pagination_user_test(){
		Page<User> page=userRepository.findAll(PageRequest.of(0,5,Sort.by("id").descending()));
		page.getContent().forEach(System.out::println);
	}
```





