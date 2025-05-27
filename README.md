# TrackMatch

> **Uma API REST enxuta para conectar músicos a vagas de bandas.**

---

## ✨ O que é isto?

**TrackMatch** é um MVP de recrutamento musical que demonstra boas práticas em **Java 21 + Spring Boot 3.5.0**.
O projeto implementa CRUDs de músicos e gigs, validação robusta, regra de matching por instrumento/cidade, testes unitários e de integração, documentação OpenAPI e cobertura de código via JaCoCo – tudo containerizado e pronto para CI/CD.

---

## 🔧 Pré‑requisitos

| Ferramenta                               | Versão mínima       | Por quê                                                  |
| ---------------------------------------- | ------------------- | -------------------------------------------------------- |
| **JDK**                                  | 21                  | Nível de linguagem exigido pelo Spring Boot 3.x          |
| **Maven Wrapper**                        | Embutido (`./mvnw`) | Compila e roda o projeto sem precisar de Maven instalado |
| **Git**                                  | Latest              | Clonar e versionar o repositório                         |
| *(Opcional)* **Docker + Docker Compose** | 24‑x                | Construir e executar a imagem (bônus)                    |

> **Dica:** no Windows, use **WSL 2** ou Git Bash para que os scripts `./mvnw`/`./mvnw.cmd` funcionem corretamente.

---

## 🚀 Executando a aplicação

```bash
# 1. clone o repositório
$ git clone https://github.com/joilsonmslopes/trackmatch.git
$ cd trackmatch

# 2. inicie a API (profile dev = banco H2 em memória)
$ ./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

### Smoke test rápido

```bash
curl -X GET http://localhost:8080/actuator/health
```

Saída esperada:

```json
{"status":"UP"}
```

---

## 🧪 Executando os testes

```bash
# Testes unitários + integração
$ ./mvnw test
```

Um relatório JaCoCo será gerado em `target/site/jacoco/index.html` (o build falha se a cobertura ficar abaixo de 60 %).

---

## 🗺️ Estrutura do projeto (camadas)

```
src/
 └─ main/
     ├─ java/com/example/trackmatch/
     │   ├─ controller/   ← Endpoints REST
     │   ├─ dto/          ← Objetos de request/response da API
     │   ├─ service/      ← Regras de negócio & orquestração
     │   ├─ repository/   ← Interfaces Spring Data JPA
     │   ├─ model/        ← Entidades JPA (estado do banco)
     │   ├─ exception/    ← Exceções customizadas + handler global
     │   ├─ config/       ← Beans de infraestrutura (CORS, Swagger…)
     │   └─ util/         ← Helpers (mappers, conversores)
     └─ resources/
         └─ application.yaml  ← Configurações por ambiente
```

---

## 🔗 Links úteis

* **Documentação Spring Boot 3.x** – [https://docs.spring.io/spring-boot/docs/current/reference/html/](https://docs.spring.io/spring-boot/docs/current/reference/html/)
* **Spring Data JPA** – [https://spring.io/projects/spring-data-jpa](https://spring.io/projects/spring-data-jpa)
* **Hibernate Validator** – [https://hibernate.org/validator/](https://hibernate.org/validator/)
* **Banco H2** (embedado) – [https://www.h2database.com/](https://www.h2database.com/)
* **Conventional Commits** – [https://www.conventionalcommits.org/](https://www.conventionalcommits.org/)
* **OpenAPI / springdoc** – [https://springdoc.org/](https://springdoc.org/)
* **JUnit 5** & **Mockito** – [https://junit.org/junit5/](https://junit.org/junit5/) | [https://site.mockito.org/](https://site.mockito.org/)

---

## 📬 Contato / Dúvidas

[![joilsonmslopes](https://img.shields.io/badge/joilsonmslopes-LinkedIn-0A66C2?style=flat-square&link=https://www.linkedin.com/in/joilsonmslopes/)](https://www.linkedin.com/in/joilsonmslopes/)