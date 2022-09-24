package com.example.BackEndStandBlog.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false,unique = true)
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "password", nullable = false)
    private String password;

    @PrePersist
    public void prePersist() {
    if(password==null){
        password="111";
    }
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "identity_card_id")
    private IdentityCard identityCard;
}