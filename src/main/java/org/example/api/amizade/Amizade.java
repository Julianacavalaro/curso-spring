package org.example.api.amizade;

import jakarta.persistence.*;
import lombok.*;
import org.example.api.usuario.Usuario;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Amizade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_a")
    private Usuario usuarioA;

    @ManyToOne
    @JoinColumn(name = "usuario_b")
    private Usuario usuarioB;

    //@Contract(pure = true)
    public Amizade(Usuario usuarioA, Usuario usuarioB) {
        this.usuarioA = usuarioA;
        this.usuarioB = usuarioB;
    }
}
