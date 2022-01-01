package com.bead.rentonow.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate bookingStartDate;
    private LocalDate bookingEndDate;
    private boolean isPaid;

    @ManyToOne
    private Property property;

    @ManyToOne
    private Person guest;
}