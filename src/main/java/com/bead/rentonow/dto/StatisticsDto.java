package com.bead.rentonow.dto;

import lombok.Data;

@Data
public class StatisticsDto {

    private int numberOfBookings;
    private String entityName;

    public StatisticsDto(int bookingsCount, String name) {
        numberOfBookings = bookingsCount;
        entityName = name;
    }
}