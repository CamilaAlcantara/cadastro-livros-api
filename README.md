# 📚 Livros API

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de livros, autores e assuntos.

A aplicação implementa CRUD completo das entidades, relacionamento N:N entre Livro/Autor e Livro/Assunto, além da geração de relatório em PDF utilizando **JasperReports** com base em uma VIEW do banco de dados.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA / Hibernate**
- **PostgreSQL**
- **JasperReports**
- **Maven**
- **Lombok**

---

## 🏗️ Arquitetura

A aplicação segue uma arquitetura em camadas bem definida:

- **Controller** – Camada REST que expõe os endpoints.
- **Service** – Camada de regras de negócio.
- **Repository** – Camada de persistência (Spring Data JPA).
- **DTOs** – Objetos de transferência de dados (Request/Response) para desacoplamento.
- **Exception Handler** – Tratamento global de erros e padronização de respostas HTTP.

---

## 🗄️ Banco de Dados

O projeto utiliza **PostgreSQL**. Os scripts de inicialização estão na pasta:

```
src/main/resources/scripts/
```

### 📝 Passos para criação manual:

1️⃣ Crie o banco de dados:

```sql
CREATE DATABASE livros;
```

2️⃣ Execute os scripts na ordem:

1. `creates.sql` (Criação de tabelas e VIEW)
2. `inserts.sql` (Popula o banco com dados iniciais)

---

## ⚙️ Configuração (Variáveis de Ambiente)

Por segurança, as credenciais do banco não são fixas no código (*hardcoded*).
O projeto está configurado para buscar **Variáveis de Ambiente**, mas possui valores padrão para facilitar o desenvolvimento local.

| Variável | Descrição | Valor Padrão |
|----------|------------|-------------------------------|
| `DB_URL` | URL de conexão JDBC | `jdbc:postgresql://localhost:5432/livros` |
| `DB_USER` | Usuário do Banco | `postgres` |
| `DB_PASSWORD` | Senha do Banco | `postgres` |

> **Nota:** Para alterar as configurações (ex: mudar a senha ou o host), basta definir estas variáveis no seu sistema operacional ou na IDE antes de rodar.

---

## ▶️ Como Executar

### 1️⃣ Compilar o projeto

```bash
mvn clean package
```

### 2️⃣ Rodar a aplicação

#### Opção A: Modo Padrão (Local)

Se o seu banco local usa `user=postgres` e `password=postgres`, basta rodar:

```bash
mvn spring-boot:run
```

#### Opção B: Configurando Credenciais (Recomendado)

Caso sua senha ou usuário sejam diferentes, passe os valores na execução:

**No Linux/Mac (Terminal):**

```bash
export DB_USER=seu_usuario
export DB_PASSWORD=sua_senha
mvn spring-boot:run
```

**No Windows (PowerShell):**

```powershell
$env:DB_USER="seu_usuario"
$env:DB_PASSWORD="sua_senha"
mvn spring-boot:run
```

A aplicação ficará disponível em:

```
http://localhost:8080
```

---

## 📌 Endpoints Principais

### 📖 Livros

- `GET /livros` - Listar todos
- `GET /livros/{id}` - Buscar por ID
- `POST /livros` - Cadastrar novo
- `PUT /livros/{id}` - Atualizar
- `DELETE /livros/{id}` - Remover

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

A API gera relatórios em PDF utilizando **JasperReports** e Views SQL.

**Rota:**

```http
GET /relatorios/livros-por-autor
```

**Características:**

- Baseado na view `vw_relatorio_livros_por_autor`.
- O download do arquivo inicia automaticamente.

---

## 📬 Contato

Desenvolvido por **Camila Alcantara**  
📧 E-mail: camila.m.a.alcantara@gmail.com