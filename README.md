# Java CRUD DAO - Todo List

Fala pessoal, beleza? Meu nome é **Edmar Yan Faria de Melo** e esse é um projetinho que eu desenvolvi na época em que eu estudava na **ETB**. 

A ideia aqui foi construir um sistema de gerenciamento de tarefas (Todo List) em Java puro, colocando em prática conceitos como padrão **DAO** (Data Access Object), **MVC** e manipulação de banco de dados via **JDBC**. Ele tem uma interface bem simples feita com `JOptionPane` (aquelas caixinhas de diálogo do Java Swing).

## O que o sistema faz?
Ele é um CRUD completo para gerenciar três coisas:
- **Usuários:** Criar conta, editar, listar e deletar.
- **Categorias:** Pra organizar as tarefas.
- **Tarefas:** A parte principal. Dá pra cadastrar, mudar o status (pendente, concluído, etc), filtrar e gerar um relatório.

A estrutura do código tá dividida bonitinha em pacotes (`model`, `dao`, `controller`), então tá bem fácil de entender como as coisas se conectam. Além disso, as consultas no banco tão protegidas contra SQL Injection e usando `try-with-resources` pra não dar ruim com conexão aberta travando o banco.

## Como rodar no seu PC?

Você vai precisar rodar o banco de dados (MySQL) e depois compilar a aplicação no seu NetBeans, Eclipse ou IntelliJ. O ponto de entrada da aplicação é o arquivo `src/main/MainTeste.java`.

### Subindo o Banco de Dados (Opção 1: Docker 🐳)
Para facilitar a vida e não precisar instalar nada pesado, eu adicionei um arquivo Docker. Se você tiver o Docker instalado, basta abrir o terminal na pasta do projeto e rodar:

```bash
docker compose up -d
```

Só isso! O Docker vai baixar o MySQL, subir na porta 3306 e já vai criar o banco `todo_list` e todas as tabelas usando o script que deixei na pasta `bancoDeDados`. Depois disso é só rodar o projeto Java.

### Subindo o Banco de Dados (Opção 2: XAMPP)
Se você não usa Docker e prefere o XAMPP da velha guarda:
1. Liga o Apache e o MySQL no XAMPP.
2. Abre o phpMyAdmin (ou DBeaver).
3. Roda o script que tá no arquivo `bancoDeDados/todolist.sql`. Ele já vai criar o banco e as tabelas pra você.

## Considerações
Esse foi um projeto acadêmico de estudos, mas serve muito bem como base pra quem tá aprendendo Java e banco de dados. Fiquem à vontade pra clonar, brincar com o código ou mandar um pull request!
