package org.example;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Postagem {

    private UUID uuid;
    private String nome;
    private String email;
    private LocalDate dob;


}
