package controller;

import dao.UsuarioDAO;
import java.awt.HeadlessException;
import model.Usuario;

import javax.swing.*;
import java.util.ArrayList;

//classe onde o usuario vai interagir com o sistema, e os metodos serão exibidos e tratados.
public class UsuarioController {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void saveUsuario() {
        boolean verificado = false; // Controla o loop até que o usuário seja salvo com sucesso
        while (!verificado) {
            try {
                // Coleta as informações do usuário por meio de caixas de diálogo
                String nome = JOptionPane.showInputDialog("Digite o nome do usuário:");
                String email = JOptionPane.showInputDialog("Digite o email do usuário:");
                String senha = JOptionPane.showInputDialog("Digite a senha do usuário:");

                Usuario usuario = new Usuario(nome, email, senha); // Cria o objeto usuário com os dados coletados

                // Salva o usuário se os dados forem válidos
                saveUsuarioVerificated(usuario);
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                verificado = true; // Sai do loop após salvar
            } catch (Exception e) {
                // Mostra mensagem de erro em caso de exceção
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário. Tente novamente.");
            }
        }
    }

    public boolean verificationSaveUsuario(Usuario usuario) {
        // Verifica se o e-mail já existe no banco de dados
        if (usuarioDAO.emailExists(usuario.getEmail())) {
            JOptionPane.showMessageDialog(null, "Erro: O e-mail '" + usuario.getEmail() + "' já está em uso. Por favor, escolha outro.");
            return false;
        }

        // Validações básicas para evitar campos vazios
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O nome do usuário não pode ser vazio.");
            return false;
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O e-mail do usuário não pode ser vazio.");
            return false;
        }

        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: A senha do usuário não pode ser vazia.");
            return false;
        }

        // Retorna true se todas as validações forem aprovadas
        return true;
    }

    public void saveUsuarioVerificated(Usuario usuario) {
        if (verificationSaveUsuario(usuario)) {
            usuarioDAO.saveUserDao(usuario); // Certifique-se de que o método saveUser  está corretamente implementado no DAO
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível salvar o usuário devido a erros de validação.");
        }
    }


    /*
     Aqui serão chama
     */
    public void findAllUsuarios() {
        // Obtém a lista de usuários por meio do método DAO
        ArrayList<Usuario> usuarios = addFindAllUsuarios();

        // Prepara a mensagem com os usuários para exibir ao usuário
        StringBuilder usuariosList = new StringBuilder("Usuários cadastrados:\n");
        for (Usuario usuario : usuarios) {
            usuariosList.append(usuario).append("\n"); // Concatena cada usuário na lista
        }

        // Exibe a lista para o usuário
        JOptionPane.showMessageDialog(null, usuariosList.toString());
    }

    //salva na lista bobão
    public ArrayList<Usuario> addFindAllUsuarios() {
        return usuarioDAO.findAllUserDao();
    }

    public void updateUsuario() {
        boolean verificado = false; // Controla o loop até que a atualização seja bem-sucedida
        while (!verificado) {
            try {
                // Solicita o ID e os novos dados do usuário
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do usuário a ser atualizado:"));
                String nome = JOptionPane.showInputDialog("Digite o novo nome do usuário:");
                String email = JOptionPane.showInputDialog("Digite o novo email do usuário:");
                String senha = JOptionPane.showInputDialog("Digite a nova senha do usuário:");

                Usuario usuario = new Usuario(id, nome, email, senha); // Cria o objeto usuário com os novos dados

                // Atualiza o usuário se os dados forem válidos
                updateUsuarioVerificated(usuario);
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
                verificado = true; // Sai do loop após atualizar
            } catch (HeadlessException | NumberFormatException e) {
                // Mostra mensagem de erro em caso de exceção
                JOptionPane.showMessageDialog(null, "Erro ao Atualizar o usuário!");
            }
        }
    }

    public boolean verificationUpdateUsuario(Usuario usuario) {
        // Verifica se o ID é válido
        if (usuario.getId() <= 0) {
            JOptionPane.showMessageDialog(null, "Erro: ID inválido para atualização.");
            return false;
        }

        // Validação de dados basica...
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O nome do usuário não pode ser vazio.");
            return false;
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O e-mail do usuário não pode ser vazio.");
            return false;
        }

        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: A senha do usuário não pode ser vazia.");
            return false;
        }

        // Verifica se o e-mail já existe, exceto para o próprio usuário
        if (usuarioDAO.emailExists(usuario.getEmail(), usuario.getId())) {
            JOptionPane.showMessageDialog(null, "Erro: O e-mail '" + usuario.getEmail() + "' já está em uso por outro usuário. Por favor, escolha outro.");
            return false;
        }

        return true; // Todas as validações passaram
    }

    public void updateUsuarioVerificated(Usuario usuario) {
        if (verificationUpdateUsuario(usuario)) {
            usuarioDAO.updateUserDao(usuario);
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível atualizar o usuário devido a erros de validação.");
        }
    }

    public void deleteUsuario() {
        boolean verificado = false; // Controla o loop até que a exclusão seja bem-sucedida
        while (!verificado) {
            try {
                // Solicita o ID do usuário a ser excluído
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do usuário a ser excluído:"));
                verificado = verificationDeleteUsuario(id); // Valida o ID e verifica a existência do usuário

                if (verificado) { // Se a verificação foi bem-sucedida
                    JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!"); // Mensagem de sucesso
                }
            } catch (HeadlessException | NumberFormatException e) {
                // Mostra mensagem de erro em caso de exceção
                JOptionPane.showMessageDialog(null, "Erro ao excluir o usuário. Verifique o ID e tente novamente.");
            }
        }
    }

    public boolean verificationDeleteUsuario(int id) {
        // Verifica se o ID é válido
        if (id <= 0) {
            JOptionPane.showMessageDialog(null, "Erro: ID inválido para exclusão.");
            return false;
        }

        // Verifica se o usuário existe antes de tentar deletar
        if (!usuarioDAO.userExistsDAO(id)) { // Certifique-se de que o método userExists está corretamente implementado no DAO
            JOptionPane.showMessageDialog(null, "Erro: Usuário com ID " + id + " não encontrado.");
            return false;
        }

        usuarioDAO.deleteUserDao(id);
        return true; // Retorna true se a exclusão foi bem-sucedida
    }
}
