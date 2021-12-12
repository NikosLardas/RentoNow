package com.bead.rentonow.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Role role;
    private String fullName;

    @OneToMany(mappedBy = "host")
    private List<Property> properties;

    @OneToMany(mappedBy = "guest")
    private List<Booking> bookings;
}