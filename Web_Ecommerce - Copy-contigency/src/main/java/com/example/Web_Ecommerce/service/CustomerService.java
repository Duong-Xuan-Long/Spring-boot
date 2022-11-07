package com.example.Web_Ecommerce.service;


import com.example.Web_Ecommerce.dto.CustomerDto;
import com.example.Web_Ecommerce.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {
    //Lưu các customer
    CustomerDto save(CustomerDto customerDto);

    //TÌm các customer theo tên
    Customer findByUsername(String username);

    //update các customer
    Customer update(MultipartFile imageCustomer, Customer customer);

    //Phân trang customer
    Page<Customer> pageCustomer(int pageNo);

    //Trả về phân trang customer
    Page<Customer> searchProducts(int pageNo, String keyword);

    //Xóa mềm customer
    void deleteById(Long id);

    //Kích hoạt customer
    void enableById(Long id);

    //Xóa vĩnh viễn customer
    void hardDelete(Long id);

    void giveAdminRole(Long id);

    void takeAdminRole(Long id);
}
