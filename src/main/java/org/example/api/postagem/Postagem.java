//package org.example.api.postagem;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.example.api.usuario.Usuario;
//
//import java.time.LocalDate;
//import java.util.UUID;
//
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class Postagem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private UUID uuid;
//    private String nome;
//    private String email;
//    private LocalDate dob;
//
//    @ManyToOne // Muitas postagens para 1 usuário
//    @JoinColumn(name = "usuario_id")
//    private Usuario autor;
//}

package org.example.api.postagem;

import jakarta.persistence.*;
import lombok.*;
import org.example.api.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "postagem")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID uuid;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String corpo;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // getters e setters (ou @Data/@Getter/@Setter do Lombok)
}
