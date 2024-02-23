set foreign_key_checks = 0;

delete from avaliacao;
delete from carrinho;
delete from carteira;
delete from pedido;
delete from produto;
delete from produto_pedido;
delete from produto_carrinho;
delete from usuario;

set foreign_key_checks = 1;

alter table avaliacao auto_increment = 1;
alter table carrinho auto_increment = 1;
alter table carteira auto_increment = 1;
alter table pedido auto_increment = 1;
alter table produto auto_increment = 1;
alter table produto_pedido auto_increment = 1;
alter table produto_carrinho auto_increment = 1;
alter table usuario auto_increment = 1;

insert into produto (produto_id, nome, descricao, imagem_url, preco, data_cadastro, data_atualizacao) values
(1, 'Monitor', 'Descricao do Monitor', 'http://monitor.springtech.com.br', 750.00, utc_timestamp, utc_timestamp),
(2, 'Teclado', 'Descricao do Teclado', 'http://monitor.springtech.com.br', 30.00, utc_timestamp, utc_timestamp),
(3, 'Mouse', 'Descricao do Mouse', 'http://monitor.springtech.com.br', 22.00, utc_timestamp, utc_timestamp),
(4, 'Fone de ouvido', 'Descricao do Fone de ouvido', 'http://monitor.springtech.com.br', 55.00, utc_timestamp, utc_timestamp),
(5, 'Mousepad', 'Descricao do Mousepad', 'http://monitor.springtech.com.br', 15.00, utc_timestamp, utc_timestamp),
(6, 'Gabinete', 'Descricao do Gabinete', 'http://monitor.springtech.com.br', 180.00, utc_timestamp, utc_timestamp),
(7, 'CPU', 'Descricao do CPU', 'http://monitor.springtech.com.br', 999.00, utc_timestamp, utc_timestamp),
(8, 'GPU', 'Descricao do GPU', 'http://monitor.springtech.com.br', 1800.00, utc_timestamp, utc_timestamp);

insert into usuario (usuario_id, nome, email, senha, usuario_tipo, usuario_status, data_cadastro, data_atualizacao) values
(1, 'Lucas Caldas', 'lucas.caldas@springtech.com.br', 'admin', 'ADMIN', 'ATIVO', utc_timestamp, utc_timestamp),
(2, 'Tiquinho Soares', 'tiquinho.soares@springtech.com.br', 'admin', 'ADMIN', 'ATIVO', utc_timestamp, utc_timestamp),
(3, 'Mauro Cezar', 'mauro.cezar@springtech.com.br', 'admin', 'CLIENTE', 'BANIDO', utc_timestamp, utc_timestamp),
(4, 'Barbara Ferreira', 'barbara.ferreira@springtech.com.br', 'admin', 'CLIENTE', 'INATIVO', utc_timestamp, utc_timestamp),
(5, 'Luiza Fernandes', 'luiza.fernandes@springtech.com.br', 'admin', 'CLIENTE', 'BLOQUEADO', utc_timestamp, utc_timestamp);

insert into carteira (carteira_id, usuario_id, saldo, data_cadastro, data_atualizacao) values
(1, 1, 810.00, utc_timestamp, utc_timestamp),
(2, 2, 75.00, utc_timestamp, utc_timestamp),
(3, 3, 2799.00, utc_timestamp, utc_timestamp),
(4, 4, 110.00, utc_timestamp, utc_timestamp),
(5, 5, 180.00, utc_timestamp, utc_timestamp);

insert into carrinho (carrinho_id, usuario_id, valor) values
(1, 1, 120.00),
(2, 2, 220.00),
(3, 3, 450.00),
(4, 4, 332.00),
(5, 5, 970.00);

insert into pedido (pedido_id, usuario_id, valor, pedido_status, desconto, endereco, data_cadastro, data_atualizacao) values
(1, 1, 8810.00, "ENTREGUE", 0, "Rua das Flores, 123", utc_timestamp, utc_timestamp),
(2, 3, 75.00, "ENTREGUE", 0, "Rua das Marmotas, 456", utc_timestamp, utc_timestamp),
(3, 5, 2799.00, "ENTREGUE", 0, "Rua das Frutas, 789", utc_timestamp, utc_timestamp),
(4, 5, 110.00, "ENTREGUE", 0, "Rua das Alegrias, 645", utc_timestamp, utc_timestamp),
(5, 1, 180.00, "ENTREGUE", 0, "Rua das Oliveiras, 321", utc_timestamp, utc_timestamp);

insert into avaliacao (avaliacao_id, usuario_id, produto_id, comentario, nota, data_cadastro, data_atualizacao) values
(1, 1, 1, "Produto Nota 10", 10, utc_timestamp, utc_timestamp),
(2, 3, 3, "Produto Nota 0", 0, utc_timestamp, utc_timestamp),
(3, 3, 2, "Razoável", 6, utc_timestamp, utc_timestamp),
(4, 4, 4, "Sofrivel", 1, utc_timestamp, utc_timestamp),
(5, 5, 5, "Adorei", 9, utc_timestamp, utc_timestamp),
(6, 5, 7, "Carburador de Santana", 10, utc_timestamp, utc_timestamp);

insert into produto_carrinho (produto_carrinho_id, produto_id, carrinho_id, quantidade, desconto, preco_unitario, preco_total) values
(1, 1, 1, 1, 0, 750, 750),
(2, 2, 1, 2, 0, 30, 60),
(3, 5, 2, 5, 0, 15, 75),
(4, 7, 3, 1, 0, 999, 999),
(5, 8, 3, 1, 0, 1800, 1800),
(6, 4, 4, 2, 0, 55, 110),
(7, 6, 5, 1, 0, 180, 180);

insert into produto_pedido (produto_pedido_id, produto_id, pedido_id, quantidade, desconto, preco_unitario, preco_total) values
(1, 1, 1, 1, 0, 750, 750),
(2, 2, 1, 2, 0, 30, 60),
(3, 5, 2, 5, 0, 15, 75),
(4, 7, 3, 1, 0, 999, 999),
(5, 8, 3, 1, 0, 1800, 1800),
(6, 4, 4, 2, 0, 55, 110),
(7, 6, 5, 1, 0, 180, 180);

