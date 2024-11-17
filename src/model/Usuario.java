package model;

public class Usuario {
    private int id; // ID único do usuário
    private String nome; // Nome do usuário
    private String email; // Email do usuário
    private String senha; // Senha do usuário

    // Construtor padrão
    public Usuario() {}

    /*
    Construtor que recebe o nome, email e senha(sobrecarga)
    (por exemplo, ao adicionar um novo usuario no banco de dados, 
    onde o ID é gerado automaticamente)
    */
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Construtor que recebe todos os atributos, incluindo ID(sobrecarga)
    // Usado quando precisamos de um objeto completo, incluindo o ID 
    //(por exemplo, ao listar usuarios do banco de dados)
    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método toString
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}