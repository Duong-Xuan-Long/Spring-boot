package com.example.BackEndStandBlog.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        Random rd=new Random();
        int rdDay=rd.nextInt(31);
        createdAt=LocalDateTime.now().minusDays(rdDay);
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}