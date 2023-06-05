package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.ComentarioRequest;
import br.edu.ifsul.api.controller.response.ComentarioResponse;
import br.edu.ifsul.api.domain.Comentario;
import br.edu.ifsul.api.domain.Postagem;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.mapper.ComentarioMapper;
import br.edu.ifsul.api.repository.ComentarioRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.BuscarPostagemService;
import br.edu.ifsul.api.service.core.BuscarUsuarioService;
import br.edu.ifsul.api.service.core.NowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private BuscarPostagemService buscarPostagemService;

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    @Autowired
    private NowService nowService;

    public ComentarioResponse comentar(Long postagemId, ComentarioRequest request) {
        Usuario usuario = autenticadoService.get();
        Postagem postagem = buscarPostagemService.porId(postagemId);

        Comentario comentario = ComentarioMapper.toEntity(request);
        comentario.setAtivo(true);
        comentario.setDataInicio(nowService.getDate());

        usuario.adicionarComentario(comentario);
        postagem.adicionarComentario(comentario);

        comentarioRepository.save(comentario);

        return ComentarioMapper.toResponse(comentario);
    }
}
