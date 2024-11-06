package mg.sandratra.mattrex.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgConnection {
    private static final String database = "mattrex";
    private static final String user = "postgres";
    private static final String password = "aina";
    private static final String url = "jdbc:postgresql://localhost:5432/" + database;

    // Methods
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
