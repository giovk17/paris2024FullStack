package com.example.paris2024Back.domains;

import com.example.paris2024Back.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(name = "FULL_NAME")
    private String name;

    @Column(name = "USERNAME", nullable = false)
    @NotBlank(message = "User should provide a username")
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    @NotBlank(message = "User should provide a password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<Ticket> ownedTickets;

    public void purchaseTicket(Ticket ticket){
        if(!this.ownedTickets.contains(ticket)){
            ownedTickets.add(ticket);
        }
    }


}
