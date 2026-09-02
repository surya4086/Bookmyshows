package com.cfs.BM.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScreenRequest {
    private Long userId;
    private Long showId;
    private List<Long> seatIds;

}
