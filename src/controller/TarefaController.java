package controller;

import dao.CategoriaDAO;
import dao.TarefaDAO;
import model.Tarefa;
import model.Categoria;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaController {

    private TarefaDAO tarefaDAO = new TarefaDAO(); // Serviço para gerenciar tarefas
    private CategoriaDAO categoriaDAO = new CategoriaDAO(); // Serviço para gerenciar categorias

    // Método para selecionar uma categoria a partir de uma lista
    private int selectCategoria() {
        List<Categoria> categorias = findCategorias(); // Obtém a lista de categorias
        StringBuilder categoriasList = new StringBuilder("Categorias disponíveis:\n");
        for (Categoria categoria : categorias) {
            categoriasList
                    .append(categoria.getId())
                            .append(" - ")
                                .append(categoria.getNome())
                                    .append("\n"); // Concatena as categorias
        }
        String input = JOptionPane.showInputDialog(categoriasList.toString() + "Digite o ID da categoria:"); // Solicita o ID da categoria
        return Integer.parseInt(input); // Retorna o ID da categoria selecionada
    }

    // Método para buscar todas as categorias
    public List<Categoria> findCategorias() {
        return categoriaDAO.findAllCategoriaDao(); // Retorna a lista de categorias
    }

    // Método para cadastrar uma nova tarefa
    public void saveTarefa() {
        boolean verificado = false; // Controla o loop até que a tarefa seja salva com sucesso
        while (!verificado) {
            try {
                // Coleta as informações da tarefa por meio de caixas de diálogo
                String titulo = JOptionPane.showInputDialog("Digite o título da tarefa:"); // Solicita o título
                String descricao = JOptionPane.showInputDialog("Digite a descrição da tarefa:"); // Solicita a descrição
                int status = Integer.parseInt(JOptionPane.showInputDialog("Digite o status da tarefa (1 - Concluída, 0 - Pendente):")); // Solicita o status

                int categoriaId = selectCategoria(); // Seleciona a categoria
                int usuarioId = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do usuário:")); // Solicita o ID do usuário

                // Cria o objeto tarefa com os dados coletados
                Tarefa tarefa = new Tarefa(titulo, descricao, status, usuarioId, categoriaId);
                
                saveTarefaVerificated(tarefa); // Salva a tarefa
                JOptionPane.showMessageDialog(null, "Tarefa cadastrada com sucesso!"); // Mensagem de sucesso
                verificado = true; // Sai do loop após salvar
            } catch (Exception e) {
                // Exibe mensagem de erro em caso de exceção
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar tarefa: " + e.getMessage());
            }
        }
    }

    // Método para verificar se os dados da tarefa são válidos
    public boolean verificationSaveTarefa(Tarefa tarefa) {
        // Verifica se o ID do usuário é válido
        if (tarefa.getUsuarioId() <= 0) {
            JOptionPane.showMessageDialog(null, "Erro: ID do usuário inválido.");
            return false;
        }

        // Verifica se o ID da categoria é válido
        if (tarefa.getCategoriaId() <= 0) {
            JOptionPane.showMessageDialog(null, "Erro: ID da categoria inválido.");
            return false;
        }

        // Verifica se o título da tarefa não está vazio
        if (tarefa.getTitulo() == null || tarefa.getTitulo().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O título da tarefa não pode ser vazio.");
            return false;
        }

        // Verifica se a descrição da tarefa não está vazia
        if (tarefa.getDescricao() == null || tarefa.getDescricao().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: A descrição da tarefa não pode ser vazia.");
            return false;
        }

        // Verifica se o status da tarefa é válido (0 ou 1)
        if (tarefa.getStatus() < 0 || tarefa.getStatus() > 1) {
            JOptionPane.showMessageDialog(null, "Erro: Status inválido. Use 0 para pendente e 1 para concluída.");
            return false;
        }

        return true; // Se todas as validações passaram
    }

    // Método para salvar a tarefa após validação
    public void saveTarefaVerificated(Tarefa tarefa) {
        if (verificationSaveTarefa(tarefa)) {
            tarefaDAO.saveTarefaDao(tarefa); // Salva a tarefa no DAO
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível salvar a tarefa devido a erros de validação.");
        }
    }

    // Método para listar todas as tarefas cadastradas
    public void findAllTarefas() {
        List<Tarefa> tarefas = addFindAllTarefas(); // Obtém a lista de tarefas
        StringBuilder tarefasList = new StringBuilder("Tarefas cadastradas:\n");
        for (Tarefa tarefa : tarefas) {
            tarefasList.append(tarefa).append("\n"); // Concatena cada tarefa na lista
        }
        JOptionPane.showMessageDialog(null, tarefasList.toString()); // Exibe a lista para o usuário
    }

    // Método auxiliar para adicionar todas as tarefas
    public ArrayList<Tarefa> addFindAllTarefas() {
        return (ArrayList<Tarefa>) tarefaDAO.findAllTarefaDao(); // Retorna a lista de tarefas
    }

    // Método para atualizar uma tarefa existente
    public void updateTarefa() {
        boolean verificado = false; // Controla o loop até que a atualização seja bem-sucedida
        while (!verificado) {
            try {
                // Solicita o ID e os novos dados da tarefa
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da tarefa a ser atualizada:")); // Solicita o ID
                String titulo = JOptionPane.showInputDialog("Digite o novo título da tarefa:"); // Solicita o novo título
                String descricao = JOptionPane.showInputDialog("Digite a nova descrição da tarefa:"); // Solicita a nova descrição
                int status = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo status da tarefa (1 - Concluído, 0 - Não Concluído):")); // Solicita o novo status
                int usuarioId = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ID do usuário:")); // Solicita o novo ID do usuário
                int categoriaId = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ID da categoria:")); // Solicita o novo ID da categoria

                // Cria o objeto tarefa com os novos dados
                Tarefa tarefa = new Tarefa(id, titulo, descricao, status, usuarioId, categoriaId); 
                
                updateTarefaVerificated(tarefa); // Atualiza a tarefa
                JOptionPane.showMessageDialog(null, "Tarefa atualizada com sucesso!"); // Mensagem de sucesso
                verificado = true; // Sai do loop após atualizar
            } catch (Exception e) {
                // Exibe mensagem de erro em caso de exceção
                JOptionPane.showMessageDialog(null, "Erro ao atualizar tarefa: " + e.getMessage());
            }
        }
    }

    // Método para verificar se os dados da tarefa para atualização são válidos
    public boolean verificationUpdateTarefa(Tarefa tarefa) {
        // Verifica se o ID da tarefa é válido
        if (tarefa.getId() <= 0) {
            JOptionPane.showMessageDialog(null, "Erro: ID inválido para atualização.");
            return false;
        }

        // Reutiliza a validação de salvar para verificar os dados
        return verificationSaveTarefa(tarefa);
    }

    // Método para atualizar a tarefa após validação
    public void updateTarefaVerificated(Tarefa tarefa) {
        if (verificationUpdateTarefa(tarefa)) {
            tarefaDAO.updateTarefaDAO(tarefa); // Atualiza a tarefa no DAO
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível atualizar a tarefa devido a erros de validação.");
        }
    }

    // Método para filtrar tarefas por status
    public void findAllStatusTarefas() {
        int status = Integer.parseInt(JOptionPane.showInputDialog("Digite o status da tarefa para filtrar (1 - Concluído, 0 - Não Concluído):")); // Solicita o status
        List<Tarefa> tarefas = findAllTarefas(status); // Obtém a lista de tarefas filtradas
        StringBuilder tarefasList = new StringBuilder("Tarefas filtradas:\n");
        for (Tarefa tarefa : tarefas) {
            tarefasList.append(tarefa).append("\n"); // Concatena cada tarefa filtrada na lista
        }
        JOptionPane.showMessageDialog(null, tarefasList.toString()); // Exibe a lista filtrada para o usuário
    }

    // Método auxiliar para buscar todas as tarefas por status
    public ArrayList<Tarefa> findAllTarefas(int status) {
        return (ArrayList<Tarefa>) tarefaDAO.findByStatusTarefaDao(status); // Retorna a lista de tarefas filtradas
    }

    // Método para ordenar tarefas por status
    public void orderByStatsTarefas() {
        List<Tarefa> tarefas = ordenarTarefas(); // Obtém a lista de tarefas ordenadas
        StringBuilder tarefasList = new StringBuilder("Tarefas ordenadas:\n");
        for (Tarefa tarefa : tarefas) {
            tarefasList.append(tarefa).append("\n"); // Concatena cada tarefa ordenada na lista
        }
        JOptionPane.showMessageDialog(null, tarefasList.toString()); // Exibe a lista ordenada para o usuário
    }

    // Método auxiliar para ordenar tarefas
    public ArrayList<Tarefa> ordenarTarefas() {
        return (ArrayList<Tarefa>) tarefaDAO.findAllOrderedByStatusTarefaDao(); // Retorna a lista de tarefas ordenadas
    }

    // Método para exibir um relatório com o total de tarefas
    public void displayReportTarefas() {
        int totalTarefas = exibirRelatorioTarefas(); // Obtém o total de tarefas
        JOptionPane.showMessageDialog(null, "Total de tarefas: " + totalTarefas); // Exibe o total de tarefas
    }

    // Método auxiliar para contar o total de tarefas
    public int exibirRelatorioTarefas() {
        return tarefaDAO.countAllTarefaDao(); // Retorna o total de tarefas
    }
}