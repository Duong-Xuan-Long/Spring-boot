package com.example.Web_Ecommerce.repository;

import com.example.Web_Ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    //Xóa item
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM cart_item c WHERE c.cart_item_id = ?1")
    void deleteItem(Long id);
}
