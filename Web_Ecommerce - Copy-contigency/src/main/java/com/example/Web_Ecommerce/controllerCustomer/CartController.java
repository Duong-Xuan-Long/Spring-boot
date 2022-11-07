package com.example.Web_Ecommerce.controllerCustomer;

import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.model.Product;
import com.example.Web_Ecommerce.model.ShoppingCart;
import com.example.Web_Ecommerce.service.CustomerService;
import com.example.Web_Ecommerce.service.ProductService;
import com.example.Web_Ecommerce.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/shop")
public class CartController {
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    //Trả về trang shopping cart
    @GetMapping("/cart")
    public String cart(Model model, Principal principal, HttpSession session) {
        if (principal == null) {
            session.removeAttribute("username");
            return "redirect:/shop/login";
        }else{
            session.setAttribute("username", principal.getName());
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        double subTotal;
        if (shoppingCart == null) {
            model.addAttribute("check", "Bạn không có sản phẩm nào");
            subTotal = 0;
        } else {
            subTotal = shoppingCart.getTotalPrices();
            subTotal = Double.parseDouble(String.format("%.2f", subTotal));
        }
        model.addAttribute("subTotal", subTotal);
        model.addAttribute("shoppingCart", shoppingCart);
        return "customer_cart";
    }

    //Xử lí quá trình thêm item
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestBody String productId,
                            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                            Principal principal,
                            Model model,
                            HttpServletRequest request) {

        if (principal == null) {
            return "redirect:/shop/login";
        }
        Product product = productService.getProductById(Long.parseLong(productId
                .substring(productId.lastIndexOf('=') + 1)));
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart shoppingCart = shoppingCartService.addItemtoCart(product, quantity, customer);
        return "redirect:" + request.getHeader("Referer");

    }

    //update cart
    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
    public String updateCart(@RequestParam("quantity") int quantity,
                             @RequestParam("id") Long productId,
                             Principal principal, Model model
    ) {
        if (principal == null) {
            return "redirect:/shop/login";
        } else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            ShoppingCart cart = shoppingCartService.updateItemInCart(product, quantity, customer);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/shop/cart";
        }
    }

    //Xử lí xóa item
    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=delete")
    public String deleteCart(@RequestParam("id") Long productId, Model model,
                             Principal principal) {
        if (principal == null) {
            return "redirect:/shop/login";
        } else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            ShoppingCart cart = shoppingCartService.deleteItemInCart(product, customer);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/shop/cart";
        }

    }

}
