package com.example.BackEndStandBlog.repository;

import com.example.BackEndStandBlog.dto.BlogInfo;
import com.example.BackEndStandBlog.entity.Blog;
import com.example.BackEndStandBlog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> getByUser_IdOrderByCreatedAtDesc(Integer id);


    @Query(nativeQuery = true, name = "getAllBlogInfo")
    List<BlogInfo> getAllBlogInfo();

    @Query(nativeQuery = true,value="SELECT b.* FROM blog b INNER JOIN (SELECT COUNT(c.blog_id) AS count,c.blog_id FROM comment c GROUP BY c.blog_id) AS table2 ON b.id=table2.blog_id ORDER BY `table2`.`count` DESC LIMIT 5")
    List<Blog> test1();

    @Query(nativeQuery = true,value="SELECT b.*,c.id \n" +
            "FROM category c\n" +
            "INNER JOIN blog_categories bc\n" +
            "ON bc.categories_id=c.id\n" +
            "INNER JOIN blog b \n" +
            "ON b.id=bc.blog_id\n" +
            "WHERE c.id=1\n" +
            "ORDER BY b.published_at DESC")
    List<Blog> getBlogByCategoryId(Integer id);
    @Query(nativeQuery = true,value="SELECT b.* \n" +
            "FROM blog b \n" +
            "WHERE b.user_id=?1\n" +
            "ORDER BY b.published_at DESC")

    List<Blog> getBlogByUserId(Integer id);
    @Query(nativeQuery = true,value="SELECT b.* \n" +
            "FROM blog b \n" +
            "WHERE b.id=?1")
    Blog getBlogBy_Id(Integer id);

    @Query(nativeQuery = true,value = "SELECT b.*,JSON_OBJECT('id',u.id,'name',u.name) AS user, \n" +
            "(SELECT JSON_ARRAYAGG(JSON_OBJECT('id',c2.id,'name',u.name  ,\n" +
            "'created_at',c2.created_at,'content',c2.content)) \n" +
            "FROM comment c2 \n" +
            "INNER JOIN user u \n" +
            "ON u.id=c2.user_id \n" +
            "WHERE c2.blog_id=?1) as comments\n" +
            "FROM blog b\n" +
            "INNER JOIN user u \n" +
            "ON u.id=b.user_id\n" +
            "INNER JOIN comment c \n" +
            "ON c.blog_id=b.id\n" +
            "WHERE b.id=?1\n" +
            "GROUP BY b.id\n")
    List<Blog> getBlogByIdWithUserAndComment(Integer id);
}