package com.example.Web_Ecommerce.controllerAdmin;


import com.example.Web_Ecommerce.dto.ProductDto;
import com.example.Web_Ecommerce.model.Category;
import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.service.CategoryService;
import com.example.Web_Ecommerce.service.CustomerService;
import com.example.Web_Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    //    @GetMapping("/products")
//    public String products(Model model, Principal principal){
//        if(principal==null){
//            return "redirect:/login";
//        }
//        List<ProductDto> productDtoList=productService.findAll();
//        model.addAttribute("products",productDtoList);
//        model.addAttribute("size",productDtoList.size());
//
//        return "products";
//    }
    //Lấy trang quản lí sàn phẩm có phân trang
    @GetMapping("/products/page/{pageNo}")
    public String productsPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        Page<ProductDto> products = productService.pageProducts(pageNo-1);
        model.addAttribute("size", products.getSize());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("products", products);
        return "admin_products";
    }

    //Lấy trang tìm sản phẩm có phân trang
    @GetMapping("/search-products/page/{pageNo}")
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
        Page<ProductDto> products = productService.searchProducts(pageNo-1, keyword);
        model.addAttribute("products", products);
        model.addAttribute("size", products.getSize());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("keyword",keyword);
        return "admin_result-products";
    }

    //Trang thêm sản phẩm
    @GetMapping("/add-product")
    public String addProduct(Model model, Principal principal,HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        List<Category> categories = categoryService.findAllByActivated();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new ProductDto());
        return "admin_add-product";
    }

    //Quá trình lưu sản phẩm
    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product") ProductDto productDto,
                              @RequestParam("imageProduct") MultipartFile imageProduct,
                              RedirectAttributes redirectAttributes,Principal principal,HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }

        try {
            productService.save(imageProduct, productDto);
            redirectAttributes.addFlashAttribute("success", "Thêm thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Thêm thất bại!");
        }
        return "redirect:/admin/products/page/1";
    }

    //trang update sản phẩm
    @GetMapping("/update-product/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model, Principal principal,HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        List<Category> categories = categoryService.findAllByActivated();
        ProductDto productDto = productService.getById(id);
        model.addAttribute("categories", categories);
        model.addAttribute("productDto", productDto);
        return "admin_update-product";
    }

    //QUá trình update sản phẩm
    @PostMapping("/update-product/{id}")
    public String processUpdate(@PathVariable("id") Long id,
                                @ModelAttribute("productDto") ProductDto productDto,
                                @RequestParam("imageProduct") MultipartFile imageProduct,
                                RedirectAttributes attributes,Principal principal,HttpSession httpSession
    ) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        try {
            productService.update(imageProduct, productDto);
            attributes.addFlashAttribute("success", "Cập nhật thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Cập nhật thất bại!");
        }
        return "redirect:/admin/products/page/1";

    }

    //Làm sản phẩm active
    @RequestMapping(value = "/enable-product/{id}", method = {RequestMethod.GET})
    public String enabledProduct(@PathVariable("id") Long id, RedirectAttributes attributes
            , Principal principal, HttpSession httpSession, HttpServletRequest request) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        try {
            productService.enableById(id);
            attributes.addFlashAttribute("success", "Kích hoạt thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Kích hoạt thất bại!");
        }
        return "redirect:" + request.getHeader("Referer");
    }

    //Xóa mềm sản phẩm
    @RequestMapping(value = "/delete-product/{id}", method = {RequestMethod.GET})
    public String deletedProduct(@PathVariable("id") Long id, RedirectAttributes attributes,
                                 Principal principal,HttpSession httpSession,HttpServletRequest request) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        try {
            productService.deleteById(id);
            attributes.addFlashAttribute("success", "Xóa thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Xóa thất bại");
        }
        return "redirect:" + request.getHeader("Referer");
    }

    //xỏa vĩnh viễn sản phẩm
    @GetMapping("hard-delete-product/{id}")
    public String hardDeleteProduct(@PathVariable("id") Long id, RedirectAttributes attributes
            ,Principal principal,HttpSession httpSession,HttpServletRequest request) {
        if (principal == null) {
            return "redirect:/shop/login";
        }else{
            Customer customer=customerService.findByUsername(principal.getName());
            httpSession.setAttribute("adminSrc",customer.getImage());
        }
        try {
            productService.hardDeleteProduct(id);
            attributes.addFlashAttribute("success", "Xóa vĩnh viễn thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Xóa vĩnh viễn thất bại");
        }
        return "redirect:" + request.getHeader("Referer");
    }
}
