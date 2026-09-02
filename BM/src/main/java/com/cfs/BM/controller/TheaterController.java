package com.cfs.BM.controller;

import com.cfs.BM.dto.TheaterRequest;
import com.cfs.BM.entity.Theater;
import com.cfs.BM.service.TheaterService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;
    @PostMapping("/addtheater")
    private ResponseEntity<Theater> addTheater(@RequestBody TheaterRequest request){
        return ResponseEntity.ok(theaterService.addTheater(request));
    }
    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters()
    {
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id)
    {
        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }
    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<Theater>> getAllTheatersByCity(@RequestParam Long cityId)
    {
        return ResponseEntity.ok(theaterService.getTheatersByCityId(cityId));
    }

}
