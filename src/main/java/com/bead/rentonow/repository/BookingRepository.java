package com.bead.rentonow.repository;

import com.bead.rentonow.dto.StatisticsDto;
import com.bead.rentonow.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    //Example of how we will use the StatisticsDTO and a query to map the results and show statistics

    //@Query("Select c from Customer c where c.email like %:email%")
    //List<StatisticsDto> getCustomersUsingEmail(@Param("email")String email);
}
