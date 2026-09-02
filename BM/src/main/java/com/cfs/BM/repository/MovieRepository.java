package com.cfs.BM.repository;

import com.cfs.BM.entity.Movie;
import com.cfs.BM.entity.Screen;
import com.cfs.BM.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
   List<Movie> findByGenre(String genre);
   List<Movie> findByLanguage(String language);
   List<Movie>findByTitleContainingIgnoreCase(String title);
}
