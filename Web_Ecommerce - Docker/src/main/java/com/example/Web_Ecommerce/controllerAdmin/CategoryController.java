package com.example.Web_Ecommerce.controllerAdmin;


import com.example.Web_Ecommerce.model.Category;
import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.service.CategoryService;
import com.example.Web_Ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;

    //Link lấy trang category có phân trang
    @GetMapping("/categories/page/{pageNo}")
    public String getCategoryPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        Page<Category> categories = categoryService.pageCategories(pageNo-1);
        model.addAttribute("size", categories.getSize());
        model.addAttribute("totalPages", categories.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("categories", categories);
        model.addAttribute("categoryCreate", new Category());
        return "admin_categories";
    }

    //trang kết quả tìm category
    @GetMapping("/search-categories/page/{pageNo}")
    public String searchProducts(@PathVariable("pageNo") int pageNo,
                                 @RequestParam("keyword") String keyword,
                                 Model model,
                                 Principal principal,HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        Page<Category> categories = categoryService.searchCategories(pageNo-1, keyword);
        model.addAttribute("categories", categories);
        model.addAttribute("size", categories.getSize());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", categories.getTotalPages());
        model.addAttribute("categoryCreate", new Category());
        model.addAttribute("keyword",keyword);
        return "admin_result-categories";
    }

    //Thêm Category
    @PostMapping("/add-category")
    public String add(@ModelAttribute("categoryCreate") Category category, RedirectAttributes redirectAttributes,Principal principal,
                      HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        try {
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("success", "Thêm thành công");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Đã tồn tại loại này");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Thêm thất bại ");
        }
        return "redirect:/admin/categories/page/1";
    }

    //Tìm category bằng id
    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    @ResponseBody
    public Category findById(@RequestParam Long id) {
        return categoryService.findById(id);
    }

    //update category
    @GetMapping("update-category")
    public String update(Category category, RedirectAttributes redirectAttributes,Principal principal,HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        try {
            categoryService.update(category);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thành công");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Cập nhật thất bại vì trùng tên");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Lỗi máy chủ");
        }
        return "redirect:/admin/categories/page/1";
    }

    //Xóa category
    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam Long id, RedirectAttributes redirectAttributes, Principal principal,
                                 HttpSession httpSession, HttpServletRequest request) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        try {
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Xóa thất bại");
        }
        return "redirect:" + request.getHeader("Referer");
    }

    //CHo category active
    @GetMapping("/enable-category")
    public String enableCategory(@RequestParam Long id, RedirectAttributes redirectAttributes,Principal principal
            ,HttpSession httpSession,HttpServletRequest request) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        try {
            categoryService.enabledById(id);
            redirectAttributes.addFlashAttribute("success", "Kích hoạt thành công");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Kích hoạt thất bại");
        }
        return "redirect:" + request.getHeader("Referer");
    }
//Xóa vĩnh viễn
    @GetMapping("hard-delete-category/{id}")
    public String hardDeleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes
            ,Principal principal,HttpSession httpSession,HttpServletRequest request) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        try {
            categoryService.hardDeleteCategory(id);
            redirectAttributes.addFlashAttribute("success", "Xóa vĩnh viễn thành công");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Xóa vĩnh viễn thất bại");
        }
        return "redirect:" + request.getHeader("Referer");
    }
}
