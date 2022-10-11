package com.example.SignInSecurity.entity;

import lombok.*;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="token")
public class TokenConfirm {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id",nullable=false)
    private Integer id;

    @Column(name="token")
    private String token;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="expires_at")
    private LocalDateTime expiresAt;

    @Column(name="confirmed_at")
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TokenConfirm(String token,LocalDateTime createdAt,LocalDateTime expiresAt,User user){
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt=expiresAt;
        this.user = user;
    }

}
