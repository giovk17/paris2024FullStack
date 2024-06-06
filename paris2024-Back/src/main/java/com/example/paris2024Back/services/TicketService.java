package com.example.paris2024Back.services;

import com.example.paris2024Back.domains.Match;
import com.example.paris2024Back.domains.Ticket;
import com.example.paris2024Back.domains.User;
import com.example.paris2024Back.dtos.CreateTicketDTO;
import com.example.paris2024Back.dtos.TicketDTO;
import com.example.paris2024Back.mappers.TicketMapper;
import com.example.paris2024Back.repositories.MatchRepository;
import com.example.paris2024Back.repositories.TicketRepo;
import com.example.paris2024Back.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TicketService {

    private final TicketRepo ticketRepo;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;
    private final Logger logger = LoggerFactory.getLogger(TicketService.class);

    public List<TicketDTO> getAllTickets(){
        return this.ticketMapper.toDto(this.ticketRepo.findAll());
    }

    public TicketDTO findByMatchIdAndUserId(Long matchId, Long userId){
        TicketDTO selected =  this.ticketMapper.toDto(this.ticketRepo.findByMatchIdAndUserId(matchId, userId));
        try {
            return selected;
        }catch (NullPointerException e){
            throw new NullPointerException("Ticket not found");
        }
    }

    public List<TicketDTO> findAllByMatchIdAndUserId(Long matchId, Long userId){
        List<TicketDTO> selected = this.ticketMapper.toDto(this.ticketRepo.findAllByMatchIdAndUserId(matchId, userId));
        try {
            return selected;
        }catch (NullPointerException e){
            throw new NullPointerException("Tickets not found");
        }
    }

    @Transactional
    public void createTicket(CreateTicketDTO createTicketDTO) throws Exception {
        User user = this.userRepository.findById(createTicketDTO.getUserId()).orElseThrow(() -> new NullPointerException("User not found") );
        Match match = this.matchRepository.findById(createTicketDTO.getMatchId()).orElseThrow(() -> new NullPointerException("Match not found"));
        Ticket ticket = new Ticket(user, match);

        this.ticketRepo.save(ticket);
        user.purchaseTicket(ticket);
        match.sellTicket(ticket);

    }
}
