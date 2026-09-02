package com.cfs.BM.service;

import com.cfs.BM.dto.ShowRequest;
import com.cfs.BM.entity.Movie;
import com.cfs.BM.entity.Screen;
import com.cfs.BM.entity.Show;
import com.cfs.BM.repository.MovieRepository;
import com.cfs.BM.repository.ShowRepository;
import com.cfs.BM.repository.TheaterRepository;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ShowService   {
private final ShowRepository showRepository;
private final MovieService movieService;
private final ScreenService screenService;
//addshow
    public Show addShow(ShowRequest request)
    {
        Movie movie=movieService.getMovieById(request.getMovieId());
        Screen screen=screenService.getScreenById(request.getScreenId());
        Show show=Show.builder()
                .movie(movie)
                .screen(screen)
                .showDate(request.getShowDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .ticketPrice(request.getTicketPrice())
                .build();
        return showRepository.save(show);
    }
    public List<Show> getAllShow()
    {
        return showRepository.findAll();
    }
    public  Show getShowById(Long id)
    {
        return showRepository.findById(id).orElseThrow(()->new RuntimeException("show not found with id "+id));
    }
    public List<Show> getShowByMovie(Long MovieId)
    {
        return showRepository.findByMovieId(MovieId);
    }
    public List<Show> getShowByMovieAndDate(Long movieId, LocalDate date)
    {
        return showRepository.findByMovieIdAndShowDate(movieId,date);
    }
    //getshowByScreen

}
