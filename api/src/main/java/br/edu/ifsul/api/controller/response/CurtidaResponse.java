package br.edu.ifsul.api.controller.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurtidaResponse {

    private Long curtidaId;

    private Long usuarioId;

    private Long postagemId;

    private boolean ativo;
}
