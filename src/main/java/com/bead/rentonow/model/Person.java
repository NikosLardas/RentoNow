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

    private String role;
    private String fullName;
    private String username;
    private String password;
    private boolean enabled;

    @OneToMany(mappedBy = "host")
    private List<Property> properties;

    @OneToMany(mappedBy = "guest")
    private List<Booking> bookings;
}