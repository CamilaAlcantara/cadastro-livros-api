CREATE DATABASE livros
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Tabela Autor
CREATE TABLE autor (
   cod_au SERIAL PRIMARY KEY,
   nome VARCHAR(40) NOT NULL
);
-- Tabela Assunto
CREATE TABLE assunto (
    cod_as SERIAL PRIMARY KEY,
    descricao VARCHAR(20) NOT NULL
);

-- Tabela Livro
CREATE TABLE livro (
   cod_l SERIAL PRIMARY KEY,
   titulo VARCHAR(40) NOT NULL,
   editora VARCHAR(40) NOT NULL,
   edicao INTEGER NOT NULL,
   ano_publicacao VARCHAR(4) NOT NULL,
   valor DECIMAL(10,2) NOT NULL
);

-- Tabela de relacionamento Livro-Autor
    CREATE TABLE livro_autor (
     livro_cod_l INTEGER NOT NULL,
     autor_cod_au INTEGER NOT NULL,
     PRIMARY KEY (livro_cod_l, autor_cod_au),
     FOREIGN KEY (livro_cod_l) REFERENCES livro(cod_l),
     FOREIGN KEY (autor_cod_au) REFERENCES autor(cod_au)
);

-- Tabela de relacionamento Livro-Assunto
CREATE TABLE livro_assunto (
   livro_cod_l INTEGER NOT NULL,
   assunto_cod_as INTEGER NOT NULL,
   PRIMARY KEY (livro_cod_l, assunto_cod_as),
   FOREIGN KEY (livro_cod_l) REFERENCES livro(cod_l),
   FOREIGN KEY (assunto_cod_as) REFERENCES assunto(cod_as)
);