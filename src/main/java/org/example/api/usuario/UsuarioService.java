package org.example.api.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.api.exception.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    public Usuario buscarPorUuid(@NotNull UUID usuarioA) {
        return usuarioJpaRepository.findByUuid(usuarioA).orElseThrow(() -> new NaoEncontradoException("Usuário Não emcontrado"));
    }
    private List<Usuario> buscaListaPorUuid(@NotNull UUID usuarioA){
        return usuarioJpaRepository.findByUuidIn(List.of(usuarioA));
    }
}
