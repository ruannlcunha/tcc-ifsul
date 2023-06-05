package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.AlterarUsuarioRequest;
import br.edu.ifsul.api.controller.response.UsuarioDetalhadoResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.mapper.UsuarioDetalhadoMapper;
import br.edu.ifsul.api.repository.UsuarioRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.BuscarUsuarioService;
import br.edu.ifsul.api.service.core.RealizarUploadService;
import br.edu.ifsul.api.service.core.ValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static br.edu.ifsul.api.mapper.UsuarioDetalhadoMapper.toResponse;

@Service
public class AlterarUsuarioService {

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidarUsuarioService validarUsuarioService;

    @Autowired
    private RealizarUploadService realizarUploadService;

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    public UsuarioDetalhadoResponse alterar(AlterarUsuarioRequest request) {
        Long usuarioId = autenticadoService.get().getId();
        validarUsuarioService.validar(usuarioId);

        MultipartFile arquivo = realizarUploadService.upload(request.getImagem());

        Usuario usuario = buscarUsuarioService.porId(usuarioId);
        usuario.setNome(request.getNome());
        usuario.setImagem(arquivo.getOriginalFilename());

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }
}
