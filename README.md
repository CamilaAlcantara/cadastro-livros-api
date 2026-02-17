
# 📚 Livros API

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de livros, autores e assuntos.

A aplicação implementa CRUD completo das entidades, relacionamento N:N entre Livro/Autor e Livro/Assunto, além da geração de relatório em PDF utilizando JasperReports com base em uma VIEW do banco de dados.

---

## 🚀 Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- JasperReports
- Maven

---

## 🏗️ Arquitetura

A aplicação segue arquitetura em camadas:

- **Controller** – Exposição dos endpoints REST
- **Service** – Regras de negócio
- **Repository** – Acesso a dados via Spring Data JPA
- **DTOs** – Objetos de entrada e saída
- **Exception Handler Global** – Tratamento centralizado de erros



## 🗄️ Banco de Dados

O projeto utiliza PostgreSQL.

Os scripts estão disponíveis na pasta:

```
scripts/
```

### Arquivos:

- `creates.sql` – Criação das tabelas e VIEW
- `inserts.sql` – Dados iniciais

### ▶️ Criação manual do banco

1️⃣ Criar o banco:

```sql
CREATE DATABASE livros;
```

2️⃣ Executar os scripts na seguinte ordem:

- `creates.sql`
- `inserts.sql`

---

## ⚙️ Configuração

As propriedades do banco utilizam variáveis de ambiente com valores padrão:

```properties
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/livros}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

Caso não sejam definidas variáveis, os valores padrão acima serão utilizados.

---

## ▶️ Como Executar

### 1️⃣ Compilar o projeto

```bash
mvn clean package
```

### 2️⃣ Executar

```bash
mvn spring-boot:run
```

ou

```bash
java -jar target/*.jar
```

A aplicação ficará disponível em:

```
http://localhost:8080
```

---

## 📌 Endpoints Principais

### 📖 Livros

- `GET /livros`
- `GET /livros/{id}`
- `POST /livros`
- `PUT /livros/{id}`
- `DELETE /livros/{id}`

### ✍️ Autores

- `GET /autores`
- `POST /autores`
- `PUT /autores/{id}`
- `DELETE /autores/{id}`

### 🏷️ Assuntos

- `GET /assuntos`
- `POST /assuntos`
- `PUT /assuntos/{id}`
- `DELETE /assuntos/{id}`

---

## 🧾 Relatório

Endpoint responsável pela geração do relatório em PDF:

```
GET /relatorios/livros-por-autor
```

O relatório:

- Utiliza JasperReports
- É baseado na VIEW `vw_relatorio_livros_por_autor`
- Retorna PDF como resposta da API

---

## ⚠️ Observações Importantes

- O campo **Valor (R$)** é obrigatório na criação de livros.
- O relacionamento entre Livro e Autor/Assunto é N:N.
- O relatório atende ao requisito de utilização de VIEW no banco.

---

## 📬 Contato

E-mail: camila.m.a.alcantara@gmail.com
