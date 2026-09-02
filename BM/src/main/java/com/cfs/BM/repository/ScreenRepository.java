package com.cfs.BM.repository;

import com.cfs.BM.entity.Screen;
import com.cfs.BM.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen,Long> {
   List<Screen> findByTheaterId(Long theaterId);
}
