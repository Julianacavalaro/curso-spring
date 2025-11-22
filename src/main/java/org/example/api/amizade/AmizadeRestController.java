package org.example.api.amizade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/amizades")
@RequiredArgsConstructor
public class AmizadeRestController {

    private final AmizadeService service;

    @GetMapping
    public Page<AmizadeDTO> listarTodos(Pageable pageable) {
        return this.service.listarTodos(pageable);
    }

    @PostMapping("/{usuarioA/{usuarioB}")
    public AmizadeDTO criarAmizade(@PathVariable UUID usuarioA, @PathVariable UUID usuarioB){
        return this.service.criarAmizade(usuarioA, usuarioB);
    }
}
