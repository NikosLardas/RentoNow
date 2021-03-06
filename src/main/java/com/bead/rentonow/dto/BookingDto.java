package com.bead.rentonow.dto;

import com.bead.rentonow.model.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookingDto {

    private Long id;
    private LocalDate bookingStartDate;
    private LocalDate bookingEndDate;
    private boolean paid;
    private String propertyTitle;
    private String guestName;

    public BookingDto(Booking booking) {
        id = booking.getId();
        bookingStartDate = booking.getBookingStartDate();
        bookingEndDate = booking.getBookingEndDate();
        paid = booking.isPaid();
        propertyTitle = booking.getProperty().getTitle();
        guestName = booking.getGuest().getFullName();
    }

    //mappings
    @JsonIgnore
    public Booking getBooking() {
        Booking booking = new Booking();
        booking.setId(id);
        booking.setBookingStartDate(bookingStartDate);
        booking.setBookingEndDate(bookingEndDate);
        booking.setPaid(paid);
        return booking;
    }
}