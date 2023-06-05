package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.response.SolicitacaoResponse;
import br.edu.ifsul.api.domain.Seguidor;

public class SolicitacaoMapper {

    public static SolicitacaoResponse toResponse(Seguidor seguidor) {
        return SolicitacaoResponse.builder()
                .idAmizade(seguidor.getId())
                .build();
    }

}
