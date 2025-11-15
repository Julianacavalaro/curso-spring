package org.example.api.usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.api.exception.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

  //  private final List<Usuario> usuarioList = new ArrayList<>();
    private final UsuarioJpaRepository repository;

    @Autowired
    public UsuarioRestController(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/dummy")
    public Usuario dummyUsuario() {
        return new Usuario(UUID.randomUUID(),"nome", "email", LocalDate.now());
    }

    @GetMapping
    public List<Usuario> listarTodos(){
        return this.repository.findAll();
    }

    @PostMapping("/create-dummy")
    public Usuario criateDummy(){

        Usuario dummy = new Usuario(UUID.randomUUID(),"/create-dummy", "email", LocalDate.now());
        return this.criarUsuario(dummy);
    }

    @GetMapping("/{uuid}")
    public Usuario buscaUsuarioPorUuid(@PathVariable UUID uuid) {
        return this.repository.findByUuid(uuid)
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));
    }
    @PostMapping("/")
    public Usuario criarUsuario(@RequestBody @Valid Usuario usuario){
        return this.repository.save(usuario);
    }

    @PutMapping("/{uuid}")
    public Usuario atualizarUsuario(@PathVariable UUID uuid, @RequestBody @Valid Usuario usuarioNovo){
        Usuario usuario = this.buscaUsuarioPorUuid(uuid);
        usuarioNovo.setId(usuario.getId());
        return this.repository.save(usuarioNovo);
    }

    @PatchMapping("/{uuid}/alterar-nome")
    public Usuario atualizarNome(@PathVariable UUID uuid, @NotNull @RequestBody Usuario usuarioAlterado){
        Usuario usuario = this.buscaUsuarioPorUuid(uuid);
        usuario.setNome(usuarioAlterado.getNome());
        return this.repository.save(usuario);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void deletarUsuario(@PathVariable UUID uuid){
        this.repository.deleteByUuid(uuid);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String,String> handleValidationExceptions(
//            @NotNull MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//
//        return errors;
//    }

}
