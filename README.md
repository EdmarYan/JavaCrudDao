# Java CRUD DAO - Todo List ✅

Bem-vindo ao projeto **JavaCrudDao**! Este é um sistema de gerenciamento de tarefas (Todo List) desenvolvido em **Java** puro, aplicando conceitos fundamentais como Padrão **DAO** (Data Access Object), **MVC** (Model-View-Controller) e manipulação de banco de dados relacional via **JDBC**.

## 📌 Funcionalidades

O sistema possui uma interface interativa baseada em `JOptionPane` e suporta operações completas de CRUD (Create, Read, Update, Delete) para 3 entidades principais:

- 👤 **Usuários**: Cadastro, Listagem, Atualização e Exclusão.
- 🏷️ **Categorias**: Gerenciamento de categorias para melhor organização.
- 📋 **Tarefas**: 
  - Gerenciamento completo de tarefas.
  - Filtro por status (pendente, concluída, etc).
  - Ordenação.
  - Exibição de relatórios interativos.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java (JDK 8 ou superior)
- **Design Patterns:** MVC (Model, View, Controller) e DAO (Data Access Object)
- **Banco de Dados:** MySQL / MariaDB (integrado via XAMPP)
- **Interface:** Java Swing (`JOptionPane`)
- **IDE Padrão:** Apache NetBeans (mas pode ser rodado em IntelliJ ou Eclipse)

## 🗂️ Estrutura do Projeto

A arquitetura do projeto foi dividida em pacotes para manter as responsabilidades separadas:

```
src/
 ├── controller/  # Controladores: Orquestram a interface visual com a lógica e persistência.
 ├── dao/         # Data Access Object: Classes que contêm as queries SQL (INSERT, SELECT, UPDATE, DELETE).
 ├── main/        # Classes de inicialização (MainTeste.java contém o loop do programa).
 ├── model/       # Entidades: Classes puras representando Usuário, Categoria e Tarefa.
 ├── util/        # Utilitários: Classe genérica (ConnectionFactory) para gerar as conexões com o MySQL.
```

---

## 🚀 Como Rodar o Projeto

### Passo 1: Configurar o Banco de Dados

1. Instale o [XAMPP](https://www.apachefriends.org/pt_br/index.html) e inicie os serviços do **Apache** e **MySQL**.
2. Acesse o seu gerenciador de banco de dados (ex: `phpMyAdmin` em `http://localhost/phpmyadmin/` ou via DBeaver/MySQL Workbench).
3. Importe o script SQL do banco de dados contido neste repositório em:
   `bancoDeDados/todolist.sql`

*(Isso criará o banco `todo_list` com as tabelas `Usuario`, `Categoria` e `Tarefa`).*

### Passo 2: Executar a Aplicação
- **Via NetBeans**: Abra o projeto, clique em "Clean and Build" e depois em "Run". A classe principal é a `src/main/MainTeste.java`.
- **Via IDE (IntelliJ/Eclipse)**: Clone o projeto, configure o JDK e rode a classe `MainTeste`.
- _Lembre-se de verificar se o conector do MySQL (MySQL JDBC Driver) está adicionado nas bibliotecas do projeto_.

## 🧐 Auditoria e Boas Práticas (Pontos de Atenção)

Durante uma rápida análise (auditoria técnica) deste código, os seguintes padrões positivos foram identificados:
- **Gerenciamento de Conexão Seguro:** As classes do DAO utilizam a estrutura `try-with-resources`. Isso garante que conexões, Statements e ResultSets sejam fechados automaticamente, prevenindo vazamentos de memória (Memory Leaks).
- **Injeção via Prepared Statements:** Todo acesso SQL é parametrizado (`?`), o que previne completamente vulnerabilidades contra *SQL Injection*.
- **Estrutura MVC Bem Definida:** O código de view (as caixas de mensagem do JOptionPane) está nos controllers, isolando os Models e a Lógica de SQL.

### Sugestões de Evolução Futura
1. **Tratamento de Senhas:** Atualizar os métodos de persistência para criptografar senhas (usando Bcrypt) em vez de salvá-las em texto plano no banco de dados.
2. **Separação View/Controller:** Caso a aplicação cresça, mover os blocos `JOptionPane` dos `Controllers` para classes puramente de `View`, deixando os `Controllers` sem dependência de biblioteca gráfica (`javax.swing`).

---
Feito para fins educacionais e aprimoramento em boas práticas de programação Java Orientada a Objetos.
