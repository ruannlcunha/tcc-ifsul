package br.edu.ifsul.api.controller.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UsuarioDetalhadoResponse {

    private Long id;

    private String nome;

    private String email;

    private String imagem;


}
