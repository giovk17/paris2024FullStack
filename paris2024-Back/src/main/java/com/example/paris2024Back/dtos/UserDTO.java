package com.example.paris2024Back.dtos;

import com.example.paris2024Back.domains.Ticket;
import com.example.paris2024Back.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDTO {
    @NotNull(message = "Id is required")
    private Long id;


    private String name;

    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "private is required")
    private String password;

    @NotBlank(message = "Role is required")
    private UserRole userRole;


    private List<Ticket> ownedTickets;
}
