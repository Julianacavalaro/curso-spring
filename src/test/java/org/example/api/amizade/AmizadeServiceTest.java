package org.example.api.amizade;

import org.example.api.exception.AmizadeInvalidadeBusinessException;
import org.example.api.usuario.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class AmizadeServiceTest {

    @Mock
    private  AmizadeRepository repository;
    @Mock
    private  UsuarioService usuarioService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private AmizadeService amizadeService;

    @Test
    void falhaCriarAmizade() {
        //cenário
    UUID usuarioA = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
    UUID usuarioB = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
    //ação
    //this.amizadeService.criarAmizade(usuarioA, usuarioB);
    //validação
        Assertions.assertThrows(AmizadeInvalidadeBusinessException.class, ()-> this.amizadeService.criarAmizade(usuarioA, usuarioB));

    }
}