✨ 🚀 Codex Engine 🚀 ✨

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-12-blue)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](#)

```
   ____          _             _____                       
  / ___|___   __| | _____  __ | ____|_ __   __ _(_)_ __   ___ 
 | |   / _ \ / _` |/ _ \ \/ / |  _| | '_ \ / _` | | '_ \ / _ \
 | |__| (_) | (_| |  __/ /\ \ | |___| | | | (_| | | | | |  __/
  \____\___/ \__,_|\__/_/  \_\|_____|_| |_|\__, |_|_| |_|\___|
                                               |___/             
```


## Índice

- [Visão geral](#visão-geral)
- [Tecnologias](#tecnologias)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Como executar (rápido)](#como-executar-rápido)
- [Exemplos rápidos (curl)](#exemplos-rápidos-curl)
- [Endpoints da API](#endpoints-da-api)
- [Variáveis de ambiente / Configuração](#variáveis-de-ambiente--configuração)
- [Swagger / Documentação](#swagger--documentação)
- [Observações importantes](#observações-importantes)

---

## Tecnologias

- **Java 21** — Linguagem de programação principal
- **Spring Boot 4** — Framework para aplicações Web (inclui Web, Data JPA, Security, Validation)
- **SpringDoc OpenAPI** — Documentação automática da API (Swagger)
- **PostgreSQL** — Banco de dados relacional
- **Flyway** — Versionamento e migração de banco de dados
- **JWT** — Autenticação via tokens (com.auth0/java-jwt, jjwt)
- **Lombok** — Redução de boilerplate code
- **Maven** — Gerenciamento de dependências e build


## Estrutura (resumo)

```
com.back.codex/
├── 📁 config/          # Configurações de segurança, JWT e OpenAPI
├── 📁 controller/      # Controladores REST
├── 📁 service/         # Lógica de negócio 
├── 📁 repository/      # Acesso a dados
├── 📁 model/           # Entidades JPA
├── 📁 dto/             # Requests e Responses
├── 📁 mapper/          # Conversores
├── 📁 exception/       # Exceções customizadas
└── 📁 enums/           # Enums
```
---

## Como executar (rápido)

1) Usando Maven:
   mvn spring-boot:run

2) Gerar JAR:
   mvn clean package
   java -jar target/codex-0.0.1-SNAPSHOT.jar

Porta padrão: 8080

---

## Exemplo rápido (curl)

1) Registrar usuário

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"nome":"Fulano","usuario":"fulano","senha":"s3nh4","role":"ROLE_USER"}'
```

Resposta: JSON com dados do usuário criado (sem a senha).

2) Login e obter token

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"usuario":"fulano","senha":"s3nh4"}'
```

Resposta: { "token": "<JWT>" }

3) Usar token para obter lista de usuários

```bash
curl -X GET http://localhost:8080/api/usuarios \
  -H "Authorization: Bearer <JWT>"
```

## Variáveis de ambiente / application.properties

As propriedades em src/main/resources/application.properties usam variáveis de ambiente com defaults:

| Propriedade                  | Descrição                                      | Padrão                                   |
|------------------------------|------------------------------------------------|------------------------------------------|
| spring.datasource.username    | Usuário do banco de dados                      | ${USUARIO_BANCO_CODEX:admin}           |
| spring.datasource.password    | Senha do banco de dados                       | ${SENHA_BANCO_CODEX:admin}             |
| spring.datasource.url         | URL do banco de dados                          | ${URL_BANCO_CODEX:jdbc:postgresql://localhost:5436/postgres} |
| app.jwt.secret                | Segredo para geração/validação de tokens JWT | ${JWT_SECRET:valor_padrao}              |

---

## Swagger / OpenAPI

- UI: http://localhost:8080/swagger-ui.html (ou /swagger-ui/index.html)
- Spec: /v3/api-docs