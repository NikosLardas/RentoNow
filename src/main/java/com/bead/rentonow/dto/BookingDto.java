package com.bead.rentonow.dto;

import com.bead.rentonow.model.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BookingDto {

    private Long id;
    private Date bookingStartDate;
    private Date bookingEndDate;
    private boolean isPaid;
    private String propertyTitle;
    private String guestName;

    public BookingDto(Booking booking) {
        id = booking.getId();
        bookingStartDate = booking.getBookingStartDate();
        bookingEndDate = booking.getBookingEndDate();
        isPaid = booking.getIsPaid() != 0;
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
        booking.setIsPaid(isPaid? 1 : 0);
        return booking;
    }
}