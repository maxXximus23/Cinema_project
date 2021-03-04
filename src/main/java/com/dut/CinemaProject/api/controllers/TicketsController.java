package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Ticket.TicketDto;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketsController {
    @Autowired
    private ITicketService ticketService;

    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDto> getByUser(@PathVariable Long id){
        return ticketService.getUsersTickets(id);
    }
}
