package com.dut.CinemaProject.dto.Movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMovieData {
    private String title;
    private String description;
    private String posterPath;
    private String trailerPath;
    private Integer duration;
}
