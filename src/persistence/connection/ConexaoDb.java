package persistence.connection;

import javafx.scene.control.Alert;
import util.Alerta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDb {

    public static Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:hsqldb:hsql://localhost/instituicaocaridade", "sa", "");
            return conn;
        } catch (SQLException e) {
            Alerta.abrirAlert("Erro", "Erro ao se conectar com o banco de dados.", Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }
}
