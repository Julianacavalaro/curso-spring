package org.example.api.amizade;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.api.exception.AmizadeInvalidadeBusinessException;
import org.example.api.usuario.Usuario;
import org.example.api.usuario.UsuarioJpaRepository;
import org.example.api.usuario.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class AmizadeService {

    private final AmizadeRepository repository;
    private final UsuarioService usuarioService;
    private final ModelMapper modelMapper;

    public Page<AmizadeDTO> listarTodos(Pageable pageable) {
        Page<Amizade> usuarios = this.repository.findAll(pageable);
        return new PageImpl<>(usuarios.getContent().stream().map(this::convertToDto).toList());
    }
    public AmizadeDTO convertToDto(Amizade amizade ){
        return this.modelMapper.map(amizade, AmizadeDTO.class);
    }

    // conversão dentro da classe (não precisa importar nada)
    public AmizadeDTO criarAmizade(@NotNull UUID usuarioA, UUID usuarioB) {
        if (usuarioA.equals(usuarioB)){
            throw new AmizadeInvalidadeBusinessException("É  preciso indicar dois usuários diferentes");
        }
        Usuario usuarioEntityA = this.usuarioService.buscarPorUuid(usuarioA);
        Usuario usuarioEntityB = this.usuarioService.buscarPorUuid(usuarioB);
        Amizade amizade = this.repository.save(new Amizade(usuarioEntityA,usuarioEntityB));
        return convertToDto(amizade);
    }

}
