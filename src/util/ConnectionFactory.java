//Está sera nossa classe responsavel pela conexão coom nosso banco.
package util;//Aqui informa o pacote pertencente.

//bibliotecas que manipulam a conexão com o banco(pesquisar documentação).
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Aqui iniciamos nossa conexão informando os parametros do nosso banco.
public class ConnectionFactory {
    //URL do banco padrão mysqli do xampp + nome do banco.
    private static final String URL = "jdbc:mysql://localhost:3306/todo_list";
    private static final String USUARIO = "root"; //login do banco.
    private static final String SENHA = "";//senha do banco.

    public static Connection getConnection() {
        try {
            //Aqui ele vai tentar conectar no banco usando o DriveManeger usando
            //os parametros que foram fornecidos por nós.
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            //Se der errado ele vai exibir uma mensagem generica de erro.
            throw new RuntimeException(e);
        }
    }
}