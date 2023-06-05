package br.edu.ifsul.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class ComentarioRequest {

    @NotBlank
    @Size(max = 255)
    private String descricao;
}
