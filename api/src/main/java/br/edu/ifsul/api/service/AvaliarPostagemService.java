package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.CurtidaResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.mapper.CurtidaMapper;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.BuscarCurtidaService;
import br.edu.ifsul.api.service.core.CurtirPostagemService;
import br.edu.ifsul.api.service.core.DescurtirPostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliarPostagemService {

    @Autowired
    CurtirPostagemService curtirPostagemService;

    @Autowired
    DescurtirPostagemService descurtirPostagemService;

    @Autowired
    BuscarCurtidaService buscarCurtidaService;

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    public CurtidaResponse avaliar(Long postagemId) {
        Usuario usuario = autenticadoService.get();

        if(buscarCurtidaService.jaCurtiu(usuario.getId(), postagemId)) {
            return descurtirPostagemService.descurtir(usuario.getId(), postagemId);
        }
        else {
            return curtirPostagemService.curtir(usuario.getId(), postagemId);
        }
    }
}
