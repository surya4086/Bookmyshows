package com.cfs.BM.service;

import com.cfs.BM.entity.Movie;
import com.cfs.BM.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    public Movie getMovieById(long id)
    {
        return movieRepository.findById(id).orElseThrow(()->new RuntimeException("movie not found with id:"+id));
    }
    public List<Movie>searchByTitle(String title)
    {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }
    public List<Movie>getByGenre(String genre)
    {
        return movieRepository.findByGenre(genre);
    }
    public List<Movie>getByLanguage(String language)
    {
        return movieRepository.findByLanguage(language);
    }
    //update movie
    //delet movie
}
