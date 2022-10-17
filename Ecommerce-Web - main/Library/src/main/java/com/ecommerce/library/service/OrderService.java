package com.ecommerce.library.service;

import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.ShoppingCart;

public interface OrderService {
    //Lưu order
    void saveOrder(ShoppingCart shoppingCart);

    //Chấp nhận order
    void acceptOrder(Long id);

    //Xóa order
    void cancelOrder(Long id);
}
