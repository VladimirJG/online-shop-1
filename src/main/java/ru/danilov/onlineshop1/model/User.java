package ru.danilov.onlineshop1.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
@JsonView
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @JsonView(Views.UserSummary.class)
    private String name;
    @Column(name = "email")
    @Email
    @JsonView(Views.UserSummary.class)
    private String email;
    @Transient
    @JsonView(Views.UserDetails.class)
    private List<Order> orderList;
}