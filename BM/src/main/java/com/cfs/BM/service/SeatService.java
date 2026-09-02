package com.cfs.BM.service;

import com.cfs.BM.entity.Seat;
import com.cfs.BM.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    //addSeat
public List<Seat> getSeatsByScreen(Long screenId){
    return seatRepository.findByScreenId(screenId);
}
public Seat getSeatById(Long id){
    return seatRepository.findById(id).orElseThrow(()->new RuntimeException("seat not found with id:"+id));
}
}
