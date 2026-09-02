package com.cfs.BM.repository;

import com.cfs.BM.entity.City;
import com.cfs.BM.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Long> {

}
