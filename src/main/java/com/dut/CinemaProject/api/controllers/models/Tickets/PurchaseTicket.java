package com.dut.CinemaProject.api.controllers.models.Tickets;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class PurchaseTicket {
    public PurchaseTicket(Optional<Long> userId, Long sessionId, Integer row, Integer place){
        this.userId = userId;
        this.sessionId = sessionId;
        this.row = row;
        this.place = place;
    }

    private Long sessionId;
    private Optional<Long> userId;
    private Integer row;
    private Integer place;
}