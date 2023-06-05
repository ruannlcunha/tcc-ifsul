package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.UsuarioDetalhadoResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.service.core.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.UsuarioDetalhadoMapper.toResponse;

@Service
public class DetalharUsuarioService {

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public UsuarioDetalhadoResponse detalhar(Long usuarioId) {

        Usuario usuario = buscarUsuarioService.porId(usuarioId);

        return toResponse(usuario);

    }
}
