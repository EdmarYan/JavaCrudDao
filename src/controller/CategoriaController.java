package controller;

import dao.CategoriaDAO; // Importa a classe CategoriaDAO para operações com categorias
import model.Categoria; // Importa a classe Categoria que representa o modelo de dados

import javax.swing.*; // Importa a biblioteca para interfaces gráficas
import java.util.List; // Importa a lista para manipulação de coleções

public class CategoriaController {
    private CategoriaDAO categoriaDAO = new CategoriaDAO(); // Instância do serviço de categoria para interagir com o banco de dados

    // Método para cadastrar uma nova categoria
    public void saveCategoria() {
        boolean verificado = false; // Flag para controle do loop
        while (!verificado) { // Loop até que a categoria seja cadastrada com sucesso
            try {
                // Solicita ao usuário o nome e a descrição da categoria
                String nome = JOptionPane.showInputDialog("Digite o nome da categoria:");
                String descricao = JOptionPane.showInputDialog("Digite a descrição da categoria:");

                // Cria uma nova instância de Categoria com os dados fornecidos
                Categoria categoria = new Categoria(nome, descricao); 
                saveCategoriaVerificated(categoria); // Chama o método para salvar a categoria
                JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!"); // Mensagem de sucesso
                verificado = true; // Atualiza a flag para sair do loop
            } catch (Exception e) {
                // Exibe mensagem de erro caso ocorra uma exceção
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar categoria: " + e.getMessage());
            }
        }
    }

    // Método para verificar se os dados da categoria são válidos
    public boolean verificationSaveCategoria(Categoria categoria) {
        // Validações de dados
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Erro: O nome da categoria não pode ser vazio.");
            return false; // Retorna falso se o nome for inválido
        }
        
        if (categoria.getDescricao() == null || categoria.getDescricao().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Erro: A descrição da categoria não pode ser vazia.");
            return false; // Retorna falso se a descrição for inválida
        }
        return true; // Retorna verdadeiro se todas as validações passarem
    }

    // Método para salvar a categoria no banco de dados
    public void saveCategoriaVerificated(Categoria categoria) {
        if (verificationSaveCategoria(categoria)) { // Verifica se a categoria é válida
            categoriaDAO.saveCategoriaDao(categoria); // Salva a categoria usando o DAO
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível salvar a categoria devido a erros de validação.");
        }
    }

    // Método para listar todas as categorias cadastradas
    public void findAllCategorias() {
        List<Categoria> categorias = findCategorias(); // Busca todas as categorias
        StringBuilder categoriasList = new StringBuilder("Categorias cadastradas:\n"); // StringBuilder para formatar a lista
        for (Categoria categoria : categorias) {
            categoriasList.append(categoria).append("\n"); // Adiciona cada categoria à lista
        }
        // Exibe a lista de categorias em um diálogo
        JOptionPane.showMessageDialog(null, categoriasList.toString());
    }

    // Método para buscar todas as categorias do DAO
    public List<Categoria> findCategorias() {
        return categoriaDAO.findAllCategoriaDao(); // Retorna a lista de categorias do DAO
    }

    // Método para atualizar uma categoria existente
    public void updateCategoria() {
        boolean verificado = false; // Flag para controle do loop
        while (!verificado) { // Loop até que a categoria seja atualizada com sucesso
            try {
                // Solicita ao usuário o ID, nome e descrição da categoria
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da categoria a ser atualizada:"));
                String nome = JOptionPane.showInputDialog("Digite o novo nome da categoria:");
                String descricao = JOptionPane.showInputDialog("Digite a nova descrição da categoria:");

                // Cria uma nova instância de Categoria com os dados fornecidos
                Categoria categoria = new Categoria(id, nome, descricao); 
                
                saveUpdateCategoriaVerificated(categoria); // Chama o método para salvar a atualização
                JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!"); // Mensagem de sucesso
                verificado = true; // Atualiza a flag para sair do loop
            } catch (Exception e) {
                // Exibe mensagem de erro caso ocorra uma exceção
                JOptionPane.showMessageDialog(null, "Erro ao atualizar categoria: " + e.getMessage());
            }
        }
    }

    // Método para verificar se os dados da categoria para atualização são válidos
    public boolean verificationUpdateCategoria(Categoria categoria) {
        if (categoria.getId() <= 0) {
            JOptionPane.showMessageDialog(null,"Erro: ID inválido para atualização.");
            return true; // Retorna verdadeiro se o ID for inválido
        }

        // Validações de dados
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Erro: O nome da categoria não pode ser vazio.");
            return true; // Retorna verdadeiro se o nome for inválido
        }
        return true; // Retorna verdadeiro se todas as validações passarem
    }

    // Método para salvar a atualização da categoria no banco de dados
    public void saveUpdateCategoriaVerificated(Categoria categoria) {
        if (verificationUpdateCategoria(categoria)) { // Verifica se a categoria é válida
            categoriaDAO.updateCategoriaDao(categoria); // Atualiza a categoria usando o DAO
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível salvar a categoria devido a erros de validação.");
        }
    }

    // Método para excluir uma categoria
    public void deleteCategoria() {
        boolean verificado = false; // Flag para controle do loop
        while (!verificado) { // Loop até que a categoria seja excluída com sucesso
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da categoria a ser excluída:"));
                deleteCategoriaVerificated(id); // Chama o método para excluir a categoria
                JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso!"); // Mensagem de sucesso
                verificado = true; // Atualiza a flag para sair do loop
            } catch (Exception e) {
                // Exibe mensagem de erro caso ocorra uma exceção
                JOptionPane.showMessageDialog(null, "Erro ao excluir categoria: " + e.getMessage());
            }
        }
    }

    // Método para deletar uma categoria pelo ID
    public boolean deleteCategoriaVerificated(int id) {
        if (id <= 0) {
            JOptionPane.showMessageDialog(null,"Erro: ID inválido para exclusão.");
            return false; // Retorna falso se o ID for inválido
        }
        categoriaDAO.deleteCategoriaDao(id); // Chama o método do DAO para deletar a categoria
        return true; // Retorna verdadeiro se a exclusão for bem-sucedida
    }
}