package org.example.api.amizade;

import org.example.api.exception.AmizadeInvalidadeBusinessException;
import org.example.api.usuario.Usuario;
import org.example.api.usuario.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class AmizadeServiceTest {

    @Mock
    private  AmizadeRepository amizadeRepository;
    @Mock
    private UsuarioService usuarioService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private AmizadeService amizadeService;

    @Test
    @DisplayName("Sucesso ao criar amizade")
    void sucessoCriarAmizade() {
        //cenário
        UUID usuarioA = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        UUID usuarioB = UUID.fromString("550e8400-e29b-41d4-a716-446655440001");
        Mockito.when(this.usuarioService.buscarPorUuid(Mockito.any())).thenReturn(new Usuario());
        Mockito.when(this.amizadeRepository.save(Mockito.any())).thenReturn(new Amizade());
        Mockito.when(this.modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(new AmizadeDTO());
        //ação
        AmizadeDTO amizadeDTO = this.amizadeService.criarAmizade(usuarioA,usuarioB);
        //validação
        Assertions.assertEquals(new AmizadeDTO(), amizadeDTO);
    }

    @Test
    @DisplayName("Falha ao criar amizade")
    void falhaCriarAmizade() {
        //cenário
    UUID usuarioA = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
    UUID usuarioB = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
    //ação
    //this.amizadeService.criarAmizade(usuarioA, usuarioB);
    //validação
        Assertions.assertThrows(AmizadeInvalidadeBusinessException.class, ()-> this.amizadeService.criarAmizade(usuarioA, usuarioB));
    }

    @Test
    @DisplayName("Sucesso ao listar amizades (listarTodos)")
    void sucessoListarTodos() {
        // CENÁRIO
        Pageable pageable = PageRequest.of(0, 10);

        Amizade amizade1 = new Amizade();
        Amizade amizade2 = new Amizade();

        List<Amizade> listaAmizades = List.of(amizade1, amizade2);
        Page<Amizade> pageAmizades = new PageImpl<>(listaAmizades, pageable, listaAmizades.size());

        // Mock do repository
        Mockito.when(this.amizadeRepository.findAll(pageable)).thenReturn(pageAmizades);

        // Mock do ModelMapper / convertToDto
        AmizadeDTO dto1 = new AmizadeDTO();
        AmizadeDTO dto2 = new AmizadeDTO();

        Mockito.when(this.modelMapper.map(amizade1, AmizadeDTO.class)).thenReturn(dto1);
        Mockito.when(this.modelMapper.map(amizade2, AmizadeDTO.class)).thenReturn(dto2);

        // AÇÃO
        Page<AmizadeDTO> resultado = this.amizadeService.listarTodos(pageable);

        // VALIDAÇÃO
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(2, resultado.getContent().size());

        // como o service criou new PageImpl<>(listaConvertida) sem pageable/total,
        // aqui o totalElements será igual ao tamanho da lista (2)
        Assertions.assertEquals(2, resultado.getTotalElements());

        // valida se os DTOs retornados são exatamente os mapeados
        Assertions.assertEquals(dto1, resultado.getContent().get(0));
        Assertions.assertEquals(dto2, resultado.getContent().get(1));

        // verifica se chamou o repository e o mapper como esperado
        Mockito.verify(this.amizadeRepository, Mockito.times(1)).findAll(pageable);
        Mockito.verify(this.modelMapper, Mockito.times(1)).map(amizade1, AmizadeDTO.class);
        Mockito.verify(this.modelMapper, Mockito.times(1)).map(amizade2, AmizadeDTO.class);
    }
}