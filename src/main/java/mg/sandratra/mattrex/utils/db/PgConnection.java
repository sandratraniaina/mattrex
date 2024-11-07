package mg.sandratra.mattrex.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class PgConnection {
    private static String database;
    private static String user;
    private static String password;
    private static String url;

    static {
        try (InputStream input = PgConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find database.properties");
                return;
            }

            // Load properties file
            Properties properties = new Properties();
            properties.load(input);

            // Get properties from the file
            database = properties.getProperty("database");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url") + database; // Append the database to the URL

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Methods
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Register the PostgreSQL driver
        Class.forName("org.postgresql.Driver");

        // Establish the connection using properties loaded from the file
        return DriverManager.getConnection(url, user, password);
    }
}
