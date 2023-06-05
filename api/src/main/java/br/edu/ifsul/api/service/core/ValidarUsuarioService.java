package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.FORBIDDEN;
@Service
public class ValidarUsuarioService {
    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    public void validar(Long usuarioId) {
        if(!usuarioId.equals(autenticadoService.get().getId())) {
            throw new ResponseStatusException(FORBIDDEN, "Você não é dono deste usuário.");
        }
    }
}
