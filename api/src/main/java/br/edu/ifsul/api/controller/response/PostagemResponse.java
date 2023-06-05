package br.edu.ifsul.api.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostagemResponse {

    private Long id;

    private String descricao;

    private String imagem;

    private Integer curtidas;

    private String usuarioNome;

    private Long usuarioId;

    private String usuarioImagem;

    private LocalDateTime dataInicio;

    private boolean jaCurtiu;

}
