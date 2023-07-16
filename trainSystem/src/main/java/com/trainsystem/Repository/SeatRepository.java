package com.trainsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trainsystem.Entity.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.status = 0")
    List<Seat> findAvailableSeats(int seatsRequired);
}
