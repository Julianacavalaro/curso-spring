package org.example.api.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioJpaRepositoryTest {

    @Autowired
    private UsuarioJpaRepository repository;

    @Test
    void findByUuid() {
        UUID uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440002");
        Optional<Usuario> usuario =  this.repository.findByUuid(uuid);
        Assertions.assertTrue(usuario.isPresent());
        Assertions.assertEquals("Pedro Santos", usuario.get().getNome());
    }

//    @Test
//    void deleteByUuid() {
//    }
//
//    @Test
//    void updateNome() {
//    }
//
//    @Test
//    void findByUuidIn() {
//    }
}