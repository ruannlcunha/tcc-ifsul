package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    Page<Postagem> findAllByUsuarioId(Long usuarioId, Pageable pageable);

}
