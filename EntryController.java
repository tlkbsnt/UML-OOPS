package fr.epita.quiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class EntryController {
    Connection connection = DBConnection.getConnector();
    PreparedStatement preparedStatement = null;
    public TextField  nameTextField;
    public  TextField emailTextField;
    ObservableList<Questions> questionsList = FXCollections.observableArrayList();

    public void start() throws IOException {
        Stage stageLogin = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Quiz-view.fxml")));
        Scene scene = new Scene(root);
        stageLogin.setScene(scene);
        stageLogin.show();
    }
    public void onStartGameBtn() throws SQLException, IOException {
        Entry entry = new Entry(nameTextField.getText(), emailTextField.getText());

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO STUDENT(name ,email) VALUES(?,?)");
            preparedStatement.setString(1,entry.getStudent_name());
            preparedStatement.setString(2,entry.getEmailID());
            preparedStatement.execute();
            connection.close();
            Stage stage = (Stage)this.nameTextField.getScene().getWindow();
            stage.close();
            start();
        } catch (SQLException e) {
            nameTextField.setText(String.valueOf(e));
        }finally {
            Objects.requireNonNull(preparedStatement).close();
        }
    }
    public void onClearBtn() {
        nameTextField.setText("");
        emailTextField.setText("");
    }
    public void onAdminLoginBtn() throws IOException {
        Stage stageLogin = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Scene scene = new Scene(root);
        stageLogin.setScene(scene);
        Stage stage = (Stage)this.nameTextField.getScene().getWindow();
        stage.close();
        stageLogin.show();
    }
}
