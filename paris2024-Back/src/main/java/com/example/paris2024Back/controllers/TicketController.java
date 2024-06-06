package com.example.paris2024Back.controllers;

import com.example.paris2024Back.dtos.CreateTicketDTO;
import com.example.paris2024Back.dtos.TicketDTO;
import com.example.paris2024Back.services.TicketService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200" )
@RequestMapping(path = "/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final Logger logger = LoggerFactory.getLogger(TicketController.class);

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
    public ResponseEntity<CreateTicketDTO> createTicket(@RequestBody CreateTicketDTO createTicketDTO) throws Exception {
        this.ticketService.createTicket(createTicketDTO);
        return new ResponseEntity<>(createTicketDTO, HttpStatus.CREATED);
    }
}
