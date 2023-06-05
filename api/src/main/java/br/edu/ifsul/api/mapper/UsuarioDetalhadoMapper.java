package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.response.UsuarioDetalhadoResponse;
import br.edu.ifsul.api.domain.Usuario;

public class UsuarioDetalhadoMapper {

    public static UsuarioDetalhadoResponse toResponse(Usuario usuario) {
        return UsuarioDetalhadoResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .imagem(usuario.getImagem())
                .build();
    }

}