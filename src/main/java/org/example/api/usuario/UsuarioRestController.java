package org.example.api.usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.api.amizade.Amizade;
import org.example.api.amizade.AmizadeDTO;
import org.example.api.exception.DuplicadoException;
import org.example.api.exception.NaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ModelMapper modelMapper;
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioRestController(UsuarioJpaRepository repository, ModelMapper modelMapper, UsuarioService usuarioService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.usuarioService = usuarioService;
    }

    public UsuarioDTO convertToDto(Usuario usuario ){
        return this.modelMapper.map(usuario, UsuarioDTO.class);
    }

    @GetMapping("/dummy")
    public Usuario dummyUsuario() {
        return new Usuario(UUID.randomUUID(),"nome", "email", LocalDate.now());
    }

    @GetMapping
    public Page<Usuario> listarTodos(Pageable peageable){
        return this.repository.findAll(peageable);
    }

    @PostMapping("/create-dummy")
    public Usuario criateDummy(){

        Usuario dummy = new Usuario(UUID.randomUUID(),"/create-dummy", "email", LocalDate.now());
        return this.criarUsuario(dummy);
    }

    @GetMapping("/{uuid}")
    public UsuarioDTO buscarPorUuidDTO(@PathVariable UUID uuid) {
        Usuario usuario = this.usuarioService.buscarPorUuid(uuid);
        return convertToDto(usuario);
                //buscarPorUuid(uuid);
      //  return this.modelMapper.map(usuario, UsuarioDTO.class);
    }
    public Usuario buscarPorUuid(UUID uuid) {

        List<Usuario> usuarios = this.repository.findByUuidIn(List.of(uuid));

        if (usuarios.isEmpty()) {
            throw new NaoEncontradoException("Usuário não encontrado para UUID: " + uuid);
        }

        if (usuarios.size() > 1) {
            throw new DuplicadoException("Há mais de um usuário com o UUID: " + uuid);
        }

        return usuarios.get(0);
    }


    @PostMapping("/")
    public Usuario criarUsuario(@RequestBody @Valid Usuario usuario){
        return this.repository.save(usuario);
    }

    @PutMapping("/{uuid}")
    public Usuario atualizarUsuario(@PathVariable UUID uuid, @RequestBody @Valid Usuario usuarioNovo){
        Usuario usuario = this.buscarPorUuid(uuid);
        usuarioNovo.setId(usuario.getId());
        return this.repository.save(usuarioNovo);
    }

    @Transactional
    @PatchMapping("/{uuid}/alterar-nome")
    public Usuario alterarNome(@PathVariable UUID uuid, @NotNull @RequestBody Usuario usuarioAlterado){
//        Usuario usuario = this.buscaUsuarioPorUuid(uuid);
//        usuario.setNome(usuarioAlterado.getNome());
        this.repository.updateNome(uuid, usuarioAlterado.getNome());
        return this.buscarPorUuid(uuid);
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
