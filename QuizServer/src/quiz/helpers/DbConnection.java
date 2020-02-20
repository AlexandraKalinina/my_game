package quiz.helpers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static volatile Connection conn = null;
    public static synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        if (conn == null)
            synchronized (DbConnection.class) {
                if (conn == null)
                    conn = DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/Victorina",
                            "postgres",
                            "123"
                    );
            }
        return conn;
    }
}

