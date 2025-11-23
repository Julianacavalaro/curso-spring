package org.example.api.usuario;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    public Usuario buscarPorUuid(@NotNull UUID usuarioA) {
        return usuarioJpaRepository.findByUuid(usuarioA);
    }
    public List<Usuario> buscaListaPorUuid(@NotNull UUID usuarioA){
        return usuarioJpaRepository.findByListUuid(usuarioA);
    }
}
