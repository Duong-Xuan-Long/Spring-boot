package com.example.BackEndStandBlog.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogInfo implements Serializable{
    private Integer id;
    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private LocalDateTime publishedAt;
    private Integer countComment;
    private AuthorInfo author;

    public BlogInfo(Integer id, String title, String description, String content, String thumbnail, LocalDateTime publishedAt, Integer countComment, String author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content=content;
        this.thumbnail = thumbnail;
        this.publishedAt = publishedAt;
        this.countComment = countComment;
        if (author != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.author = mapper.readValue(author,AuthorInfo.class );
            } catch (IOException e) {
                this.author = null;
            }
        }
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class AuthorInfo implements Serializable{
        private Integer id;
        private String name;
    }
}



