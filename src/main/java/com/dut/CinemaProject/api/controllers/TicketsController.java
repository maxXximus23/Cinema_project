package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.api.controllers.models.Tickets.PurchaseTicket;
import com.dut.CinemaProject.dto.Ticket.PurchaseTicketDto;
import com.dut.CinemaProject.dto.Ticket.TicketDto;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("tickets")
@AllArgsConstructor
public class TicketsController {
    private final ITicketService ticketService;

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelBooking(@PathVariable Long id){
        ticketService.deleteTicket(id);
    }

    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDto> getByUser(@PathVariable Long id){
        return ticketService.getUsersTickets(id);
    }

    @PostMapping("purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PurchaseTicket> purchaseTicket(@RequestBody PurchaseTicket purchaseTicket,
                                                         UriComponentsBuilder uriComponentBuilder){
        try {
            Long createdId = ticketService.purchaseTicket(new PurchaseTicketDto(purchaseTicket));
            UriComponents uriComponents =
                    uriComponentBuilder.path("/ticket/{id}").buildAndExpand(createdId);
            var location = uriComponents.toUri();
            return ResponseEntity.created(location).build();

        }
        catch (ItemNotFoundException itemNotFoundException){
            return ResponseEntity.notFound().build();
        }
        catch (UnsupportedOperationException unsupportedOperationException){
            return new ResponseEntity(unsupportedOperationException.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (IllegalStateException illegalStateException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
