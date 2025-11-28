package org.example.api.usuario;

import org.example.api.exception.NaoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    void sucessoBuscarPorUuid() {
        UUID uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440002");
        Usuario usuario =  this.usuarioService.buscarPorUuid(uuid);
        Assertions.assertTrue(Objects.nonNull(usuario));
    }
    @Test
    void falhaBuscarPorUuid() {
        UUID uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440020");
        Assertions.assertThrows(NaoEncontradoException.class, () -> this.usuarioService.buscarPorUuid(uuid));
    }
}