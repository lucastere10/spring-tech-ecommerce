CREATE TABLE produto_carrinho (
    id BIGINT NOT NULL AUTO_INCREMENT,
    produto_id BIGINT NOT NULL,
    carrinho_id BIGINT NOT NULL,
    quantidade BIGINT NOT NULL,
    desconto DECIMAL(10, 2),
    PRIMARY KEY (id),
    CONSTRAINT fk_produto_carrinho_produto FOREIGN KEY (produto_id) REFERENCES produto (id),
    CONSTRAINT fk_produto_carrinho_carrinho FOREIGN KEY (carrinho_id) REFERENCES carrinho (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE produto_pedido (
    id BIGINT NOT NULL AUTO_INCREMENT,
    produto_id BIGINT NOT NULL,
    pedido_id BIGINT NOT NULL,
    quantidade BIGINT NOT NULL,
    desconto DECIMAL(10, 2),
    PRIMARY KEY (id),
    CONSTRAINT fk_produto_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto (id),
    CONSTRAINT fk_produto_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE Avaliacao(
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    comentario TEXT,
    nota INT,
    data_cadastro DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_avaliacao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id),
    CONSTRAINT fk_avaliacao_produto FOREIGN KEY (produto_id) REFERENCES produto (id)
)