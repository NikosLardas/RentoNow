package com.bead.rentonow.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private BigDecimal price;
    private String description;
    private String location;
    private String contactInfo;
    private LocalDate availableStartDate;
    private LocalDate availableEndDate;
    private String image;

    @ManyToOne
    private Person host;

    @OneToMany(mappedBy = "property")
    private List<Booking> bookings;
}