package com.example.Web_Ecommerce.controllerCustomer;

import com.example.Web_Ecommerce.dto.ProductDto;
import com.example.Web_Ecommerce.service.CategoryService;
import com.example.Web_Ecommerce.service.ProductService;
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
@RequestMapping("/shop")
public class CustomerProductController {
    @Autowired
    public ProductService productService;

    @Autowired
    public CategoryService categoryService;

    //Trả về trang detail product
    @GetMapping("/product-detail/{id}")
    public String getDetailProductPage(@PathVariable("id") Long id, Model model, Principal principal, HttpSession session) {
        if(principal!=null){
            session.setAttribute("username", principal.getName());
        }else{
            session.removeAttribute("username");
        }
        ProductDto productDto = productService.getById(id);
        model.addAttribute("product", productDto);
        return "customer_product-detail";
    }

    //Trả về trang product list
    @GetMapping("/products/page/{pageNo}")
    public String productsPage(@PathVariable("pageNo") int pageNo, Model model,Principal principal,HttpSession session) {
        if(principal!=null){
            session.setAttribute("username", principal.getName());
        }else{
            session.removeAttribute("username");
        }
        Page<ProductDto> products = productService.pageProductsCustomer(pageNo-1);
        model.addAttribute("size", products.getSize());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("products", products);
        // model.addAttribute("categories",categoryService.findAllByActivated());
        model.addAttribute("categories", categoryService.getCategoryDto());
        return "customer_product_list";
    }

    //Trả về trang tìm kiếm product
    @GetMapping("/search-products/page/{pageNo}")
    public String searchProducts(@PathVariable("pageNo") int pageNo,
                                 @RequestParam("keyword") String keyword,
                                 Model model,Principal principal,HttpSession session) {
        if(principal!=null){
            session.setAttribute("username", principal.getName());
        }else{
            session.removeAttribute("username");
        }
        Page<ProductDto> products = productService.searchCustomerProducts(pageNo-1, keyword);
        model.addAttribute("products", products);
        model.addAttribute("size", products.getSize());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("categories", categoryService.getCategoryDto());
        model.addAttribute("keyword",keyword);
        return "customer_resultSearch";
    }

    //Trả về trang tìm kiếm sản phẩm theo loại
    @GetMapping("/search-products-by-category/page/{pageNo}")
    public String searchProducts(@PathVariable("pageNo") int pageNo,
                                 @RequestParam("category_id") Long id,
                                 Model model,Principal principal,HttpSession session) {
        if(principal!=null){
            session.setAttribute("username", principal.getName());
        }else{
            session.removeAttribute("username");
        }
        Page<ProductDto> products = productService.searchCusTomerProductsByCategory(pageNo-1, id);
        model.addAttribute("products", products);
        model.addAttribute("size", products.getSize());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("categories", categoryService.getCategoryDto());
        model.addAttribute("idC",id);
        return "customer_resultSearchByCategory";
    }

    //TRả về trang tìm kiếm các sản phẩm giá từ thấp nhất
    @GetMapping("/low-price-products/page/{pageNo}")
    public String getDescProducts(@PathVariable("pageNo") int pageNo,
                                  Model model,Principal principal,HttpSession session) {
        if(principal!=null){
            session.setAttribute("username", principal.getName());
        }else{
            session.removeAttribute("username");
        }
        Page<ProductDto> products = productService.getAscProducts(pageNo-1);
        model.addAttribute("products", products);
        model.addAttribute("size", products.getSize());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("categories", categoryService.getCategoryDto());
        return "customer_lowPrice";
    }

    //TRả về trang tìm kiếm các sản phẩm giá từ cao nhất
    @GetMapping("/high-price-products/page/{pageNo}")
    public String getAscProducts(@PathVariable("pageNo") int pageNo,
                                 Model model,Principal principal,HttpSession session) {
        if(principal!=null){
            session.setAttribute("username", principal.getName());
        }else{
            session.removeAttribute("username");
        }
        Page<ProductDto> products = productService.getDescProducts(pageNo-1);
        model.addAttribute("products", products);
        model.addAttribute("size", products.getSize());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("categories", categoryService.getCategoryDto());
        return "customer_highPrice";
    }
}
