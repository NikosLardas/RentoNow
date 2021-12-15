package com.bead.rentonow.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date bookingStartDate;
    private Date bookingEndDate;
    private int isPaid; // Changed Boolean to int, as Boolean cannot be saved to DB.

    @ManyToOne
    private Property property;

    @ManyToOne
    private Person guest;
}