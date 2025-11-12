package org.example.api.postagem;

import jakarta.persistence.*;
import lombok.*;
import org.example.api.usuario.Usuario;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;
    private String nome;
    private String email;
    private LocalDate dob;

    @ManyToOne // Muitas postagens para 1 usuário
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
}
