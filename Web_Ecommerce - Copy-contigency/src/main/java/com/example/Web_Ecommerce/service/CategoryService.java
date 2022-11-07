package com.example.Web_Ecommerce.service;

import com.example.Web_Ecommerce.dto.CategoryDto;
import com.example.Web_Ecommerce.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    //TÌm tất cả cá loại
    List<Category> findAll();

    //Lưu cá loại
    Category save(Category category);

    //Tìm theo id
    Category findById(Long id);

    //update các loại
    Category update(Category category);

    //Xóa loại theo id
    void deleteById(Long id);

    //Kích hoạt loại
    void enabledById(Long id);

    //TÌm tất cả các loại đã được kích hoạt
    List<Category> findAllByActivated();

    //Tìm tất cả các categoryDto
    List<CategoryDto> getCategoryDto();

    //Xóa vĩnh viễn một loại
    void hardDeleteCategory(Long id);
    //Phân trang các category

    Page<Category> pageCategories(int pageNo);
    //phân trang khi tìm các category

    Page<Category> searchCategories(int pageNo, String keyword);
}
