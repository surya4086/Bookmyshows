package com.cfs.BM.service;

import com.cfs.BM.dto.TheaterRequest;
import com.cfs.BM.entity.City;
import com.cfs.BM.entity.Theater;
import com.cfs.BM.repository.CityRepository;
import com.cfs.BM.repository.TheaterRepository;
import com.cfs.BM.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;
    private final CityService cityService;

    public Theater addTheater(TheaterRequest request)
    {
        City city=cityService.getCityById(request.getCityId());
        Theater theater= Theater.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(city)
                .build();
        return theaterRepository.save(theater);

    }
    public List<Theater> getAllTheaters()
    {
        return theaterRepository.findAll();
    }
    public Theater getTheaterById(long id)
    {
        return theaterRepository.findById(id).orElseThrow(()->new RuntimeException("Theater not found with id"+id));
    }
    public List<Theater> getTheatersByCityId(long cityId)
    {
        return theaterRepository.findByCityId(cityId);
    }

}
