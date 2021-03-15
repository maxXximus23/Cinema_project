package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Hall;
import com.dut.CinemaProject.dao.repos.HallRepository;
import com.dut.CinemaProject.dto.Hall.HallDto;
import com.dut.CinemaProject.dto.Hall.NewHall;
import com.dut.CinemaProject.dto.Hall.UpdateHallData;
import com.dut.CinemaProject.exceptions.BadRequestException;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.IHallService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HallService implements IHallService {

    private final HallRepository hallRepository;

    @Override
    public HallDto createHall(NewHall newHall) {
        if(newHall.getName().isBlank() || newHall.getPlaces() < 1 || newHall.getRowsAmount() < 1)
            throw new BadRequestException("Invalid info about the Hall");
        else if (hallRepository.findByName(newHall.getName()).size() > 0)
            throw new BadRequestException("This name is already used");

        Hall hall = new Hall();
        hall.setName(newHall.getName());
        hall.setRowsAmount(newHall.getRowsAmount());
        hall.setPlaces(newHall.getPlaces());

        //return hallRepository.save(hall).getId();
        return new HallDto(hallRepository.save(hall));
    }

    @Override
    public void deleteHall(Long id) {
        Hall hall = hallRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Hall is not found"));
        hallRepository.delete(hall);
    }

    @Override
    public HallDto updateHall(Long id, UpdateHallData hall) {
        Hall updateHall = hallRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Hall is not found"));

        if(hall.getName() != null){
            if(hall.getName().isBlank())
                throw new BadRequestException("Name can`t be empty");
            else if(hallRepository.findByName(hall.getName()).size() > 0)
                throw new BadRequestException("This name is already used");
            else
                updateHall.setName(hall.getName());
        }

        if(hall.getRowsAmount() != null) {
            if (hall.getRowsAmount() < 1)
                throw new BadRequestException("Rows amount can`t be less than 1");
            else
                updateHall.setRowsAmount(hall.getRowsAmount());
        }


        if(hall.getPlaces() != null) {
            if (hall.getPlaces() < 1)
                throw new BadRequestException("Places amount can`t be less than 1");
            else
                updateHall.setPlaces(hall.getPlaces());
        }

        return new HallDto(hallRepository.save(updateHall));
    }

    @Override
    public HallDto getHallById(Long id) {
        return new HallDto(hallRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Hall is not found")));
    }
}
