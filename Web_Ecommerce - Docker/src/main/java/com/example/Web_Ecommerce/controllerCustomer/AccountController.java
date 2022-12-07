package com.example.Web_Ecommerce.controllerCustomer;


import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.service.CustomerService;
import com.example.Web_Ecommerce.utils.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class AccountController {
    @Autowired
    CustomerService customerService;

    //Trả về trang my account
    @GetMapping("/account")
    public String getAccountHome(Model model, Principal principal, HttpSession session) {
        if (principal == null) {
            session.removeAttribute("username");
            return "redirect:/shop/login";
        }else{
            session.setAttribute("username", principal.getName());
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        model.addAttribute("customer", customer);
        return "customer_my-account";
    }

    //update customer
    @PostMapping("/update-customer")
    public String updateCustomer(@ModelAttribute("customer") Customer customer,
                                 @RequestParam("imageCustomer") MultipartFile imageCustomer,
                                 RedirectAttributes attributes) {
        try {
            Map<String,String> error= ValidateObject.validateUpdate(customer.getPassword());
            if(!ObjectUtils.isEmpty(error)){
                attributes.addFlashAttribute("failed", error.get("password"));
            }else{
                customerService.update(imageCustomer, customer);
                attributes.addFlashAttribute("success", "Cập nhật thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Cập nhật thất bại!");
        }
        return "redirect:/shop/account";
    }
}
