package br.edu.ifsul.api.controller;

import br.edu.ifsul.api.controller.request.AlterarUsuarioRequest;
import br.edu.ifsul.api.controller.request.ComentarioRequest;
import br.edu.ifsul.api.controller.request.PostagemRequest;
import br.edu.ifsul.api.controller.request.UsuarioRequest;
import br.edu.ifsul.api.controller.response.ComentarioResponse;
import br.edu.ifsul.api.controller.response.CurtidaResponse;
import br.edu.ifsul.api.controller.response.UsuarioDetalhadoResponse;
import br.edu.ifsul.api.controller.response.UsuarioResponse;
import br.edu.ifsul.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;

    @Autowired
    private DetalharUsuarioService detalharUsuarioService;

    @Autowired
    private PesquisarUsuariosService pesquisarUsuariosService;

    @Autowired
    private AlterarUsuarioService alterarUsuarioService;

    @Autowired
    private IncluirPostagemService incluirPostagemService;

    @PostMapping
    public UsuarioResponse incluir(@Valid @RequestBody UsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @PutMapping
    public UsuarioDetalhadoResponse alterar(@Valid @RequestBody AlterarUsuarioRequest request) {
        return alterarUsuarioService.alterar(request);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDetalhadoResponse detalhar(@PathVariable Long usuarioId) {
        return detalharUsuarioService.detalhar(usuarioId);
    }

    @GetMapping("/pesquisar")
    public Page<UsuarioResponse> pesquisar(@RequestParam("nome") String nome, Pageable pageable) {
        return pesquisarUsuariosService.pesquisar(nome, pageable);
    }

}
