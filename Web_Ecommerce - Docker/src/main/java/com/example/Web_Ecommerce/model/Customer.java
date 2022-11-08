package com.example.Web_Ecommerce.model;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "image", "phone_number"}))
@TypeDef(
        name="json",
        typeClass = JsonStringType.class
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Size(min = 3, max = 15, message = "First name should have 3-15 characters")
    private String firstName;
    @Size(min = 3, max = 15, message = "Last name should have 3-15 characters")
    private String lastName;
    private String username;
    private String country;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;

    private String password;
    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private String image;

    @Column(name = "city")
    private String city;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @OneToOne(mappedBy = "customer")
    private ShoppingCart shoppingCart;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @Type(type="json")
    @Column(name="roles",nullable=false,columnDefinition = "json")
    private List<String> roles;

    private boolean is_activated;
    private boolean is_deleted;

    public Customer(String firstName,String lastName, String username, String password, List<String> roles) {
        this.firstName = firstName;
        this.lastName=lastName;
        this.username=username;
        this.password = password;
        this.roles = roles;
    }

//    @PreRemove
//    void predelete(){
//
//    }
}