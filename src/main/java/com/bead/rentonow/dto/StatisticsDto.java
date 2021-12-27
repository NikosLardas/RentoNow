package com.bead.rentonow.dto;

import lombok.Data;

@Data
public class StatisticsDto {


    private String entityName;
    private long numberOfBookings;

    public StatisticsDto(String name, long bookingsCount) {
        entityName = name;
        numberOfBookings = bookingsCount;
    }
}