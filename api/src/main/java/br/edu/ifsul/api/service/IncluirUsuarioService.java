package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.UsuarioRequest;
import br.edu.ifsul.api.controller.response.UsuarioResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.UsuarioRepository;
import br.edu.ifsul.api.service.core.PermissaoPadraoService;
import br.edu.ifsul.api.service.core.RealizarUploadService;
import br.edu.ifsul.api.service.core.SenhaCriptografadaService;
import br.edu.ifsul.api.service.core.ValidaEmailUnicoService;
import br.edu.ifsul.api.util.Base64ToMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static br.edu.ifsul.api.mapper.UsuarioMapper.toEntity;
import static br.edu.ifsul.api.mapper.UsuarioMapper.toResponse;

@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SenhaCriptografadaService senhaCriptografadaService;

    @Autowired
    private PermissaoPadraoService permissaoPadraoService;

    @Autowired
    private ValidaEmailUnicoService validaEmailUnicoService;

    @Autowired
    private RealizarUploadService realizarUploadService;

    public UsuarioResponse incluir(UsuarioRequest request) {
        validaEmailUnicoService.validar(request.getEmail());

        MultipartFile arquivo = realizarUploadService.upload(request.getImagem());

        Usuario usuario = toEntity(request);
        usuario.setImagem(arquivo.getOriginalFilename());
        usuario.setSenha(senhaCriptografadaService.get(request.getSenha()));
        usuario.adicionarPermissao(permissaoPadraoService.get());
        usuario.setAtivo(true);

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }
}
