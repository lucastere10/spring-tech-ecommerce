# Spring Tech Ecommerce

Este projeto foi criado com o objetivo de praticar a criação de APIs com Spring Boot.

## Tecnologias Utilizadas

- Spring Boot
- JWT
- OpenAPI
- Flyway
- Docker e Docker-compose
- MySQL
- Model Mapper

## Funcionalidades

### CRUD Completo

O projeto implementa um CRUD completo para avaliações, produtos, usuários e pedidos.

### Autenticação

Possui um sistema específico de autenticação para login, registro e retornar informações do usuário logado.

### Carrinho de Compras

Cada usuário possui um carrinho que guarda os produtos antes da compra.

### Carteira

Cada usuário possui uma carteira com endpoints para saque e depósito.

### Permissões de Usuário

Os usuários possuem permissões distintas para CLIENTE e ADMIN. Existem endpoints para mudar status de usuários, como ATIVO, INATIVO, BANIDO e BLOQUEADO.

## Como executar o projeto

1. Clone o repositório
2. Execute o comando `docker-compose up`
3. Acesse a aplicação em `localhost:8080`

## Documentação da API

A documentação da API pode ser acessada em `localhost:8080/swagger-ui.html`

## Contribuição

Contribuições são sempre bem-vindas. Sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a licença MIT.
