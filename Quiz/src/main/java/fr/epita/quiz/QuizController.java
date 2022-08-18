package fr.epita.quiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class QuizController implements Initializable {
    public TextArea questionTextArea;
    public CheckBox firstCheckBox;
    public CheckBox secondCheckBox;
    public CheckBox thirdCheckBox;
    public CheckBox forthCheckBox;
    public TableView<Score> quizTableView;
    public TableColumn<Score, String>  nameColumn;
    public TableColumn<Score, String>  scoreColumn;
    public String studentName;
    public String studentEmail;
    public int quizScore;
    public ObservableList<Score> scoreList;
    public void onSubmitBtn() {
    }
    public void onNextQuestionBtn() {

    }
    public void onQuitBtn() {
        System.exit(0);
    }

    public void onLoginAdmin() throws IOException {




        Stage stageLogin = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AdminController.fxml"));
        Scene scene = new Scene(root);
        stageLogin.setScene(scene);
        stageLogin.show();

        Stage stage = (Stage)this.questionTextArea.getScene().getWindow();
        stage.close();
    }
    public void loadScore(){

            Score score = new Score(null,quizScore);
            String query = "select  Student.name, Score.scores from Score left join Student on Score.Sn = Student.Sn";
            try {
                Connection conn = DBConnection.getConnector();
                this.scoreList = FXCollections.observableArrayList();
                ResultSet resultSet = conn.createStatement().executeQuery(query);
                while (resultSet.next()) {
                    this.scoreList.add(new Score(resultSet.getString(1), resultSet.getInt(2)));
                    this.scoreColumn.setCellValueFactory(new PropertyValueFactory<Score, String>("name"));
                    this.nameColumn.setCellValueFactory(new PropertyValueFactory<Score, String>("scores"));
                    this.quizTableView.setItems(null);
                    this.quizTableView.setItems(this.scoreList);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadScore();

    }
}