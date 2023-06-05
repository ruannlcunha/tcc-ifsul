package br.edu.ifsul.api.domain;

import br.edu.ifsul.api.security.domain.Permissao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Usuario {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private boolean ativo;

    private String imagem;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permissao> permissoes = new ArrayList<>();

    @OneToMany(mappedBy = "seguidor")
    private List<Seguidor> perfisSeguidos = new ArrayList<>();

    @OneToMany(mappedBy = "seguido")
    private List<Seguidor> seguidores = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Curtida> curtidas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Postagem> postagens = new ArrayList<>();

    public void adicionarPermissao(Permissao permissao) {
        this.permissoes.add(permissao);
        permissao.setUsuario(this);

    }

    public void adicionarSeguidor(Seguidor seguidor) {
        this.seguidores.add(seguidor);
        seguidor.setSeguido(this);
    }

    public void adicionarPerfilSeguido(Seguidor perfilSeguido) {
        this.perfisSeguidos.add(perfilSeguido);
        perfilSeguido.setSeguidor(this);
    }

    public void adicionarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
        comentario.setUsuario(this);
    }

    public void adicionarCurtida(Curtida curtida) {
        this.curtidas.add(curtida);
        curtida.setUsuario(this);
    }

    public void adicionarPostagem(Postagem postagem) {
        this.postagens.add(postagem);
        postagem.setUsuario(this);
    }



}
