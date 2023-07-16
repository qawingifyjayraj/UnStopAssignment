package com.trainsystem.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trainsystem.Controller.SeatReservationController;
import com.trainsystem.Entity.Seat;
import com.trainsystem.Repository.SeatRepository;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class TrainSeatReservationController implements SeatReservationController {
    private static final int SEATS_PER_ROW = 7;
    private SeatRepository seatRepository;

    @Autowired
    public TrainSeatReservationController(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Seat>> getSeatLayout() {
        Iterable<Seat> seats = seatRepository.findAll();
        return ResponseEntity.ok(seats);
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveSeats(@RequestParam("seatsToReserve") int seatsToReserve) {
        List<Seat> availableSeats = findAvailableSeats(seatsToReserve);

        if (availableSeats.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No seats available.");
        } else {
            for (Seat seat : availableSeats) {
                seat.setStatus(1);
                seatRepository.save(seat);
            }
            return ResponseEntity.ok("Seats reserved successfully.");
        }
    }

    private List<Seat> findAvailableSeats(int seatsRequired) {
        List<Seat> availableSeats = seatRepository.findAvailableSeats(seatsRequired);
        if (availableSeats.size() >= seatsRequired) {
            return availableSeats.subList(0, seatsRequired);
        } else {
            return availableSeats;
        }
    }
}
