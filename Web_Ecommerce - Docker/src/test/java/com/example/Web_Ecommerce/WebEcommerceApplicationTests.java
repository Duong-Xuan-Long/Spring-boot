package com.example.Web_Ecommerce;

import com.example.Web_Ecommerce.dto.CustomerDto;
import com.example.Web_Ecommerce.dto.ProductDto;
import com.example.Web_Ecommerce.model.Category;
import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.model.Product;
import com.example.Web_Ecommerce.model.ShoppingCart;
import com.example.Web_Ecommerce.repository.CartItemRepository;
import com.example.Web_Ecommerce.repository.CustomerRepository;
import com.example.Web_Ecommerce.repository.ShoppingCartRepository;
import com.example.Web_Ecommerce.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class WebEcommerceApplicationTests {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	OrderService orderService;
	@Autowired
	PasswordEncoder bCryptPasswordEncoder;
	@Autowired
	CustomerService customerService;
	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	ProductService productService;
	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	CategoryService categoryService;


	@Test
	void delete() {
		Customer customer = customerRepository.findById(6L).get();
		orderService.saveOrder(customer.getShoppingCart());

	}

	@Test
	void save() {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setFirstName("customerNew");
		customerDto.setLastName("customerNew");
		customerDto.setUsername("customerNew@gmail.com");
		customerDto.setPassword(bCryptPasswordEncoder.encode("123456"));
		customerService.save(customerDto);
	}

	@Test
	void findCustomerByUsername() {
		customerService.findByUsername("customer1");
	}

	@Test
	void updateCustomer() {
		Customer customer = customerRepository.findById(9L).get();
		customerService.update(null, customer);
	}

	@Test
	void addItemToCart() {
		Product product = productService.getProductById(1L);
		Customer customer = customerRepository.findById(6L).get();
		shoppingCartService.addItemtoCart(product, 1, customer);
	}

	@Test
	void updateItem() {
		Product product = productService.getProductById(1L);
		Customer customer = customerRepository.findById(6L).get();
		shoppingCartService.updateItemInCart(product, 2, customer);
	}

	@Test
	void deleteItem() {
		Product product = productService.getProductById(1L);
		Customer customer = customerRepository.findById(6L).get();
		shoppingCartService.deleteItemInCart(product, customer);
	}

	@Test
	void acceptOrder() {
		orderService.acceptOrder(17L);
	}

	@Test
	void deleteOrder() {
		orderService.cancelOrder(17L);
	}

	@Test
	void deleteShoppingcart(){
		ShoppingCart shoppingCart=shoppingCartRepository.findById(24L).get();
		shoppingCartRepository.delete(shoppingCart);
	}
	@Test
	void deleteCustomer(){
		Customer customer=customerRepository.findById(16L).get();
		customerRepository.delete(customer);
	}
	@Test
	void findALlCategory() {
		categoryService.findAll();
	}

	@Test
	void saveCategory() {
		Category category = new Category();
		category.setName("sofa");
		categoryService.save(category);
	}

	@Test
	void findById() {
		categoryService.findById(2L);
	}

	@Test
	void update() {
		Category category = categoryService.findById(3L);
		category.setName("Sofa");
		categoryService.update(category);
	}

	@Test
	void deleteById() {
		categoryService.deleteById(3L);
	}

	@Test
	void enableById() {
		categoryService.enabledById(3L);
	}

	@Test
	void findAllByActivated() {
		categoryService.findAllByActivated();
	}

	@Test
	void getCategoryDto() {
		categoryService.getCategoryDto();
	}

	@Test
	void getAllProduct() {
		productService.findAll();
	}

	@Test
	void saveProduct() {
		Category category = categoryService.findById(3L);
		ProductDto productDto = new ProductDto();
		productDto.setName("WINE RED VELVET DOUBLE SEATED CHESTERFIELD SOFA");
		productDto.setCategory(category);
		productDto.setDescription("This beautiful Wine Red Velvet Double Seated Chesterfield Sofa has been upholstered in a luxurious wine red cotton velvet and features four turned feet constructed from 100% solid mango wood in a chestnut finish. The sofa is detailed with deep button tufts, piping and sheltering arms. This is a statement piece which can be added as a splash of colour to any room in the home. It's suitable for many different styles and colours of interiors either modern or classic.");
		productDto.setCurrentQuantity(5);
		productDto.setCostPrice(600);
		productService.save(null, productDto);
	}

	@Test
	void UpdateProduct() {
		Category category = categoryService.findById(3L);
		ProductDto productDto = new ProductDto();
		productDto.setId(9L);
		productDto.setName("WINE RED VELVET DOUBLE SEATED CHESTERFIELD SOFA");
		productDto.setCategory(category);
		productDto.setDescription("This beautiful Wine Red Velvet Double Seated Chesterfield Sofa has been upholstered in a luxurious wine red cotton velvet and features four turned feet constructed from 100% solid mango wood in a chestnut finish. The sofa is detailed with deep button tufts, piping and sheltering arms. This is a statement piece which can be added as a splash of colour to any room in the home. It's suitable for many different styles and colours of interiors either modern or classic.");
		productDto.setCurrentQuantity(2);
		productDto.setCostPrice(500);
		productService.update(null, productDto);
	}

	@Test
	void deleteProductById() {
		productService.deleteById(1L);
	}

	@Test
	void enableProductById() {
		productService.enableById(1L);
	}

	@Test
	void getProductDtoById() {
		productService.getProductById(1L);
	}

	@Test
	void pageProducts() {
		productService.pageProducts(1);
	}

	@Test
	void pageProductsCustomer() {
		productService.pageProductsCustomer(1);
	}

	@Test
	void searchProducts() {
		productService.searchProducts(1, "b");
	}

	@Test
	void getRandomProducts() {
		productService.getRandomProducts();
	}

	@Test
	void get5HighestPriceProducts() {
		productService.get5HighestPriceProducts();
	}

	@Test
	void searchCustomerProducts() {
		productService.searchCustomerProducts(1, "b");
	}

	@Test
	void searchCusTomerProductsByCategory() {
		Category category = categoryService.findById(3L);
		productService.searchCusTomerProductsByCategory(1, 1L);
	}

	@Test
	void getDescProducts() {
		productService.getDescProducts(1);
	}

	@Test
	void getAscProducts() {
		productService.getAscProducts(1);
	}

	@Test
	void getProductById() {
		productService.getById(1L);
	}
}
