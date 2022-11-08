package com.example.Web_Ecommerce.controllerAdmin;


import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.repository.CustomerRepository;
import com.example.Web_Ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;

    //trang customer có phân trang
    @GetMapping("/customers/page/{pageNo}")
    public String customersPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc", customer.getImage());
        }
        Page<Customer> customers = customerService.pageCustomer(pageNo - 1);
        model.addAttribute("size", customers.getSize());
        model.addAttribute("totalPages", customers.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("customers", customers);
        return "admin_customers";
    }

    //trang kết quả tìm customer
    @GetMapping("detail-customer/{id}")
    public String detailedCustomerPage(@PathVariable("id") Long id, Model model, Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc", customer.getImage());
        }
        Customer customer = customerRepository.findById(id).get();
        model.addAttribute("customer", customer);
        return "admin_detailedCustomer";
    }

    //kích hoạt customer
    @GetMapping("/enable-customer/{id}")
    public String enableCustomer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc", customer.getImage());
        }
        try {
            customerService.enableById(id);
            redirectAttributes.addFlashAttribute("success", "Kích hoạt thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Kích hoạt thất bại!");
        }
        customerService.enableById(id);
        return "redirect:/admin/customers/page/1";
    }

    //xóa mềm customer
    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc", customer.getImage());
        }
        try {
            customerService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Xóa thất bại");
        }

        return "redirect:/admin/customers/page/1";
    }

    //xóa xĩnh viễn customer
    @GetMapping("hard-delete-customer/{id}")
    public String hardDeleteCustomer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
                                     Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc", customer.getImage());
        }
        try {
            customerService.hardDelete(id);
            redirectAttributes.addFlashAttribute("success", "Xóa vĩnh viễn thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Xóa vĩnh viễn thất bại");
        }

        return "redirect:/admin/customers/page/1";
    }

    //kết quả trang tìm customer
    @GetMapping("/search-customers/page/{pageNo}")
    public String getSearchPage(@PathVariable int pageNo, @RequestParam("keyword") String keyword,
                                Principal principal,
                                Model model, HttpSession httpSession) {

        if (principal == null) {
            return "redirect:/shop/login";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc", customer.getImage());
        }
        Page<Customer> customers = customerService.searchProducts(pageNo - 1, keyword);
        model.addAttribute("size", customers.getSize());
        model.addAttribute("totalPages", customers.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("customers", customers);
        model.addAttribute("keyword", keyword);
        return "admin_result_customer";
    }

    //Cấp quyền admin
    @GetMapping("/give-admin-role/{id}")
    public String giveAdminROle(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
                                 Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc", customer.getImage());
        }
        try {
            customerService.giveAdminRole(id);
            redirectAttributes.addFlashAttribute("success", "Cấp quyền thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Cấp quyền thất bại");
        }
        return "redirect:/admin/customers/page/1";
    }

    //Bỏ cấp quyền admin
    @GetMapping("/take-admin-role/{id}")
    public String takeAdminRole(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
                                 Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc", customer.getImage());
        }
        try {
            customerService.takeAdminRole(id);
            redirectAttributes.addFlashAttribute("success", "Bỏ cấp quyền thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Bỏ cấp quyền thất bại");
        }
        return "redirect:/admin/customers/page/1";
    }
}
