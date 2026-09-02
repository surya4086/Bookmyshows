package com.cfs.BM.repository;

import com.cfs.BM.entity.Screen;
import com.cfs.BM.entity.Seat;
import com.cfs.BM.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {
   List<Seat> findByScreenId(Long screenId);
}
