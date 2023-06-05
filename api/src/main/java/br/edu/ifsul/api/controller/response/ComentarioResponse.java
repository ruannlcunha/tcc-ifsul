package br.edu.ifsul.api.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ComentarioResponse {

    private String descricao;

    private String nomeUsuario;

    private String imagemUsuario;

    private LocalDateTime dataInicio;

}
