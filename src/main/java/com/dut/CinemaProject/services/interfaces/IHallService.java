package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dto.Hall.HallDto;
import com.dut.CinemaProject.dto.Hall.NewHall;
import com.dut.CinemaProject.dto.Hall.UpdateHallData;

public interface IHallService {
    HallDto createHall(NewHall newHall);
    void deleteHall(Long id);
    HallDto updateHall(Long id, UpdateHallData hall);
    HallDto getHallById(Long id);
}
