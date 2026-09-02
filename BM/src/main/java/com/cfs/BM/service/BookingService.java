package com.cfs.BM.service;

import com.cfs.BM.dto.BookingRequest;
import com.cfs.BM.entity.Booking;
import com.cfs.BM.entity.Seat;
import com.cfs.BM.entity.Show;
import com.cfs.BM.entity.User;
import com.cfs.BM.enums.BookingStatus;
import com.cfs.BM.repository.BookingRepository;
import com.cfs.BM.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
private final BookingRepository bookingRepository;
private final SeatRepository seatRepository;
private final UserService userService;
private final ShowService showService;
@Transactional
public Booking createBooking(BookingRequest request){
    User user=userService.getUserById(request.getUserId());
    Show show=showService.getShowById(request.getShowId());

    //check if any of the requested aet are already booked
    List<Long> alreadyBookedSeats=bookingRepository.findBookingIdsByShowId(show.getId());
    for ( Long seatId : request.getSeatIds() ) {
        if(alreadyBookedSeats.contains(seatId)){
            throw new RuntimeException("seat with id:"+seatId+" is already Booked");
        }
    }
     List<Seat> seats=seatRepository.findAllById(request.getSeatIds());
    if(seats.size()!=request.getSeatIds().size()){
        throw new RuntimeException("some seats are invalid");
    }
    double totalPrice=seats.size()*show.getTicketPrice();
    Booking booking=Booking.builder()
            .user(user)
            .show(show)
            .seats(seats)
            .totalprice(totalPrice)
            .status(BookingStatus.CONFIRMED)
            .build();
    return bookingRepository.save(booking);
}
public Booking getBookingById(Long id){
    return bookingRepository.findById(id).orElseThrow(()->new RuntimeException("Booking not found with "+id));
}
public List<Booking> getBookingByUser(Long userId){
    return bookingRepository.findByUserId(userId);
}
@Transactional
    public  Booking cancelBooking(Long bookingId){
    Booking booking =getBookingById(bookingId);
    booking.setStatus(BookingStatus.CANCELLED);
    return bookingRepository.save(booking);
}
public List<Seat>getAvailableSeats(Long showId){
    Show show=showService.getShowById(showId);
    List<Seat> allSeats=seatRepository.findByScreenId(show.getScreen().getId());
    List<Long> bookingSeatIds=bookingRepository.findBookingIdsByShowId(showId);
    return allSeats.stream()
            .filter(seat -> !bookingSeatIds.contains(seat.getId()))
            .toList();
}
}
