package com.bead.rentonow.dto;

import com.bead.rentonow.model.Booking;
import lombok.Data;
import java.util.Date;

@Data
public class BookingDto {

    private Long id;
    private Date bookingStartDate;
    private Date bookingEndDate;
    private int isPaid;
    private String propertyTitle;
    private String guestName;

    public BookingDto(Booking booking) {
        id = booking.getId();
        bookingStartDate = booking.getBookingStartDate();
        bookingEndDate = booking.getBookingEndDate();
        isPaid = booking.getIsPaid();
        propertyTitle = booking.getProperty().getTitle();
        guestName = booking.getGuest().getFullName();
    }

    //mappings
    public Booking getBooking() {
        Booking booking = new Booking();
        booking.setId(id);
        booking.setBookingStartDate(bookingStartDate);
        booking.setBookingEndDate(bookingEndDate);
        booking.setIsPaid(isPaid);

        // Missing property and guest back mapping

        return booking;
    }
}
