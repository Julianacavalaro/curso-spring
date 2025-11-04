package org.example.api;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    private final List<Usuario> usuarioList = new ArrayList<>();

    @GetMapping("/dummy")
    public Usuario dummyUsuario() {
        return new Usuario(UUID.randomUUID(),"nome", "email", LocalDate.now());
    }

    @GetMapping
    public List<Usuario> ListarTodos(){
        return this.usuarioList;
    }
    @PostMapping("/create-dummy")
    public Usuario crieateDummy(@RequestBody Usuario usuario){
        Usuario dummy = new Usuario(UUID.randomUUID(),"/create-dummy", "email", LocalDate.now());
        return this.crieateDummy(dummy);
    }

    @GetMapping("/{uuid}")
    public Usuario buscaUsuarioPorUuid(@PathVariable UUID uuid) {
        return usuarioList.stream()
                .filter(usuario -> usuario.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow();
    }
    @PostMapping("/")
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        this.usuarioList.add(usuario);
        return usuario;
    }

//    @PutMapping
//    public
}
