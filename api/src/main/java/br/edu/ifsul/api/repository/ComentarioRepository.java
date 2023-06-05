package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    Page<Comentario> findByPostagemId(Long postagemId, Pageable pageable);
}
