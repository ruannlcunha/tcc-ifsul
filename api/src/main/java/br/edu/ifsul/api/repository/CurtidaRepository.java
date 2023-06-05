package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.Curtida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {
    Optional<Curtida> findByUsuarioIdAndPostagemId(Long id, Long id1);

    boolean existsByUsuarioIdAndPostagemIdAndAtivoIsTrue(Long usuarioId, Long postagemId);

    int countByPostagemIdAndAtivoIsTrue(Long postagemId);
}
