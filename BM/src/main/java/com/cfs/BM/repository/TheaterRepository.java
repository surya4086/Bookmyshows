package com.cfs.BM.repository;

import com.cfs.BM.entity.Theater;
import com.cfs.BM.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater,Long> {
   List<Theater> findByCityId(Long cityId);
}
