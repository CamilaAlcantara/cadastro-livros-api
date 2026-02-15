INSERT INTO autor (nome) VALUES
     ('Robert C. Martin'),
     ('Martin Fowler'),
     ('Eric Evans'),
     ('Kent Beck'),
     ('Andrew Hunt'),
     ('David Thomas');

INSERT INTO livro (titulo, editora, edicao, ano_publicacao, valor) VALUES
   ('Clean Code', 'Prentice Hall', 1, '2008', 159.90),
   ('Refactoring', 'Addison-Wesley', 2, '2018', 189.90),
   ('The Pragmatic Programmer', 'Addison-Wesley', 2, '2019', 179.90);

INSERT INTO assunto (descricao) VALUES
    ('Clean Code'),
    ('Refatoração'),
    ('Arquitetura');

-- Cria relacionamento entre Livro e Autor
INSERT INTO livro_autor (livro_cod_l, autor_cod_au) VALUES
    (1, 1), -- Robert C. Martin
    (1, 4), -- Kent Beck
    (1, 2); -- Martin Fowler

INSERT INTO livro_autor (livro_cod_l, autor_cod_au) VALUES
    (2, 2); -- Martin Fowler

INSERT INTO livro_autor (livro_cod_l, autor_cod_au) VALUES
    (3, 5), -- Andrew Hunt
    (3, 6); -- David Thomas

-- Cria relacionamento entre Livro e Assunto
INSERT INTO livro_assunto (livro_cod_l, assunto_cod_as) VALUES
-- Livro 1
    (1, 1),
    (1, 3),

-- Livro 2
    (2, 2),
    (2, 3),

-- Livro 3
    (3, 1),
    (3, 3);