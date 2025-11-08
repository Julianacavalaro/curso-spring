package org.example.api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {

}
