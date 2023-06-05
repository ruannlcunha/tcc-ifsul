package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.PostagemRequest;
import br.edu.ifsul.api.controller.response.PostagemResponse;
import br.edu.ifsul.api.domain.Postagem;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.PostagemRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.NowService;
import br.edu.ifsul.api.service.core.RealizarUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static br.edu.ifsul.api.mapper.PostagemMapper.toEntity;
import static br.edu.ifsul.api.mapper.PostagemMapper.toResponse;

@Service
public class IncluirPostagemService {

    private final Integer CURTIDAS_INICIAIS = 0;
    private final boolean JA_CURTIU_INICIAL = false;

    @Autowired
    PostagemRepository postagemRepository;

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    @Autowired
    private RealizarUploadService realizarUploadService;

    @Autowired
    NowService nowService;

    public PostagemResponse incluir(PostagemRequest request) {
        Usuario usuario = autenticadoService.get();

        MultipartFile arquivo = realizarUploadService.upload(request.getImagem());

        Postagem postagem = toEntity(request);
        postagem.setAtivo(true);
        postagem.setImagem(arquivo.getOriginalFilename());
        postagem.setDataInicio(nowService.getDate());
        postagem.setUsuario(usuario);
        usuario.adicionarPostagem(postagem);

        postagemRepository.save(postagem);
        return toResponse(postagem, CURTIDAS_INICIAIS, JA_CURTIU_INICIAL);
    }
}
