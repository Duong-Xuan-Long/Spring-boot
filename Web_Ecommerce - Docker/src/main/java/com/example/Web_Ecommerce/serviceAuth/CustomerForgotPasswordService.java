package com.example.Web_Ecommerce.serviceAuth;

import com.example.Web_Ecommerce.exception.BadRequestException;
import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.repository.CustomerRepository;
import com.example.Web_Ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Random;

@Service
public class CustomerForgotPasswordService {
    @Autowired
    CustomerEmailService emailService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomerService customerService;

    //gửi mail mật khẩu mới quên mật khẩu
    public void forgotPassword(String email) throws MessagingException {
        Customer customer = customerService.findByUsername(email);
        if (customer == null) {
            throw new BadRequestException("email : " + email + " doesn't exist");
        }
        String newPassword=generateUUDI();
        customer.setPassword(passwordEncoder.encode(newPassword));
        customerRepository.save(customer);
        emailService.send(email, "Forgot Password", "New Password:" + newPassword);
    }

    //Tạo mật khẩu mới
    private String generateUUDI() {
        String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
        String alphaUpperCase = alpha.toUpperCase(); // A-Z
        String digits = "0123456789"; // 0-9
        String specials = "~=+%^*/()[]{}/!@#$?|";
        String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
        String ALL = alpha + alphaUpperCase + digits + specials;
        Random generator = new Random();
        StringBuilder sb = new StringBuilder();
        int min = 0;
        int max = ALPHA_NUMERIC.length() - 1;
        for (int i = 0; i < 5; i++) {
            int number = generator.nextInt((max - min) + 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
}
