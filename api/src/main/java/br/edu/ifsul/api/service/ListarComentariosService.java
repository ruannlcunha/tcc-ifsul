package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.ComentarioResponse;
import br.edu.ifsul.api.mapper.ComentarioMapper;
import br.edu.ifsul.api.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarComentariosService {

    @Autowired
    private ComentarioRepository comentarioRepository;


    public Page<ComentarioResponse> listar(Long postagemId, Pageable pageable) {
        return comentarioRepository.findByPostagemId(postagemId, pageable)
                .map(ComentarioMapper::toResponse);
    }
}
