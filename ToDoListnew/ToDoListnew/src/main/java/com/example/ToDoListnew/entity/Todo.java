package com.example.ToDoListnew.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable=false,unique = true)
    private Integer id;

    @Column(name="title")
    private String title;

    //@Column(name="status",nullable=false,columnDefinition = "DEFAULT false")
    @Column(name="status")
    private Boolean status;

    public Todo( String title) {
        this.title=title;
    }

    @PrePersist
    public void prePersist() {
        if(status==null){
            status=false;
        }
    }


    //Neu mot truong mang gia tri mac dinh co the set nhu sau
    //C1: private status=false
    //C2:columnDefinition
    //C3: su dung lifecycle
}
