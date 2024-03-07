package ru.danilov.onlineshop1.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
@JsonView
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @JsonView(Views.UserDetails.class)
    private String name;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @JsonView(Views.UserDetails.class)
    private Status status;
    @Column(name = "price")
    @JsonView(Views.UserDetails.class)
    private int price;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;
}