package com.example.Web_Ecommerce.controllerAdmin;


import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.service.CategoryService;
import com.example.Web_Ecommerce.service.CustomerService;
import com.example.Web_Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class IndexAdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;


    //trang index admin
    @RequestMapping("/index")
    public String home(Model model, Principal principal, HttpSession httpSession){
    Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
    if(authentication==null||authentication instanceof AnonymousAuthenticationToken){
        return "redirect:/shop/login";
    }
    if(principal!=null){
       Customer customer=customerService.findByUsername(principal.getName());
        httpSession.setAttribute("adminSrc",customer.getImage());
        httpSession.setAttribute("idAdmin",customer.getId());
    }else{
        httpSession.removeAttribute("idAdmin");
        httpSession.removeAttribute("adminSrc");
    }
    model.addAttribute("totalProducts",productService.findAll().size());
    model.addAttribute("totalCategories",categoryService.findAll().size());
        return "admin_index1";
    }
}
