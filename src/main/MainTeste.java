package main;

import controller.CategoriaController;
import controller.TarefaController;
import controller.UsuarioController;

import javax.swing.*;

public class MainTeste {

    // Variável global para controlar a execução do programa
    private static boolean isRunning = true;

    public static void main(String[] args) {
        // Instâncias dos controladores (pontos de entrada para gerenciar dados de usuários, categorias e tarefas)
        UsuarioController usuarioController = new UsuarioController();
        CategoriaController categoriaController = new CategoriaController();
        TarefaController tarefaController = new TarefaController();

        // Configuração do JFrame principal, que serve como a janela da aplicação
        JFrame frame = new JFrame("Gerenciamento de Tarefas");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Desabilita o fechamento direto da janela

        // Adiciona um listener para capturar quando o usuário tenta fechar a janela
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                confirmarSaida(frame); // Chama o método para confirmar saída ao tentar fechar
            }
        });

        // Loop principal do programa para exibir o menu inicial e lidar com as opções do usuário
        while (isRunning) {
            // Opções disponíveis no menu principal
            String[] options = {"Gerenciar Usuários", "Gerenciar Categorias", "Gerenciar Tarefas", "Sair"};
            int opcao = JOptionPane.showOptionDialog(frame, "Menu Principal", "Selecione uma opção",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            // Verifica se o usuário fechou a janela do diálogo
            if (opcao == -1) {
                confirmarSaida(frame); // Pergunta se ele realmente quer sair
            } else {
                // Trata as opções do menu principal
                switch (opcao) {
                    case 0:
                        menuUsuarios(usuarioController, frame); // Gerenciar usuários
                        break;
                    case 1:
                        menuCategorias(categoriaController, frame); // Gerenciar categorias
                        break;
                    case 2:
                        menuTarefas(tarefaController, frame); // Gerenciar tarefas
                        break;
                    case 3:
                        confirmarSaida(frame); // Sair do programa
                        break;
                }
            }
        }

        System.exit(0); // Encerra o programa ao sair do loop
    }

    // Método para confirmar a saída do programa
    private static void confirmarSaida(JFrame frame) {
        // Exibe uma caixa de diálogo para perguntar se o usuário deseja sair
        int confirm = JOptionPane.showConfirmDialog(frame, "Você realmente deseja sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) { // Se o usuário clicar em "Sim"
            isRunning = false; // Interrompe o loop principal
        }
    }

    // Menu de opções para gerenciar usuários
    private static void menuUsuarios(UsuarioController usuarioController, JFrame frame) {
        while (true) {
            // Opções disponíveis para o gerenciamento de usuários
            String[] options = {"Cadastrar Usuário", "Listar Usuários", "Atualizar Usuário", "Excluir Usuário", "Voltar"};
            int opcao = JOptionPane.showOptionDialog(frame, "Menu de Usuários", "Selecione uma opção",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            // Verifica se o usuário fechou o menu ou escolheu voltar
            if (opcao == -1 || opcao == 4) {
                return; // Sai do menu de usuários
            }

            // Trata as opções de gerenciamento de usuários
            switch (opcao) {
                case 0:
                    usuarioController.saveUsuario(); // Cadastrar um novo usuário
                    break;
                case 1:
                    usuarioController.findAllUsuarios(); // Listar todos os usuários
                    break;
                case 2:
                    usuarioController.updateUsuario(); // Atualizar informações de um usuário
                    break;
                case 3:
                    usuarioController.deleteUsuario(); // Excluir um usuário
                    break;
            }
        }
    }

    // Menu de opções para gerenciar categorias
    private static void menuCategorias(CategoriaController categoriaController, JFrame frame) {
        while (true) {
            // Opções disponíveis para o gerenciamento de categorias
            String[] options = {"Cadastrar Categoria", "Listar Categorias", "Atualizar Categoria", "Excluir Categoria", "Voltar"};
            int opcao = JOptionPane.showOptionDialog(frame, "Menu de Categorias", "Selecione uma opção",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            // Verifica se o usuário fechou o menu ou escolheu voltar
            if (opcao == -1 || opcao == 4) {
                return; // Sai do menu de categorias
            }

            // Trata as opções de gerenciamento de categorias
            switch (opcao) {
                case 0:
                    categoriaController.saveCategoria(); // Cadastrar uma nova categoria
                    break;
                case 1:
                    categoriaController.findAllCategorias(); // Listar todas as categorias
                    break;
                case 2:
                    categoriaController.updateCategoria(); // Atualizar informações de uma categoria
                    break;
                case 3:
                    categoriaController.deleteCategoria(); // Excluir uma categoria
                    break;
            }
        }
    }

    // Menu de opções para gerenciar tarefas
    private static void menuTarefas(TarefaController tarefaController, JFrame frame) {
        while (true) {
            // Opções disponíveis para o gerenciamento de tarefas
            String[] options = {"Cadastrar Tarefa", "Listar Tarefas", "Atualizar Tarefa", "Filtrar Tarefas", "Ordenar Tarefas", "Exibir Relatório de Tarefas", "Voltar"};
            int opcao = JOptionPane.showOptionDialog(frame, "Menu de Tarefas", "Selecione uma opção",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            // Verifica se o usuário fechou o menu ou escolheu voltar
            if (opcao == -1 || opcao == 6) {
                return; // Sai do menu de tarefas
            }

            // Trata as opções de gerenciamento de tarefas
            switch (opcao) {
                case 0:
                    tarefaController.saveTarefa(); // Cadastrar uma nova tarefa
                    break;
                case 1:
                    tarefaController.findAllTarefas(); // Listar todas as tarefas
                    break;
                case 2:
                    tarefaController.updateTarefa(); // Atualizar informações de uma tarefa
                    break;
                case 3:
                    tarefaController.findAllStatusTarefas(); // Filtrar tarefas por status
                    break;
                case 4:
                    tarefaController.orderByStatsTarefas(); // Ordenar tarefas por critérios
                    break;
                case 5:
                    tarefaController.displayReportTarefas(); // Exibir um relatório de tarefas
                    break;
            }
        }
    }
}
