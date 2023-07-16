package com.trainsystem.Controller;

import org.springframework.http.ResponseEntity;

import com.trainsystem.Entity.Seat;

public interface SeatReservationController {
    ResponseEntity<Iterable<Seat>> getSeatLayout();
    ResponseEntity<String> reserveSeats(int seatsToReserve);
}
