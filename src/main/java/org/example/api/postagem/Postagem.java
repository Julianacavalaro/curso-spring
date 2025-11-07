package org.example.api.postagem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Postagem {

    @Id
    @GeneratedValue
    private Long id;

    private UUID uuid;
    private String nome;
    private String email;
    private LocalDate dob;
}
