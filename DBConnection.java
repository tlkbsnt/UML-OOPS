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

    /*
           statement = connection.createStatement();
        resultSet = statement.executeQuery("Select sn from student");

        try{
        sn = Integer.parseInt(userIDTextField.getText());
        // ArrayList is used for the fetch the Sn list from  the student
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(resultSet.getInt("sn")));

        if(list.equals(sn)){
        userIDTextField.setText("Enter Specific Another number");
        }
        } catch (NumberFormatException e) {
        userIDTextField.setText("Enter number only");
        }

        } catch (SQLException e) {
        throw new RuntimeException(e);
        }

     */