package fr.epita.quiz;

import java.sql.*;

class DBConnection {
    public static Connection connection;

    static String url = "jdbc:postgresql://localhost:5432/Quiz";
    static String user = "postgres";
    static String pass = "postgres";

    public static Connection getConnector() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


}
