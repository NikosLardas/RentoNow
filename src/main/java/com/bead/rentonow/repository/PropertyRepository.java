package com.bead.rentonow.repository;

import com.bead.rentonow.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("Select p from Property p where p.location = :location and p.price <= :price and " +
            "p.availableStartDate <= :startDate and p.availableEndDate >= :endDate and p.id not in " +
            "(select b.property from Booking b where (:startDate between b.bookingStartDate and b.bookingEndDate) " +
            "or (:endDate between b.bookingStartDate and b.bookingEndDate) or (b.bookingStartDate >= :startDate and b.bookingEndDate <= :endDate))")
    List<Property> getPropertiesFiltered(@Param("location") String location,@Param("price") BigDecimal price,
                                         @Param("startDate")LocalDate startDate,@Param("endDate") LocalDate endDate);
}