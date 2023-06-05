package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Curtida;
import br.edu.ifsul.api.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BuscarCurtidaService {

    @Autowired
    private CurtidaRepository curtidaRepository;

    public Curtida porUsuario(Long usuarioId, Long postagemId) {
        return curtidaRepository.findByUsuarioIdAndPostagemId(usuarioId, postagemId)
                .orElse(new Curtida());
    }

    public boolean jaCurtiu(Long usuarioId, Long postagemId) {
        return curtidaRepository.existsByUsuarioIdAndPostagemIdAndAtivoIsTrue(usuarioId, postagemId);
    }

    public Integer contarCurtidas(Long postagemId) {
        return curtidaRepository.countByPostagemIdAndAtivoIsTrue(postagemId);
    }
}
