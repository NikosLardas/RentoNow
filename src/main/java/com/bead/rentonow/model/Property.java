package com.bead.rentonow.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;
    private String description;
    private String Location;
    private String contactInfo;

    // To be converted to a Buffered Image / BLOB. Needs re-check/
    private byte[] imageBytes;

    @ElementCollection
    private List<Date> rentingDates;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @OneToMany(mappedBy = "property")
    private List<Booking> bookings;
}
