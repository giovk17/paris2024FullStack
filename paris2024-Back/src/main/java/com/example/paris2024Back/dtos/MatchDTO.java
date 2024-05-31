package com.example.paris2024Back.dtos;

import com.example.paris2024Back.enums.Sports;
import com.example.paris2024Back.interfaces.CheckStartDate;
import com.example.paris2024Back.interfaces.OlympicNumOneConstraint;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MatchDTO {
   @NotNull(message = "Id is required")
    private Long id;



    @NotBlank(message = "Sport name is required")
    private Sports sportName;



    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Date should be in future or present")
    @CheckStartDate
    private LocalDate startDate;



    @NotNull(message = "Start hour is required")
    @Min(value = 8, message = "Matches start from 8h")
    @Max(value = 24, message = "Matches cant start after 24h")
    private Integer startHour;



    @NotBlank(message = "Stadium name is required")
    private String stadiumName;



    @Size(max = 2, message = "Max 2 disciplines")
    private List<String> discipline;



    @NotNull(message = "Olympic nr one is required")
    @OlympicNumOneConstraint
    private Long olympicNumOne;



    @Setter(AccessLevel.NONE)
    private Long olympicNumTwo;



    @Min(value = 0, message = "Free seats must be between 0 and 50")
    @Max(value = 50, message = "Free seats must be between 0 and 50")
    private int freeSeats;


    @Min(value = 1, message = "Ticket price must be between 0 and 150")
    @Max(value = 149 , message = "Ticket price must be between 0 and 150")
    private List<TicketDTO> soldTickets;

    private double ticketPrice;

    @Builder
    public MatchDTO(long id, Sports sportName, LocalDate startDate, int startHour, String stadiumName, List<String> discipline, long olympicNumOne, int freeSeats, List<TicketDTO> soldTickets, double ticketPrice ) {
        this.id = id;
        this.sportName = sportName;
        this.startDate = startDate;
        this.startHour = startHour;
        this.stadiumName = stadiumName;
        this.discipline = discipline == null ? new ArrayList<>(): discipline;
        this.olympicNumOne = olympicNumOne;
        this.olympicNumTwo = calculateOlympicNumTwo(this.olympicNumOne);
        this.freeSeats = freeSeats;
        this.ticketPrice = ticketPrice;
        this.soldTickets = soldTickets.isEmpty() ? new ArrayList<>() : soldTickets;

    }

    private long calculateOlympicNumTwo(long olympicNumOne){
        long lowerBound = olympicNumOne - 1000;
        long upperBound = olympicNumOne + 1000;
        return ThreadLocalRandom.current().nextLong(lowerBound, upperBound);
    }

}
