package org.example.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

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
    public Usuario criateDummy(){
        Usuario dummy = new Usuario(UUID.randomUUID(),"/create-dummy", "email", LocalDate.now());
        return this.criarUsuario(dummy);
    }

    @GetMapping("/{uuid}")
    public Usuario buscaUsuarioPorUuid(@PathVariable UUID uuid) {
        return usuarioList.stream()
                .filter(usuario -> usuario.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow();
    }
    @PostMapping("/")
    public Usuario criarUsuario(@RequestBody @Valid Usuario usuario){
        this.usuarioList.add(usuario);
        return usuario;
    }

    @PutMapping("/{uuid}")
    public Usuario atualizarUsuario(@PathVariable UUID uuid, @RequestBody @Valid Usuario usuarioNovo){
        Usuario usuario = this.buscaUsuarioPorUuid(uuid);
        this.usuarioList.set(this.usuarioList.indexOf(usuario), usuarioNovo);
        return usuarioNovo;
    }

    @PatchMapping("/{uuid}/alterar-nome")
    public Usuario atualizarNome(@PathVariable UUID uuid, @NotNull @RequestBody Usuario usuarioAlterado){
        Usuario usuario = this.buscaUsuarioPorUuid(uuid);
        usuario.setNome(usuarioAlterado.getNome());
        this.usuarioList.set(this.usuarioList.indexOf(usuario), usuarioAlterado);
        return usuarioAlterado;
    }

    @DeleteMapping("/{uuid}")
    public void deletarUsuario(@PathVariable UUID uuid){
        this.usuarioList.removeIf(usuario -> usuario.getUuid().equals(uuid));
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
