package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Ticket.TicketDto;
import com.dut.CinemaProject.services.interfaces.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets")
@AllArgsConstructor
public class TicketsController {
    private ITicketService ticketService;

    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDto> getByUser(@PathVariable Long id){
        return ticketService.getUsersTickets(id);
    }
}
