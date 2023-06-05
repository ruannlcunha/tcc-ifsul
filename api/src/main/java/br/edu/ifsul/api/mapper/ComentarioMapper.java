package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.request.ComentarioRequest;
import br.edu.ifsul.api.controller.response.ComentarioResponse;
import br.edu.ifsul.api.domain.Comentario;

public class ComentarioMapper {

    public static Comentario toEntity(ComentarioRequest request) {
        Comentario comentario = new Comentario();
        comentario.setDescricao(request.getDescricao());

        return comentario;
    }

    public static ComentarioResponse toResponse(Comentario comentario) {
        return ComentarioResponse.builder()
                .descricao(comentario.getDescricao())
                .nomeUsuario(comentario.getUsuario().getNome())
                .imagemUsuario(comentario.getUsuario().getImagem())
                .dataInicio(comentario.getDataInicio())
                .build();
    }

}
