package com.example.paris2024Back.controllers;

import com.example.paris2024Back.dtos.TicketDTO;
import com.example.paris2024Back.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200" )
@RequestMapping(path = "/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDTO> getAllTickets(){
        return this.ticketService.getAllTickets();
    }

    @GetMapping(path = "/{userId}/{matchId}", produces = "application/json")
    public TicketDTO findByMatchIdAndUserId(@PathVariable("matchId") Long matchId, @PathVariable("userId") Long userId){
        return this.ticketService.findByMatchIdAndUserId(matchId, userId);
    }
    @GetMapping(path = "/{userId}/{matchId}/all", produces = "application/json")
    public List<TicketDTO> findAllByMatchIdAndUserId(@PathVariable("matchId") Long matchId, @PathVariable("userId") Long userId){
        return this.ticketService.findAllByMatchIdAndUserId(matchId, userId);
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO){
        this.ticketService.createTicket(ticketDTO);
        return new ResponseEntity<>(ticketDTO, HttpStatus.CREATED);
    }
}
