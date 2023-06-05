package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.response.CurtidaResponse;
import br.edu.ifsul.api.domain.Curtida;

public class CurtidaMapper {

    public static CurtidaResponse toResponse(Curtida curtida) {
        return CurtidaResponse.builder()
                .curtidaId(curtida.getId())
                .usuarioId(curtida.getUsuario().getId())
                .postagemId(curtida.getPostagem().getId())
                .ativo(curtida.isAtivo())
                .build();
    }

}
