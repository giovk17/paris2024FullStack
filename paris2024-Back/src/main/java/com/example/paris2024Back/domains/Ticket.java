package com.example.paris2024Back.domains;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "TICKETS")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull(message = "User id is required")
    private User user;

    @ManyToOne
    @JoinColumn(name = "MATCH_ID", nullable = false)
    @NotNull(message = "Match id is required")
    private Match match;

    public Ticket(User user, Match match){
        this.user = user;
        this.match = match;
    }
}
