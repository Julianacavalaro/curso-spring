package org.example.api.amizade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.api.usuario.Usuario;
import org.example.api.usuario.UsuarioJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmizadeService {

    private final AmizadeRepository repository;
    private final UsuarioJpaRepository usuarioRepository;

    public Page<AmizadeDTO> listarTodos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toDTO); // usa o método interno
    }

    // conversão dentro da classe (não precisa importar nada)
    private AmizadeDTO toDTO(Amizade amizade) {
        AmizadeDTO dto = new AmizadeDTO();
        dto.setId(amizade.getId());
        dto.setUsuarioA(amizade.getUsuarioA());
        dto.setUsuarioB(amizade.getUsuarioB());
        return dto;
    }

    @Transactional
    public AmizadeDTO criarAmizade(UUID uuidA, UUID uuidB) {

        Usuario usuarioA = usuarioRepository.findByUuid(uuidA)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário A não encontrado"));

        Usuario usuarioB = usuarioRepository.findByUuid(uuidB)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário B não encontrado"));

        // Criando a entidade
        Amizade amizade = new Amizade();
        amizade.setUsuarioA(usuarioA);
        amizade.setUsuarioB(usuarioB);

        amizade = repository.save(amizade);

        // Convertendo para DTO
        return new AmizadeDTO(
                amizade.getId(),
                amizade.getUsuarioA(),
                amizade.getUsuarioB()
        );
    }
}
