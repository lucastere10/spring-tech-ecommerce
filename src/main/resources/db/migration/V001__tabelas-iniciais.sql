CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    usuario_tipo VARCHAR(255) NOT NULL,
    usuario_status VARCHAR(255) NOT NULL,
    data_cadastro DATETIME NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE carteira (
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    saldo BIGINT NOT NULL,
    data_cadastro DATETIME NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_carteira_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE produto (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    imagem_url VARCHAR(255),
    preco DECIMAL(10, 2),
    data_cadastro DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE carrinho (
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    valor DECIMAL(10, 2),
    PRIMARY KEY (id),
    CONSTRAINT fk_carrinho_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE Pedido (
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    valor DECIMAL(10, 2),
    status_pedido VARCHAR(255) NOT NULL,
    desconto DECIMAL(10, 2),
    endereco VARCHAR(255),
    data_cadastro DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

