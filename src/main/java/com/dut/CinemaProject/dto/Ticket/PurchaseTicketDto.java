package com.dut.CinemaProject.dto.Ticket;

import com.dut.CinemaProject.api.controllers.models.Tickets.PurchaseTicket;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class PurchaseTicketDto {
    public PurchaseTicketDto(PurchaseTicket purchaseTicket){
        this.userId = purchaseTicket.getUserId();
        this.sessionId = purchaseTicket.getSessionId();
        this.row = purchaseTicket.getRow();
        this.place = purchaseTicket.getPlace();
    }

    private Long sessionId;
    private Optional<Long> userId;
    private Integer row;
    private Integer place;
}
