package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.controller.response.CurtidaResponse;
import br.edu.ifsul.api.domain.Curtida;
import br.edu.ifsul.api.domain.Postagem;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.mapper.CurtidaMapper;
import br.edu.ifsul.api.repository.CurtidaRepository;
import br.edu.ifsul.api.service.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.edu.ifsul.api.mapper.CurtidaMapper.toResponse;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CurtirPostagemService {

    @Autowired
    CurtidaRepository curtidaRepository;

    @Autowired
    BuscarUsuarioService buscarUsuarioService;

    @Autowired
    BuscarPostagemService buscarPostagemService;

    @Autowired
    BuscarCurtidaService buscarCurtidaService;

    @Autowired
    NowService nowService;

    public CurtidaResponse curtir(Long usuarioId, Long postagemId) {
        Usuario usuario = buscarUsuarioService.porId(usuarioId);
        Postagem postagem = buscarPostagemService.porId(postagemId);

        Curtida curtida = buscarCurtidaService.porUsuario(usuario.getId(), postagem.getId());

        curtida.setAtivo(true);
        if(curtida.getDataInicio() == null) {
            curtida.setDataInicio(nowService.getDate());
            usuario.adicionarCurtida(curtida);
            postagem.adicionarCurtida(curtida);
        }
        curtidaRepository.save(curtida);

        return toResponse(curtida);
    }
}
