package org.example.api.amizade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amizades")
@RequiredArgsConstructor
public class AmizadeRestController {

    private final AmizadeService service;

    @GetMapping
    public Page<AmizadeDTO> listarTodos(Pageable pageable) {
        return this.service.listarTodos(pageable);
    }
}
