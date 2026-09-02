package com.cfs.BM.controller;

import com.cfs.BM.dto.BookingRequest;
import com.cfs.BM.entity.Booking;
import com.cfs.BM.entity.Seat;
import com.cfs.BM.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
private final BookingService bookingService;
@PostMapping
    public ResponseEntity<Booking> booking(@RequestBody BookingRequest request)
    {
        return ResponseEntity.ok(bookingService.createBooking(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id)
        {
        return ResponseEntity.ok(bookingService.getBookingById(id));
        }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingByUser(@PathVariable Long userId)
        {
        return ResponseEntity.ok(bookingService.getBookingByUser(userId));
        }
    @PutMapping("/{id}/cancle")
    public ResponseEntity<Booking> cancleBooking(@PathVariable Long id)
        {
        return ResponseEntity.ok(bookingService.cancelBooking(id));
        }
        @GetMapping("/show/{showId}/available-seats")
    public  ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable Long showId)
        {
            return ResponseEntity.ok(bookingService.getAvailableSeats(showId));
        }
}
