CREATE TABLE produto_carrinho (
    produto_carrinho_id BIGINT NOT NULL AUTO_INCREMENT,
    produto_id BIGINT NOT NULL,
    carrinho_id BIGINT NOT NULL,
    quantidade BIGINT NOT NULL,
    desconto DECIMAL(10, 2),
    PRIMARY KEY (produto_carrinho_id),
    CONSTRAINT fk_produto_carrinho_produto FOREIGN KEY (produto_id) REFERENCES produto (produto_id),
    CONSTRAINT fk_produto_carrinho_carrinho FOREIGN KEY (carrinho_id) REFERENCES carrinho (carrinho_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE produto_pedido (
    produto_pedido_id BIGINT NOT NULL AUTO_INCREMENT,
    produto_id BIGINT NOT NULL,
    pedido_id BIGINT NOT NULL,
    quantidade BIGINT NOT NULL,
    desconto DECIMAL(10, 2),
    PRIMARY KEY (produto_pedido_id),
    CONSTRAINT fk_produto_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto (produto_id),
    CONSTRAINT fk_produto_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (pedido_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE Avaliacao(
    avaliacao_id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    comentario TEXT,
    nota INT,
    data_cadastro DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,
    PRIMARY KEY (avaliacao_id),
    CONSTRAINT fk_avaliacao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (usuario_id),
    CONSTRAINT fk_avaliacao_produto FOREIGN KEY (produto_id) REFERENCES produto (produto_id)
)