package com.cfs.BM.service;

import com.cfs.BM.entity.City;
import com.cfs.BM.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private City addCity(City city)
    {

        return cityRepository.save(city);
    }
    public List<City> getAllCities()
    {
        return cityRepository.findAll();
    }
    public  City getCityById(long id)
    {
        return cityRepository.findById(id).orElseThrow(()->new RuntimeException("city not found with id:"+id));
    }
}
