package org.example.api.amizade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmizadeService {

    private final AmizadeRepository repository;

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

    private AmizadeDTO criarAmizade(UUID usuarioA, UUID usuarioB) {
        AmizadeDTO dto = new AmizadeDTO();

        return dto;
    }
}
