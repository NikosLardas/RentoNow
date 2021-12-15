package com.bead.rentonow.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
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
    private Date availableStartDate;
    private Date availableEndDate;

    // Image possibly as a BLOB. Needs re-check/
    @Lob
    private Blob imageBlob;

    @ManyToOne
    private Person host;

    @OneToMany(mappedBy = "property")
    private List<Booking> bookings;
}