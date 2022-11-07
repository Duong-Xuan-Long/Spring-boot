package com.example.Web_Ecommerce.service;


import com.example.Web_Ecommerce.model.Customer;
import com.example.Web_Ecommerce.model.Product;
import com.example.Web_Ecommerce.model.ShoppingCart;

public interface ShoppingCartService {
    //Thêm item vào cart
    ShoppingCart addItemtoCart(Product product, int quantity, Customer customer);

    //update shopping cart
    ShoppingCart updateItemInCart(Product product, int quantity, Customer customer);

    //Xóa item ở shopping cart
    ShoppingCart deleteItemInCart(Product product, Customer customer);
}
