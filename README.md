# Java CRUD DAO - Gerenciador de Tarefas

Este repositório contém um sistema de gerenciamento de tarefas (Todo List) desenvolvido em **Java**. Foi criado originalmente como um projeto acadêmico durante meus estudos na **ETB** (Escola Técnica de Brasília), com o objetivo de consolidar fundamentos de Programação Orientada a Objetos e acesso a banco de dados.

O foco do projeto é demonstrar a aplicação de padrões de arquitetura como **MVC** (Model-View-Controller) e **DAO** (Data Access Object), além da manipulação direta de um banco de dados relacional (MySQL) através de conexão **JDBC**.

## 📌 Funcionalidades

O sistema possui uma interface gráfica baseada em `JOptionPane` e provê operações completas de CRUD (Criar, Ler, Atualizar, Deletar) para três entidades principais:
- **Usuários:** Cadastro, listagem, atualização e remoção.
- **Categorias:** Estruturação para agrupamento de tarefas.
- **Tarefas:** Criação, modificação de status, filtros operacionais e exibição de relatórios.

## 🛠️ Tecnologias e Padrões

- **Linguagem:** Java
- **Interface:** Java Swing (`JOptionPane`)
- **Padrões de Projeto:** MVC, DAO
- **Banco de Dados:** MySQL
- **Boas Práticas Implementadas:** 
  - Proteção contra vulnerabilidades de *SQL Injection* utilizando `PreparedStatement`.
  - Prevenção contra *Memory Leaks* no banco de dados, fazendo o uso rigoroso de blocos `try-with-resources` para fechamento e devolução automática de conexões.

## 🗂️ Arquitetura do Projeto

A organização interna segue as camadas do padrão de projeto:

- `src/model/`: Classes puras que representam as entidades de negócio (Usuário, Categoria, Tarefa).
- `src/dao/`: Camada de persistência que gerencia as operações (queries SQL) para o banco.
- `src/controller/`: Orquestra as interações entre as chamadas visuais e a camada de acesso a dados.
- `src/main/`: Contém a classe principal (`MainTeste.java`) com o *loop* de execução do sistema.
- `src/util/`: Componentes utilitários, como a classe `ConnectionFactory` responsável por lidar com o JDBC.

## 🚀 Como Executar

A aplicação requer um ambiente Java local e uma instância ativa do banco de dados MySQL na porta `3306`. A inicialização do código deve ser feita compilando e executando a classe `src/main/MainTeste.java` em sua IDE de preferência (como NetBeans, IntelliJ ou Eclipse).

Abaixo estão as instruções de como provisionar o banco de dados.

### Subindo o Banco de Dados (Opção 1: Docker 🐳)

O projeto inclui uma configuração de contêinerização para o MySQL, permitindo levantar o banco e criar as tabelas automaticamente de forma transparente. Tendo o Docker instalado, abra o terminal na raiz do projeto e execute:

```bash
docker compose up -d
```
A partir deste momento, o banco já estará pronto para receber as conexões do Java.

### Subindo o Banco de Dados (Opção 2: XAMPP / Local)

Caso prefira gerenciar o banco manualmente sem o Docker:
1. Inicie o serviço do MySQL localmente (através do painel do XAMPP, por exemplo).
2. Abra seu cliente de banco de dados (phpMyAdmin, DBeaver, MySQL Workbench).
3. Importe e execute as queries do arquivo de setup localizado em `bancoDeDados/todolist.sql`. Ele irá criar o *database* `todo_list` bem como todas as tabelas necessárias.
