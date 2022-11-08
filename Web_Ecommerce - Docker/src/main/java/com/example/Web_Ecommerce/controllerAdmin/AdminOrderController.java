package com.example.Web_Ecommerce.controllerAdmin;


import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.model.Order;
import com.example.Web_Ecommerce.repository.OrderRepository;
import com.example.Web_Ecommerce.service.CustomerService;
import com.example.Web_Ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerService customerService;
//get ALl
    @GetMapping("/orders/page/{pageNo}")
    public String productsPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        Page<Order> orders = orderService.getAllOrder(pageNo-1);
        model.addAttribute("size", orders.getSize());
        model.addAttribute("totalPages", orders.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("orders", orders);
        return "admin_orders";
    }
//Xóa order
    @GetMapping("/cancel-order/{id}")
    public String cancelOrder(@PathVariable("id") Long id, Principal principal,HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        orderService.cancelOrder(id);
        return "redirect:/admin/orders/page/1";
    }
    //accept order
    @GetMapping("/accept-order/{id}")
    public String acceptOrder(@PathVariable("id")Long id, Principal principal,HttpSession httpSession){
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        orderService.acceptOrder(id);
        return "redirect:/admin/orders/page/1";
    }
   // unaccept order
    @GetMapping("/unaccept-order/{id}")
    public String unAcceptOrder(@PathVariable("id")Long id, Principal principal,HttpSession httpSession){
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        orderService.undoAcceptOrder(id);
        return "redirect:/admin/orders/page/1";
    }
    //update notes
    @GetMapping("/update-order/{id}")
    public String updateOrder(@PathVariable("id")Long id, @RequestParam("notes")String notes, Principal principal,
                              HttpSession httpSession){
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        Order order=orderRepository.findById(id).get();
        orderService.updateNote(id,notes);
        return "redirect:/admin/orders/page/1";
    }

}
