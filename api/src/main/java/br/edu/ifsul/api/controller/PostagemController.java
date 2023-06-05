package br.edu.ifsul.api.controller;

import br.edu.ifsul.api.controller.request.ComentarioRequest;
import br.edu.ifsul.api.controller.request.PostagemRequest;
import br.edu.ifsul.api.controller.response.ComentarioResponse;
import br.edu.ifsul.api.controller.response.CurtidaResponse;
import br.edu.ifsul.api.controller.response.PostagemResponse;
import br.edu.ifsul.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private ListarComentariosService listarComentariosService;

    @Autowired
    private IncluirPostagemService incluirPostagemService;

    @Autowired
    private AlterarPostagemService alterarPostagemService;

    @Autowired
    private AvaliarPostagemService avaliarPostagemService;

    @Autowired
    private ComentarService comentarService;

    @PostMapping
    public PostagemResponse incluir(@Valid @RequestBody PostagemRequest request) {
        return incluirPostagemService.incluir(request);
    }

    @PutMapping
    public PostagemResponse alterar(@PathVariable Long postagemId, @Valid @RequestBody PostagemRequest request) {
        return alterarPostagemService.alterar(postagemId, request);
    }

    @GetMapping("/{postagemId}/comentarios")
    public Page<ComentarioResponse> listarComentarios(@PathVariable Long postagemId, Pageable pageable) {
        return listarComentariosService.listar(postagemId, pageable);
    }

    @PostMapping("/{postagemId}/avaliar")
    public CurtidaResponse avaliar(@PathVariable Long postagemId) {
        return avaliarPostagemService.avaliar(postagemId);
    }

    @PostMapping("/{postagemId}/comentar/")
    public ComentarioResponse comentar(@PathVariable Long postagemId,
                                       @Valid @RequestBody ComentarioRequest request) {
        return comentarService.comentar(postagemId, request);
    }

}
