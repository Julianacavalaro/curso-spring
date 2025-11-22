package org.example.api.amizade;

import jakarta.persistence.*;
import lombok.*;
import org.example.api.usuario.Usuario;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AmizadeDTO {

    private Long id;
    private Usuario usuarioA;
    private Usuario usuarioB;

}