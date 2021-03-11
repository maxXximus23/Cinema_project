package com.dut.CinemaProject.dto.Session;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateSessionData {
    private Long id;
    private Long movieId;
    private Long hallId;
    private LocalDateTime date;
}
