package com.example.BackEndStandBlog.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "identity_card")
public class IdentityCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "expried")
    private LocalDate expried;

    @Column(name = "issued")
    private LocalDate issued;

    @PrePersist
    public void prePersist() {
        expried= LocalDate.now().minusYears(2);
        issued=LocalDate.now().plusYears(2);
    }
}