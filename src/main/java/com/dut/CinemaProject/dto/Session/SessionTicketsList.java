package com.dut.CinemaProject.dto.Session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionTicketsList {
    private Long sessionId;
    private Integer rowsAmount;
    private Integer place;
    private HashMap<Integer, Integer> tickets;
}
