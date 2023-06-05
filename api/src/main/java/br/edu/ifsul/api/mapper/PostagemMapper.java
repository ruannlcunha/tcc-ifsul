package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.request.PostagemRequest;
import br.edu.ifsul.api.controller.response.PostagemResponse;
import br.edu.ifsul.api.domain.Postagem;

public class PostagemMapper {

    public static Postagem toEntity(PostagemRequest request) {
        return Postagem.builder()
                .descricao(request.getDescricao())
                .build();
    }

    public static PostagemResponse toResponse(Postagem postagem,
                                              Integer curtidas,
                                              boolean jaCurtiu) {
        return PostagemResponse.builder()
                .id(postagem.getId())
                .descricao(postagem.getDescricao())
                .imagem(postagem.getImagem())
                .curtidas(curtidas)
                .usuarioNome(postagem.getUsuario().getNome())
                .usuarioId(postagem.getUsuario().getId())
                .usuarioImagem(postagem.getUsuario().getImagem())
                .dataInicio(postagem.getDataInicio())
                .jaCurtiu(jaCurtiu)
                .build();
    }

}
