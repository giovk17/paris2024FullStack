package com.example.paris2024Back.domains;

import com.example.paris2024Back.interfaces.CheckStartDate;
import com.example.paris2024Back.interfaces.OlympicNumOneConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;


@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "MATCHES")
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCH_ID")
    private long id;


    @Column(name = "SPORT_NAME", nullable = false)
    @NotBlank(message = "Sport name is required")
    private String sportName;


    @Column(name = "START_DATE", nullable = false)
    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Date should be in future or present")
    @CheckStartDate
    private LocalTime startDate;


    @Column(name = "START_HOUR", nullable = false)
    @NotNull(message = "Start hour is required")
    @Min(value = 8, message = "Matches start from 8h")
    @Max(value = 24, message = "Matches start from 8h")
    private LocalTime startHour;


    @Column(name = "STADIUM_NAME", nullable = false)
    @NotBlank(message = "Stadium name is required")
    private String stadiumName;


    @Column(name = "DISCIPLINE")
    @Size(max = 2, message = "Max 2 disciplines")
    private String discipline;


    @Column(name = "OLYMPIC_NR_ONE")
    @NotNull(message = "Olympic nr one is required")
    @OlympicNumOneConstraint
    private long olympicNumOne;


    @Setter(AccessLevel.NONE)
    @Column(name = "OLYMPIC_NR_TWO")
    private long olympicNumTwo;


    @Column(name = "FREE_SEATS")
    @Min(value = 0, message = "Free seats must be between 0 and 50")
    @Max(value = 50, message = "Free seats must be between 0 and 50")
    private int freeSeats;


    @Min(value = 1, message = "Ticket price must be between 0 and 150")
    @Max(value = 149 , message = "Ticket price must be between 0 and 150")
    @Column(name = "TICKET_PRICE")

    private double ticketPrice;

    @Builder
    public Match(long id, String sportName, LocalTime startDate, LocalTime startHour, String stadiumName, String discipline, long olympicNumOne, int freeSeats, double ticketPrice) {
        this.id = id;
        this.sportName = sportName;
        this.startDate = startDate;
        this.startHour = startHour;
        this.stadiumName = stadiumName;
        this.discipline = discipline;
        this.olympicNumOne = olympicNumOne;
        this.olympicNumTwo = calculateOlympicNumTwo(this.olympicNumOne);
        this.freeSeats = freeSeats;
        this.ticketPrice = ticketPrice;
    }

    private long calculateOlympicNumTwo(long olympicNumOne){
        long lowerBound = olympicNumOne - 1000;
        long upperBound = olympicNumOne + 1000;
        return ThreadLocalRandom.current().nextLong(lowerBound, upperBound);
    }

}
