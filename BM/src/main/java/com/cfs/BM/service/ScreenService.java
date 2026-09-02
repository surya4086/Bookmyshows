package com.cfs.BM.service;


import com.cfs.BM.entity.Screen;
import com.cfs.BM.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import com.cfs.BM.entity.Seat;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ScreenService {
    private final ScreenRepository screenRepository;
    private final TheaterService theaterService;
    //addscreen
//    public List<Screen> getAllScreen() {
//        return screenRepository.findAll();
//    }
    public List<Screen> getAllScreen()
    {
        return screenRepository.findAll();
    }
    public Screen getScreenById(long id)
    {
        return screenRepository.findById(id).orElseThrow(()->new RuntimeException("screen not found with id :" +id));
    }
    public List<Screen> getScreenByTheater(Long theaterId)
    {
        return screenRepository.findByTheaterId(theaterId);
    }
}
