package persistence.connection;

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
            throw new RuntimeException(e);
        }
    }
}
