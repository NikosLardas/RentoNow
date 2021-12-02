package com.bead.rentonow.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

// ** Optional Class as a possible project extension **

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date paymentDate;       // The date when the payment was issued.
    private String paymentType;     // Credit Card, Cash, Debit Card etc...
    private char paymentStatus;     // 'C' for Completed, 'P' for Pending

    @OneToOne(mappedBy = "payment")
    private Booking booking;
}
