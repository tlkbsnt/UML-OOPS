package fr.epita.quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    public TextField loginEmailTextField;
    public TextField loginPasswordTextField;

    public void onLogInBtn(ActionEvent actionEvent) {
        Login login = new Login(loginEmailTextField.getText(), loginPasswordTextField.getText());
        String sql = "SELECT EMAIL, PASSWORD FROM ADMIN WHERE EMAIL = ? AND PASSWORD = ?";
        try {

            Connection conn = DBConnection.getConnector();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, login.getEmail());
            preparedStatement.setString(2, login.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Stage stage = (Stage)this.loginPasswordTextField.getScene().getWindow();
                stage.close();
                start();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Wrong Credential");
                alert.show();
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onSignUpLink(ActionEvent actionEvent) throws IOException {
            Stage stage = (Stage)this.loginPasswordTextField.getScene().getWindow();
            stage.close();
            startNext();
    }
    public void start() throws IOException {
        Stage stageLogin = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("QuestionController.fxml")));
        Scene scene = new Scene(root);
        stageLogin.setScene(scene);
        stageLogin.show();
    }
    public void startNext() throws IOException {
        Stage stageLogin = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AdminController.fxml"));
        Scene scene = new Scene(root);
        stageLogin.setScene(scene);
        stageLogin.show();
    }
}
