CREATE TABLE usuario (
    usuario_id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    usuario_tipo VARCHAR(255) NOT NULL,
    usuario_status VARCHAR(255) NOT NULL,
    data_cadastro DATETIME NOT NULL,
    PRIMARY KEY (usuario_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE carteira (
    carteira_id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    saldo BIGINT NOT NULL,
    data_cadastro DATETIME NOT NULL,
    PRIMARY KEY (carteira_id),
    CONSTRAINT fk_carteira_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (usuario_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE produto (
    produto_id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL UNIQUE,
    descricao VARCHAR(255),
    imagem_url VARCHAR(255),
    preco DECIMAL(10, 2),
    data_cadastro DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,
    PRIMARY KEY (produto_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE carrinho (
    carrinho_id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    valor DECIMAL(10, 2),
    PRIMARY KEY (carrinho_id),
    CONSTRAINT fk_carrinho_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (usuario_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE pedido (
    pedido_id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    valor DECIMAL(10, 2),
    pedido_status VARCHAR(255) NOT NULL,
    desconto DECIMAL(10, 2),
    endereco VARCHAR(255),
    data_cadastro DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,
    PRIMARY KEY (pedido_id),
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (usuario_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

