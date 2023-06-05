package br.edu.ifsul.api.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Postagem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String descricao;

    private String imagem;

    private boolean ativo;

    private LocalDateTime dataInicio;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "postagem")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "postagem")
    private List<Curtida> curtidas = new ArrayList<>();

    public void adicionarCurtida(Curtida curtida) {
        this.curtidas.add(curtida);
        curtida.setPostagem(this);
    }
    public void adicionarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
        comentario.setPostagem(this);
    }
}
