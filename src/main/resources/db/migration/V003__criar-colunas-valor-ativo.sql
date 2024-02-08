alter table produto_carrinho add preco_unitario DECIMAL(10,2) not null;
alter table produto_carrinho add preco_total DECIMAL(10,2) not null;
alter table produto_pedido add preco_unitario DECIMAL(10,2) not null;
alter table produto_pedido add preco_total DECIMAL(10,2) not null;
