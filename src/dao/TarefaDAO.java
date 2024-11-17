package dao;

import model.Tarefa;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TarefaDAO {

    // Método para salvar uma nova tarefa no banco de dados
    public void saveTarefaDao(Tarefa tarefa) {
        // SQL para inserir uma nova tarefa na tabela "Tarefa"
        String sql = "INSERT INTO Tarefa(titulo, descricao, status, usuario_id, categoria_id) VALUES (?, ?, ?, ?, ?)";

        // Usando try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados automaticamente
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Configuração dos parâmetros da query SQL
            stmt.setString(1, tarefa.getTitulo()); // Define o título da tarefa
            stmt.setString(2, tarefa.getDescricao()); // Define a descrição da tarefa
            stmt.setInt(3, tarefa.getStatus()); // Define o status da tarefa (1 - Concluída, 0 - Pendente)
            stmt.setInt(4, tarefa.getUsuarioId()); // Define o ID do usuário associado à tarefa
            stmt.setInt(5, tarefa.getCategoriaId()); // Define o ID da categoria associada à tarefa

            // Executa a instrução SQL para inserir a tarefa
            stmt.executeUpdate();

            // Recupera a chave primária gerada automaticamente (ID) da tarefa recém-inserida
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tarefa.setId(rs.getInt(1)); // Atualiza o ID da tarefa com o valor gerado pelo banco
                }
            }
            
        } catch (Exception e) {
            // Captura qualquer outra exceção inesperada
            JOptionPane.showMessageDialog(null,"Erro inesperado ao salvar tarefa: " + e.getMessage());
        }
    }

    // Método para listar todas as tarefas do banco de dados
    public List<Tarefa> findAllTarefaDao() {
        // SQL para selecionar todas as tarefas da tabela "Tarefa"
        String sql = "SELECT * FROM Tarefa";
        List<Tarefa> tarefas = new ArrayList<>(); // Lista para armazenar as tarefas

        // Usando try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados automaticamente
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Iteração sobre o ResultSet para transformar os dados em objetos Tarefa
            while (rs.next()) {
                Tarefa tarefa = new Tarefa(); // Cria um novo objeto Tarefa
                tarefa.setId(rs.getInt("id")); // Atribui o ID da tarefa
                tarefa.setTitulo(rs.getString("titulo")); // Atribui o título da tarefa
                tarefa.setDescricao(rs.getString("descricao")); // Atribui a descrição da tarefa
                tarefa.setStatus(rs.getInt("status")); // Atribui o status da tarefa
                tarefa.setUsuarioId(rs.getInt("usuario_id")); // Atribui o ID do usuário associado
                tarefa.setCategoriaId(rs.getInt("categoria_id")); // Atribui o ID da categoria associada
                tarefas.add(tarefa); // Adiciona a tarefa à lista
            }

        } catch (SQLException e) {
            // Captura exceção de SQL e exibe a mensagem de erro
            JOptionPane.showMessageDialog(null,"Erro ao listar tarefas: " + e.getMessage());
        }

        return tarefas; // Retorna a lista de tarefas
    }

    // Método para atualizar uma tarefa existente no banco de dados
    public void updateTarefaDAO(Tarefa tarefa) {
        // SQL para atualizar uma tarefa existente com base no ID
        String sql = "UPDATE Tarefa SET titulo = ?, descricao = ?, status = ?, usuario_id = ?, categoria_id = ? WHERE id = ?";

        // Usando try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados automaticamente
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configuração dos parâmetros da query SQL
            stmt.setString(1, tarefa.getTitulo()); // Define o novo título da tarefa
            stmt.setString(2, tarefa.getDescricao()); // Define a nova descrição da tarefa
            stmt.setInt(3, tarefa.getStatus()); // Define o novo status da tarefa
            stmt.setInt(4, tarefa.getUsuarioId()); // Define o novo ID do usuário associado
            stmt.setInt(5, tarefa.getCategoriaId()); // Define o novo ID da categoria associada
            stmt.setInt(6, tarefa.getId()); // Define o ID da tarefa a ser atualizada

            // Executa a atualização no banco de dados
            if (stmt.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null,"Erro: Nenhuma tarefa foi atualizada. Verifique se o ID é válido.");
            }
        } catch (SQLException e) {
            // Captura exceção de SQL e exibe a mensagem de erro
            JOptionPane.showMessageDialog(null,"Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    // Método para deletar uma tarefa do banco de dados
    public void deleteTarefaDao(int id) {
        // SQL para deletar uma tarefa com base no ID
        String sql = "DELETE FROM Tarefa WHERE id = ?";

        // Usando try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados automaticamente
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Define o ID da tarefa a ser deletada

            // Verifica se a tarefa foi deletada
            if (stmt.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null,"Erro: Nenhuma tarefa foi encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            // Captura exceção de SQL e exibe a mensagem de erro
            JOptionPane.showMessageDialog(null,"Erro ao deletar tarefa: " + e.getMessage());
        }
    }

    // Método para buscar tarefas por status
    public List<Tarefa> findByStatusTarefaDao(int status) {
        // SQL para selecionar tarefas com base no status
        String sql = "SELECT * FROM Tarefa WHERE status = ?";
        List<Tarefa> tarefas = new ArrayList<>(); // Lista para armazenar as tarefas filtradas

        // Usando try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados automaticamente
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, status); // Define o status para a consulta
            try (ResultSet rs = stmt.executeQuery()) {
                // Iteração sobre o ResultSet para transformar os dados em objetos Tarefa
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa(); // Cria um novo objeto Tarefa
                    tarefa.setId(rs.getInt("id")); // Atribui o ID da tarefa
                    tarefa.setTitulo(rs.getString("titulo")); // Atribui o título da tarefa
                    tarefa.setDescricao(rs.getString("descricao")); // Atribui a descrição da tarefa
                    tarefa.setStatus(rs.getInt("status")); // Atribui o status da tarefa
                    tarefa.setUsuarioId(rs.getInt("usuario_id")); // Atribui o ID do usuário associado
                    tarefa.setCategoriaId(rs.getInt("categoria_id")); // Atribui o ID da categoria associada
                    tarefas.add(tarefa); // Adiciona a tarefa à lista
                }
            }
        } catch (SQLException e) {
            // Captura exceção de SQL e exibe a mensagem de erro
            System.err.println("Erro ao buscar tarefas por status: " + e.getMessage());
        }
        return tarefas; // Retorna a lista de tarefas filtradas
    }

    // Método para listar todas as tarefas ordenadas por status
    public List<Tarefa> findAllOrderedByStatusTarefaDao() {
        // SQL para selecionar todas as tarefas ordenadas por status
        String sql = "SELECT * FROM Tarefa ORDER BY status";
        List<Tarefa> tarefas = new ArrayList<>(); // Lista para armazenar as tarefas ordenadas

        // Usando try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados automaticamente
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            // Iteração sobre o ResultSet para transformar os dados em objetos Tarefa
            while (rs.next()) {
                Tarefa tarefa = new Tarefa(); // Cria um novo objeto Tarefa
                tarefa.setId(rs.getInt("id")); // Atribui o ID da tarefa
                tarefa.setTitulo(rs.getString("titulo")); // Atribui o título da tarefa
                tarefa.setDescricao(rs.getString("descricao")); // Atribui a descrição da tarefa
                tarefa.setStatus(rs.getInt("status")); // Atribui o status da tarefa
                tarefa.setUsuarioId(rs.getInt("usuario_id")); // Atribui o ID do usuário associado
                tarefa.setCategoriaId(rs.getInt("categoria_id")); // Atribui o ID da categoria associada
                tarefas.add(tarefa); // Adiciona a tarefa à lista
            }
        } catch (SQLException e) {
            // Captura exceção de SQL e exibe a mensagem de erro
            JOptionPane.showMessageDialog(null,"Erro ao listar tarefas ordenadas por status: " + e.getMessage());
        }
        return tarefas; // Retorna a lista de tarefas ordenadas
    }

    // Método para contar o total de tarefas no banco de dados
    public int countAllTarefaDao() {
        // SQL para contar o total de tarefas na tabela "Tarefa"
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Tarefa";

        // Usando try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados automaticamente
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1); // Obtém o total de tarefas
            }
        } catch (SQLException e) {
            // Captura exceção de SQL e exibe a mensagem de erro
            JOptionPane.showMessageDialog(null,"Erro ao contar tarefas: " + e.getMessage());
        }
        return count; // Retorna o total de tarefas
    }

    // Método para deletar tarefas associadas a um usuário (chave estrangeira)
    public void deleteByUsuarioIdTarefaDao(int usuarioId) {
        // SQL para deletar tarefas com base no ID do usuário
        String sql = "DELETE FROM Tarefa WHERE usuario_id = ?";

        // Usando try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados automaticamente
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId); // Define o ID do usuário
            stmt.executeUpdate(); // Executa a deleção
        } catch (SQLException e) {
            // Captura exceção de SQL e exibe a mensagem de erro
            JOptionPane.showMessageDialog(null,"Erro ao deletar tarefas associadas ao usuário: " + e.getMessage());
        }
    }
}