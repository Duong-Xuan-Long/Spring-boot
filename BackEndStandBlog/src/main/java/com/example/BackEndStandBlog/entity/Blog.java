package com.example.BackEndStandBlog.entity;

import com.example.BackEndStandBlog.dto.BlogInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;


@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "blogInfo",
                classes = @ConstructorResult(
                        targetClass = BlogInfo.class,
                        columns = {
                                @ColumnResult(name = "id",type=Integer.class),
                                @ColumnResult(name = "title",type=String.class),
                                @ColumnResult(name = "description", type=String.class),
                                @ColumnResult(name = "content", type=String.class),
                                @ColumnResult(name = "thumbnail", type=String.class),
                                @ColumnResult(name = "published_at", type=LocalDateTime.class),
                                @ColumnResult(name = "count_comment", type=Integer.class),
                                @ColumnResult(name = "author", type=String.class),
                        }
                )
        )
})
@NamedNativeQuery(
        name = "getAllBlogInfo",
        resultSetMapping = "blogInfo",
        query = "SELECT b.id, b.title, b.description,b.content, b.thumbnail, b.published_at,\n" +
                "COUNT(c.id) AS count_comment, JSON_OBJECT(\"id\", u.id , \"name\", u.name) AS author\n" +
                "FROM blog b \n" +
                "LEFT JOIN user u\n" +
                "ON b.user_id = u.id\n" +
                "LEFT JOIN comment c\n" +
                "ON b.id = c.blog_id\n" +
                "WHERE b.status = true\n" +
                "GROUP BY b.id\n" +
                "ORDER BY b.created_at DESC")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "content",columnDefinition = "TEXT")
    private String content;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "status")
    private Boolean status;



    @PrePersist
    public void prePersist() {
        createdAt=LocalDateTime.now();
        if(status){
            publishedAt=createdAt;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt=LocalDateTime.now();
        if(status){
            publishedAt=updatedAt;
        }else{
            publishedAt=null;
        }
    }

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "blog_categories",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private Set<Category> categories = new LinkedHashSet<>();

}