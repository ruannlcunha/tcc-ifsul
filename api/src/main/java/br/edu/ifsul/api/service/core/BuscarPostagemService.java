package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Postagem;
import br.edu.ifsul.api.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class BuscarPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    public Postagem porId(Long id) {
        return postagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Postagem não existe"));
    }

    public Page<Postagem> todas(Long usuarioId, Pageable pageable) {
        return postagemRepository.findAllByUsuarioId(usuarioId, pageable);
    }

}
