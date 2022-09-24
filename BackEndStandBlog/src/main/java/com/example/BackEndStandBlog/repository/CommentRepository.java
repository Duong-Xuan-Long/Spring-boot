package com.example.BackEndStandBlog.repository;

import com.example.BackEndStandBlog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(nativeQuery = true,value = "SELECT c.* \n" +
            "FROM comment c\n" +
            "INNER JOIN blog b\n" +
            "ON b.id=c.blog_id\n" +
            "WHERE b.id=?1\n" +
            "ORDER BY c.created_at DESC")
    List<Comment> getAllCommentByBlogId(Integer id);
}