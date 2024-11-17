package dao;

import model.Categoria;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    // Método para salvar uma nova categoria no banco de dados.
    public void saveCategoriaDao(Categoria categoria) {
        // Query SQL para inserir os valores na tabela Categoria.
        String sql = "INSERT INTO Categoria(nome, descricao) VALUES (?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            // Define os valores para os placeholders (?) na consulta SQL.
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            
            // Executa o comando de inserção no banco de dados.
            stmt.executeUpdate();
            
            // Obtém a chave gerada automaticamente (ID da nova categoria).
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    categoria.setId(rs.getInt(1));
                }
            }
            
        } catch (SQLIntegrityConstraintViolationException e) {
            // Erro específico para violações de integridade, como duplicação de nome.
            System.err.println("Erro: Categoria já existe com esse nome.");
        } catch (SQLException e) {
            // Tratamento genérico para erros de SQL.
            System.err.println("Erro ao salvar categoria: " + e.getMessage());
        } catch (Exception e) {
            // Tratamento para outros erros inesperados.
            System.err.println("Erro inesperado ao salvar categoria: " + e.getMessage());
        }
    }

    // Método para buscar todas as categorias armazenadas no banco de dados.
    public List<Categoria> findAllCategoriaDao() {
        // Query SQL para buscar todas as categorias.
        String sql = "SELECT * FROM Categoria";
        List<Categoria> categorias = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            // Itera pelos resultados da consulta e popula a lista de categorias.
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id")); // Define o ID da categoria.
                categoria.setNome(rs.getString("nome")); // Define o nome.
                categoria.setDescricao(rs.getString("descricao")); // Define a descrição.
                categorias.add(categoria); // Adiciona à lista.
            }
            
        } catch (SQLException e) {
            // Tratamento de erros durante a consulta.
            System.err.println("Erro ao listar categorias: " + e.getMessage());
        }
        
        // Retorna a lista de categorias.
        return categorias;
    }

    // Método para atualizar uma categoria existente no banco de dados.
    public void updateCategoriaDao(Categoria categoria) {
        // Query SQL para atualizar os valores de uma categoria específica.
        String sql = "UPDATE Categoria SET nome = ?, descricao = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            // Define os valores para os placeholders (?) na consulta SQL.
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getId());
            
            // Executa o comando de atualização e verifica se algo foi alterado.
            if (stmt.executeUpdate() == 0) {
                System.err.println("Erro: Nenhuma categoria foi atualizada. Verifique se o ID é válido.");
            }
            
        } catch (SQLIntegrityConstraintViolationException e) {
            // Tratamento de erros de integridade.
            System.err.println("Erro: Não foi possível atualizar a categoria. Verifique os dados fornecidos.");
        } catch (SQLException e) {
            // Tratamento genérico para erros de SQL.
            System.err.println("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    // Método para deletar uma categoria com base no ID fornecido.
    public void deleteCategoriaDao(int id) {
        // Query SQL para deletar uma categoria pelo ID.
        String sql = "DELETE FROM Categoria WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            // Define o valor do placeholder (?) na consulta SQL.
            stmt.setInt(1, id);
            
            // Executa o comando de exclusão e verifica se algo foi removido.
            if (stmt.executeUpdate() == 0) {
                System.err.println("Erro: Nenhuma categoria foi encontrada com o ID fornecido.");
            }
            
        } catch (SQLException e) {
            // Tratamento de erros durante a exclusão.
            System.err.println("Erro ao deletar categoria: " + e.getMessage());
        }
    }
}
