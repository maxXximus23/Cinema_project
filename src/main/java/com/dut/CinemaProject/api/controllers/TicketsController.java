package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Ticket.TicketDto;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketsController {
    @Autowired
    private ITicketService ticketService;

    @GetMapping("user/{id}")
    public ResponseEntity<List<TicketDto>> getByUser(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.getUsersTickets(id));
    }
}
