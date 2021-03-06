package com.dut.CinemaProject.dto.Movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateMovieData {
    private Long id;
    private String title;
    private String description;
    private String posterPath;
    private String trailerPath;
    private Integer duration;
}
