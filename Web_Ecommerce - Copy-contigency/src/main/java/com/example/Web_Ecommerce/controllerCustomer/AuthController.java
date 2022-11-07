package com.example.Web_Ecommerce.controllerCustomer;


import com.example.Web_Ecommerce.dto.CustomerDto;
import com.example.Web_Ecommerce.exception.BadRequestException;
import com.example.Web_Ecommerce.request.LoginRequest;
import com.example.Web_Ecommerce.request.RegisterRequest;
import com.example.Web_Ecommerce.service.CustomerService;
import com.example.Web_Ecommerce.serviceCustomer.AuthService;
import com.example.Web_Ecommerce.serviceCustomer.CustomerForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/shop")
public class AuthController {
    @Autowired
    CustomerService customerService;
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    CustomerForgotPasswordService forgotPasswordService;

    @Autowired
    AuthService authService;

    //Trả về trang login
    @GetMapping("/login")
    public String login(Principal principal, HttpSession session) {
        if(principal!=null){
            session.setAttribute("username", principal.getName());
        }else{
            session.removeAttribute("username");
        }
        return "customer_login";
    }

    @PostMapping("/do-login")
    @ResponseBody
    public String loginProcess(@RequestBody LoginRequest request, HttpServletResponse httpServletResponse){
        return  authService.login(request, httpServletResponse);
    }

    @GetMapping("/logout-handle")
    public String logout(HttpServletResponse httpServletResponse,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("success","Bạn đã đăng xuất thành công");
        authService.logout(httpServletResponse);
        return "redirect:/shop/login";
    }

    //Trả về trang đăng kí
    @GetMapping("/register")
    public String register(Model model,Principal principal,HttpSession session) {
        if(principal!=null){
            session.setAttribute("username", principal.getName());
        }else{
            session.removeAttribute("username");
        }
        model.addAttribute("customerDto", new CustomerDto());
        return "customer_register";
    }
    @GetMapping("/confirm")
    @ResponseBody
    public String confirm(@RequestParam String token){
        return authService.confirmToken(token);
    }

    //Xử lí quá trình đăng kí
    @PostMapping("/do-register")
    @ResponseBody
    public String doRegister(@RequestBody RegisterRequest registerRequest) throws MessagingException {
        return authService.register(registerRequest);
    }

    //Link đến trang xử lí quên mật khẩu
    @GetMapping("/forgot-password")
    public String forgotPassword(Model model,Principal principal,HttpSession session) {
        if(principal!=null){
            session.setAttribute("username", principal.getName());
        }else{
            session.removeAttribute("username");
        }
        return "customer_forgot-password";
    }

    //LInk làm quá trình lấy lại mật khẩu
    @GetMapping("/do-forgot-password")
    public String forgotPassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        try {
            forgotPasswordService.forgotPassword(email);
            redirectAttributes.addFlashAttribute("success", "Mở email của bạn lấy mật khẩu mới và đăng nhập lại");
        } catch (BadRequestException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (MessagingException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/shop/login";
    }
}
