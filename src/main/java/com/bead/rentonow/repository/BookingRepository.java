package com.bead.rentonow.repository;

import com.bead.rentonow.dto.StatisticsDto;
import com.bead.rentonow.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Queries to the database for Statistics of bookings per type

    // Query database for Statistics of bookings per guest
    @Query("Select new com.bead.rentonow.dto.StatisticsDto(p.fullName,count(b.id)) from Booking b Join Person p on b.guest=p group by p.fullName")
    List<StatisticsDto> getBookingsPerGuest();

    // Query database for Statistics of bookings per property
    @Query("Select new com.bead.rentonow.dto.StatisticsDto(p.title,count(b.id)) from Booking b Join Property p on b.property=p group by p.title")
    List<StatisticsDto> getBookingsPerProperty();

    // Query database for Statistics of bookings per host
    @Query("Select new com.bead.rentonow.dto.StatisticsDto(p.fullName,count(b.id)) from Booking b Join Property pr on b.property=pr Join Person p on pr.host=p group by p.fullName")
    List<StatisticsDto> getBookingsPerHost();
}
