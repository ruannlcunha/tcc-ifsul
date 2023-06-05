package br.edu.ifsul.api.service;
import br.edu.ifsul.api.controller.request.PostagemRequest;
import br.edu.ifsul.api.controller.response.PostagemResponse;
import br.edu.ifsul.api.domain.Postagem;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.CurtidaRepository;
import br.edu.ifsul.api.repository.PostagemRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.BuscarCurtidaService;
import br.edu.ifsul.api.service.core.BuscarPostagemService;
import br.edu.ifsul.api.service.core.RealizarUploadService;
import br.edu.ifsul.api.service.core.ValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static br.edu.ifsul.api.mapper.PostagemMapper.toResponse;

@Service
public class AlterarPostagemService {

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    @Autowired
    private BuscarPostagemService buscarPostagemService;

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private ValidarUsuarioService validarUsuarioService;

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private BuscarCurtidaService buscarCurtidaService;

    @Autowired
    private RealizarUploadService realizarUploadService;

    public PostagemResponse alterar(Long postagemId, PostagemRequest request) {
        //validar proprietairo
        Long usuarioId = autenticadoService.get().getId();
        MultipartFile arquivo = realizarUploadService.upload(request.getImagem());

        Postagem postagem = buscarPostagemService.porId(postagemId);
        postagem.setDescricao(request.getDescricao());
        postagem.setImagem(arquivo.getOriginalFilename());

        Integer curtidas = buscarCurtidaService.contarCurtidas(postagemId);
        boolean jaCurtiu = buscarCurtidaService.jaCurtiu(usuarioId, postagemId);

        postagemRepository.save(postagem);

        return toResponse(postagem, curtidas, jaCurtiu);
    }
}
