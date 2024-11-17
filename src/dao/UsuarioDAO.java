package dao;

//importação de model usiario para usar seus atributos.
import model.Usuario;
//importação da nossa fabrica de conexão para sempre que precisar iniciar uma conexão
import util.ConnectionFactory;

//bibliotecas para manupulação das logicas de gerenciamento de usuario.
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    //metodo de cadastro de usuario.
    public void saveUserDao(Usuario usuario) {
        //Query sql, parametros que serão enviados a um "script" sql, utilizando o seguinte codigo sql:
        String sql = "INSERT INTO Usuario(nome, email, senha) VALUES (?, ?, ?)";

        /*
         Utilização do try-with-resources para garantir o fechamento automático dos recursos (Connection e PreparedStatement)
         O método ConnectionFactory.getConnection() é responsável por obter uma conexão com o banco de dados
         O PreparedStatement é criado para executar a query SQL com segurança, prevenindo SQL Injection
         A constante Statement.RETURN_GENERATED_KEYS é usada para recuperar as chaves primárias geradas automaticamente pelo banco de dados
         */
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            /*
            Configuração dos parâmetros da query SQL utilizando o PreparedStatement
            Substitui os placeholders (?) na query com os valores do objeto usuario
            */
            stmt.setString(1, usuario.getNome());//o numeros são relacionados aos parametros que demos na query slq, ex:(1,?,?)
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            // Executa a instrução SQL para inserir os dados no banco de dados
            stmt.executeUpdate();

            // Recupera a chave primária gerada automaticamente (ID) do usuário recém-inserido
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));// Atualiza o ID do objeto usuario com o valor gerado pelo banco
                }
            }

            //exebindo erro.
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar usuário: " + e.getMessage());
        }
    }

    /*
    Método para listar todos os usuários do banco de dados
    Utiliza um ArrayList para armazenar objetos da classe Usuario
    */
    public ArrayList<Usuario> findAllUserDao() {
        // Query SQL para selecionar todos os registros da tabela "Usuario"
        String sql = "SELECT * FROM Usuario";

        // Criação de um ArrayList para armazenar os usuários retornados pela consulta
        ArrayList<Usuario> usuarios = new ArrayList<>();

        // Bloco try-with-resources para gerenciar a conexão, o PreparedStatement e o ResultSet
        try (Connection conn = ConnectionFactory.getConnection(); // Obtém a conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql); // Prepara a consulta SQL
                ResultSet rs = stmt.executeQuery()) { // Executa a consulta e retorna os resultados

            // Iteração sobre o ResultSet para transformar os dados em objetos Usuario
            while (rs.next()) {
                // Cria um novo objeto Usuario e preenche seus atributos com os dados do ResultSet
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));       // Atribui o ID do usuário
                usuario.setNome(rs.getString("nome"));// Atribui o nome do usuário
                usuario.setEmail(rs.getString("email")); // Atribui o e-mail do usuário
                usuario.setSenha(rs.getString("senha")); // Atribui a senha do usuário

                // Adiciona o objeto Usuario à lista de usuários
                usuarios.add(usuario);
            }

        } catch (Exception e) {
            // Tratamento de exceção, caso ocorra algum erro durante a execução da consulta
            JOptionPane.showMessageDialog(null,"Erro ao listar usuários: " + e.getMessage());
        }

        // Retorna a lista de usuários obtida do banco de dados
        return usuarios;
    }

   // Método para atualizar um usuário no banco de dados
    public void updateUserDao(Usuario usuario) {
        // Query SQL para atualizar os dados do usuário com base no ID
        String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); // Obtém a conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a consulta SQL

            // Configura os valores dos placeholders na query SQL
            stmt.setString(1, usuario.getNome());  // Define o novo nome
            stmt.setString(2, usuario.getEmail()); // Define o novo e-mail
            stmt.setString(3, usuario.getSenha()); // Define a nova senha
            stmt.setInt(4, usuario.getId());       // Define o ID do usuário a ser atualizado

            // Executa a atualização no banco de dados
            stmt.executeUpdate();

        } catch (Exception e) {
            // Exibe uma mensagem de erro ao usuário em caso de falha
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o usuário!");
        }
    }

// Método para deletar um usuário do banco de dados
    public void deleteUserDao(int id) {
        // Primeiro, remover as tarefas associadas ao usuário
        TarefaDAO tarefaDAO = new TarefaDAO(); // Instancia o DAO de tarefas
        tarefaDAO.deleteByUsuarioIdTarefaDao(id); // Remove as tarefas relacionadas ao ID do usuário

        // Query SQL para deletar o usuário com base no ID
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); // Obtém a conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a query SQL

            stmt.setInt(1, id); // Define o ID do usuário a ser deletado

            // Verifica se o usuário foi deletado (caso o ID não exista, não será deletado)
            if (stmt.executeUpdate() == 0) {
                System.err.println("Erro: Nenhum usuário foi encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            // Exibe mensagem de erro no console em caso de falha
            JOptionPane.showMessageDialog(null,"Erro ao deletar usuário: " + e.getMessage());
        }
    }

// Método para verificar se um usuário existe pelo ID
    public boolean userExistsDAO(int id) {
        // Query SQL para contar os registros com o ID fornecido
        String sql = "SELECT COUNT(*) FROM Usuario WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); // Conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a query SQL

            stmt.setInt(1, id); // Define o ID para a verificação
            ResultSet rs = stmt.executeQuery(); // Executa a consulta e obtém os resultados

            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se houver registros
            }
        } catch (SQLException e) {
            // Exibe mensagem de erro no console em caso de falha
            System.err.println("Erro ao verificar usuário: " + e.getMessage());
        }

        return false; // Retorna false se o ID não for encontrado
    }

// Método para verificar se um e-mail já existe no banco de dados
    public boolean emailExists(String email) {
        // Query SQL para contar os registros com o e-mail fornecido
        String sql = "SELECT COUNT(*) FROM Usuario WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection(); // Conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a query SQL

            stmt.setString(1, email); // Define o e-mail para a verificação
            ResultSet rs = stmt.executeQuery(); // Executa a consulta e obtém os resultados

            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se houver registros
            }
        } catch (SQLException e) {
            // Exibe mensagem de erro no console em caso de falha
            JOptionPane.showMessageDialog(null,"Erro ao verificar e-mail: " + e.getMessage());
        }

        return false; // Retorna false se o e-mail não for encontrado
    }

// Método para verificar se um e-mail já existe, exceto para um usuário específico
    public boolean emailExists(String email, int userId) {
        // Query SQL para contar os registros com o e-mail fornecido, excluindo um usuário específico
        String sql = "SELECT COUNT(*) FROM Usuario WHERE email = ? AND id != ?";

        try (Connection conn = ConnectionFactory.getConnection(); // Conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a query SQL

            stmt.setString(1, email); // Define o e-mail para a verificação
            stmt.setInt(2, userId);   // Define o ID do usuário que deve ser excluído da verificação
            ResultSet rs = stmt.executeQuery(); // Executa a consulta e obtém os resultados

            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se houver registros
            }
        } catch (SQLException e) {
            // Exibe mensagem de erro no console em caso de falha
            JOptionPane.showMessageDialog(null,"Erro ao verificar e-mail: " + e.getMessage());
        }

        return false; // Retorna false se o e-mail não for encontrado
    }
}
