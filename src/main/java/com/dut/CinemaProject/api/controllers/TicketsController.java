package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Ticket.TicketDto;
import com.dut.CinemaProject.services.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("tickets")
public class TicketsController {
    @Autowired
    private ITicketService ticketService;

    @GetMapping("user/{id}")
    public String getByUser(@PathVariable Long id, Model model){
        List<TicketDto> tickets;
        try{
            tickets = ticketService.getUsersTickets(id);
        }
        catch (ItemNotFoundException ex){
            model.addAttribute("error", ex);
            return "error";
        }
        model.addAttribute("tickets", tickets);
        return "userTickets";
    }
}
