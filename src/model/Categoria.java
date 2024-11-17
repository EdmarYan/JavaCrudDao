package model;

// Classe Categoria representa uma categoria de tarefas
public class Categoria {

    private int id; // ID único da categoria
    private String nome; // Nome da categoria
    private String descricao; // Descrição da categoria

    // Construtor padrão 
    //(necessário caso precise criar um objeto sem inicializar atributos)
    public Categoria() {
    }

    // Construtor que inicializa apenas o nome e a descrição
    // Usado quando queremos criar uma nova categoria sem especificar o ID 
    //(por exemplo, ao adicionar uma nova categoria no banco de dados, 
    //onde o ID é gerado automaticamente)
    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Construtor que inicializa todos os atributos, incluindo o ID
    // Usado quando precisamos de um objeto completo, incluindo o ID 
    //(por exemplo, ao listar categorias do banco de dados)
    public Categoria(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e setters para acessar e modificar os atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Método toString para facilitar a exibição do objeto como uma string
    @Override
    public String toString() {
        return "Categoria{"
                + "id=" + id
                + ", nome='" + nome + '\''
                + ", descricao='" + descricao + '\''
                + '}';
    }
}
