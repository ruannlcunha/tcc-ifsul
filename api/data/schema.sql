DROP TABLE IF EXISTS permissao CASCADE;
DROP TABLE IF EXISTS comentario CASCADE;
DROP TABLE IF EXISTS curtida CASCADE;
DROP TABLE IF EXISTS seguidor CASCADE;
DROP TABLE IF EXISTS favorito CASCADE;
DROP TABLE IF EXISTS postagem CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;

CREATE TABLE usuario (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	senha VARCHAR(128) NOT NULL,
	ativo BOOLEAN NOT NULL,
	imagem VARCHAR(8000)
);

CREATE TABLE permissao (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	funcao VARCHAR(100) NOT NULL,
	usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_permissao_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
    CONSTRAINT uk_permissao UNIQUE(funcao,usuario_id)
);

CREATE TABLE seguidor (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	usuario_seguidor BIGINT NOT NULL,
	usuario_seguido BIGINT NOT NULL,
	ativo boolean NOT NULL,
    CONSTRAINT uk_seguidor UNIQUE (usuario_seguidor, usuario_seguido),
    CONSTRAINT fk_usuario_seguidor FOREIGN KEY (usuario_seguidor) REFERENCES usuario(id),
	CONSTRAINT fk_usuario_seguido FOREIGN KEY (usuario_seguido) REFERENCES usuario(id)
);

CREATE TABLE postagem (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	descricao VARCHAR(255),
	imagem VARCHAR(512),
	data_inicio TIMESTAMP NOT NULL,
	ativo BOOLEAN NOT NULL,
	usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_postagem_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE comentario (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	descricao VARCHAR(255) NOT NULL,
	data_inicio TIMESTAMP NOT NULL,
	ativo BOOLEAN NOT NULL,
	postagem_id BIGINT NOT NULL,
	usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_comentario_postagem FOREIGN KEY (postagem_id) REFERENCES postagem(id),
    CONSTRAINT fk_comentario_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE curtida (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	ativo BOOLEAN NOT NULL,
	data_inicio TIMESTAMP NOT NULL,
	usuario_id BIGINT NOT NULL,
	postagem_id BIGINT NOT NULL,
    CONSTRAINT uk_curtida_usuario_postagem UNIQUE(usuario_id, postagem_id),
    CONSTRAINT fk_curtida_postagem FOREIGN KEY (postagem_id) REFERENCES postagem(id),
    CONSTRAINT fk_curtida_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE favorito (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	ativo BOOLEAN NOT NULL,
	usuario_id BIGINT NOT NULL,
	postagem_id BIGINT NOT NULL,
    CONSTRAINT uk_favorito_usuario_postagem UNIQUE(usuario_id, postagem_id),
    CONSTRAINT fk_favorito_postagem FOREIGN KEY (postagem_id) REFERENCES postagem(id),
    CONSTRAINT fk_favorito_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
