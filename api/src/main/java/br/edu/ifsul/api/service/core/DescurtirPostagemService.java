package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.controller.response.CurtidaResponse;
import br.edu.ifsul.api.domain.Curtida;
import br.edu.ifsul.api.domain.Postagem;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.CurtidaRepository;
import br.edu.ifsul.api.service.core.BuscarCurtidaService;
import br.edu.ifsul.api.service.core.BuscarPostagemService;
import br.edu.ifsul.api.service.core.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.CurtidaMapper.toResponse;

@Service
public class DescurtirPostagemService {

    @Autowired
    CurtidaRepository curtidaRepository;

    @Autowired
    BuscarUsuarioService buscarUsuarioService;

    @Autowired
    BuscarPostagemService buscarPostagemService;

    @Autowired
    BuscarCurtidaService buscarCurtidaService;


    public CurtidaResponse descurtir(Long usuarioId, Long postagemId) {
        Usuario usuario = buscarUsuarioService.porId(usuarioId);
        Postagem postagem = buscarPostagemService.porId(postagemId);

        Curtida curtida = buscarCurtidaService.porUsuario(usuario.getId(), postagem.getId());

        curtida.setAtivo(false);

        curtidaRepository.save(curtida);

        return toResponse(curtida);
    }
}
