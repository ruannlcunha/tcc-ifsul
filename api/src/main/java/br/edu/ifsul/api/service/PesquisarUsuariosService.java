package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.UsuarioResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.mapper.UsuarioMapper;
import br.edu.ifsul.api.repository.UsuarioRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PesquisarUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    public Page<UsuarioResponse> pesquisar(String nome, Pageable pageable) {
        Long usuarioId = autenticadoService.get().getId();

        return usuarioRepository
                .findByNomeContainingIgnoreCaseAndIdNot(nome,usuarioId, pageable)
                .map(UsuarioMapper::toResponse);
    }

}
