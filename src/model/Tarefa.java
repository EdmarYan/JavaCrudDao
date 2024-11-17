package model;

public class Tarefa {
    private int id; // ID único da tarefa
    private String titulo; // Título da tarefa
    private String descricao; // Descrição da tarefa
    private int status; // Status da tarefa (1 - Concluído, 0 - Não Concluído)
    private int usuarioId; // ID do usuário que criou a tarefa
    private int categoriaId; // ID da categoria da tarefa

    // Construtor padrão
    public Tarefa() {}

    // Construtor que recebe o título, descrição, status, usuário e categoria(sobrecarga)
    //(por exemplo, ao adicionar uma nova tarefa no banco de dados, 
    //onde o ID é gerado automaticamente)
    public Tarefa(String titulo, String descricao, int status, int usuarioId, int categoriaId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
    }

    // Construtor que recebe todos os atributos, incluindo ID(sobrecarga)
    // Usado quando precisamos de um objeto completo, incluindo o ID 
    //(por exemplo, ao listar tarefa do banco de dados)
    public Tarefa(int id, String titulo, String descricao, int status, int usuarioId, int categoriaId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
    }

    // Getters e Setters(encapsulamento)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
 this.status = status;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    // Método toString
    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", usuarioId=" + usuarioId +
                ", categoriaId=" + categoriaId +
                '}';
    }
}