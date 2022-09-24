package com.example.BackEndStandBlog.repository;

import com.example.BackEndStandBlog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Set<Category> getByIdIn(List<Integer> ids);

    @Query(nativeQuery = true,value = "SELECT c.*,table1.count\n" +
            "FROM category c\n" +
            "INNER JOIN (SELECT COUNT(bc.categories_id) as count,bc.categories_id FROM blog_categories bc GROUP BY bc.categories_id) AS table1\n" +
            "ON table1.categories_id=c.id\n" +
            "ORDER BY table1.count DESC\n" +
            "LIMIT 5")
    List<Category> getTop5Categories();

    @Modifying
    @Transactional
    @Query(nativeQuery=true,value="UPDATE category c SET c.name=?2 WHERE c.id=?1")
    void updateCategory_By_id(Integer id,String categoryName);

    Category getById(Integer id);


    @Modifying
    @Transactional
    @Query(nativeQuery=true,value="DELETE FROM category c WHERE c.id = ?1")
     void deleteCategory_By_id(Integer id);
}