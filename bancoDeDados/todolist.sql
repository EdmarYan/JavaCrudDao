-- Criação do banco de dados
CREATE DATABASE todo_list;
USE todo_list;

-- Tabela de Usuários
CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL
);

-- Tabela de Categorias
CREATE TABLE Categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

-- Tabela de Tarefas
CREATE TABLE Tarefa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    status INT NOT NULL,
    usuario_id INT NOT NULL,
    categoria_id INT,
    
    -- Definição de chaves estrangeiras
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (categoria_id) REFERENCES Categoria(id)
);
