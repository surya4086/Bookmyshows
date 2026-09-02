package com.cfs.BM.controller;

import com.cfs.BM.entity.Screen;
import com.cfs.BM.service.ScreenService;
import com.cfs.BM.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/screen")
@RequiredArgsConstructor
public class ScreenController {
    private final ScreenService screenService;
    @GetMapping("/{id}")
    public ResponseEntity<Screen> getScreenById(@PathVariable Long id) {
        return ResponseEntity.ok(screenService.getScreenById(id));
    }
    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<Screen>> getScreenByTheaterId(@PathVariable Long theaterId) {
        return ResponseEntity.ok(screenService.getScreenByTheater(theaterId));
    }

}
