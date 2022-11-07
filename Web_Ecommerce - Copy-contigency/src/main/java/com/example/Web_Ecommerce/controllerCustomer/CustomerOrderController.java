package com.example.Web_Ecommerce.controllerCustomer;

import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.model.Order;
import com.example.Web_Ecommerce.model.ShoppingCart;
import com.example.Web_Ecommerce.service.CustomerService;
import com.example.Web_Ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/shop")
public class CustomerOrderController {
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderService orderService;

    //Trả về trang checkout
    @GetMapping("/check-out")
    public String checkout(Model model, Principal principal, HttpSession session) {
        if (principal == null) {
            session.removeAttribute("username");
            return "redirect:/shop/login";
        }else{
            session.setAttribute("username", principal.getName());
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        if (customer.getPhoneNumber() == null || customer.getAddress() == null || customer.getCity() == null
                || customer.getCountry() == null) {
            model.addAttribute("customer", customer);
            model.addAttribute("error", "Bạn phải điền hết tất cả các thông tin trước khi kiểm tra đơn!");
            return "customer_my-account";
        }
        if (customer.getPhoneNumber().trim().isEmpty() || customer.getAddress().trim().isEmpty() || customer.getCity().trim().isEmpty()
                || customer.getCountry().trim().isEmpty()) {
            model.addAttribute("customer", customer);
            model.addAttribute("error", "Bạn phải điền hết tất cả các thông tin trước khi kiểm tra đơn!");
            return "customer_my-account";
        }

        model.addAttribute("customer", customer);
        ShoppingCart cart = customer.getShoppingCart();
        model.addAttribute("cart", cart);
        return "customer_checkout";
    }

    //Trả về trang order
    @GetMapping("/order")
    public String getOrderPage(Principal principal, Model model,HttpSession session) {
        if (principal == null) {
            session.removeAttribute("username");
            return "redirect:/shop/login";
        }else{
            session.setAttribute("username", principal.getName());
        }
        Customer customer = customerService.findByUsername(principal.getName());
        List<Order> orderList = customer.getOrders();
        model.addAttribute("orders", orderList);
        model.addAttribute("size", orderList.size());
        return "customer_order";
    }

    //Xử lí quá trình lưu order
    @GetMapping("/save-order")
    public String saveOrder(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/shop/login";
        }
        Customer customer = customerService.findByUsername(principal.getName());
        ShoppingCart cart = customer.getShoppingCart();
        orderService.saveOrder(cart);
        return "redirect:/shop/order";
    }

    //Xử lí quá trình xóa order
    @GetMapping("/cancel-order/{id}")
    public String cancelOrder(@PathVariable("id") Long id) {
        orderService.cancelOrder(id);
        return "redirect:/shop/order";
    }
}
