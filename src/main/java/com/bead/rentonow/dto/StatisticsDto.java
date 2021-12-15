package com.bead.rentonow.dto;

import lombok.Data;

@Data
public class StatisticsDto {

    private int numberOfBookings;
    private String guestName;

    public StatisticsDto(int bookingsCount, String name) {
        numberOfBookings = bookingsCount;
        guestName = name;
    }
}
