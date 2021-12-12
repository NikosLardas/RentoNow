package com.bead.rentonow.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private char role;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "host")
    private List<Property> properties;

    @OneToMany(mappedBy = "host")
    private List<Booking> bookings;
}
