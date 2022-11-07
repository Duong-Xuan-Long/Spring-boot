package com.example.Web_Ecommerce.dto;


import com.example.Web_Ecommerce.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private long costPrice;
    private long salePrice;
    private int currentQuantity;
    private String image;
    private Category category;
    private boolean is_activated;
    private boolean is_deleted;
}
