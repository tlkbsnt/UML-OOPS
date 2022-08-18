package fr.epita.quiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class QuestionController implements Initializable {
    public TableView<Questions> tableView;
    public  TableColumn<Questions, String> question_number_Column;
    public TableColumn<Questions, String>  question_Column;
    @FXML
    public TextField question_numberTextField,answer_TextField,answer_optionTextField1, answer_optionTextField2,answer_optionTextField3;
    public TextArea question_TextArea;
    public Button delete_questionBtn,delete_questionBtn1;

    public ObservableList<Questions> questionsList;
    int index =-1;

    public void createQuestionBtn() throws SQLException {
        Questions questions = new Questions(
                question_numberTextField.getText(),
                question_TextArea.getText(),
                answer_TextField.getText(),
                answer_optionTextField1.getText(),
                answer_optionTextField2.getText(),
                answer_optionTextField3.getText()
        );
        Connection conn = DBConnection.getConnector();
        String sql = "INSERT INTO QUESTION(qn,Question,Answer,answerOption1, answerOption2, answerOption3) VALUES(?,?,?,?,?,?)";

        try {

            if (question_numberTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Enter New Question number or Question Before click Delete button");
                alert.show();
            }
            else {

                    PreparedStatement preparedStatement;
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setInt(1, questions.getQn());
                    preparedStatement.setString(2, questions.getQuestion());
                    preparedStatement.setString(3, questions.getAnswer());
                    preparedStatement.setString(4, questions.getAnsweroption1());
                    preparedStatement.setString(5, questions.getAnsweroption2());
                    preparedStatement.setString(6, questions.getAnsweroption3());
                    preparedStatement.execute();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Data Loaded Successfully!");
                    alert.show();
                }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.valueOf(String.valueOf(e)));
            alert.setContentText("SQL Error");
            alert.show();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.valueOf(String.valueOf(e)));
            alert.setContentText("Database Number Problem");
            alert.show();
        }
    }
    void getSelected(){
       index = tableView.getSelectionModel().getSelectedIndex();
       if(index <= -1){
           return;
       }
       question_numberTextField.setText(question_number_Column.getCellData(index));
       question_TextArea.setText(question_Column.getCellData(index));
       answer_TextField.setText(answer_TextField.getSelectedText());
       answer_optionTextField1.setText(answer_optionTextField1.getSelectedText());
       answer_optionTextField2.setText(answer_optionTextField2.getText());
       answer_optionTextField3.setText(answer_optionTextField3.getText());
    }
    public void onUpdateQuestionBtn() {
    }
    public void onDeleteQuestionBtn() {
        String query = "Delete from Question where qn = ?";
        Connection connection = DBConnection.getConnector();

            try {

                if (question_numberTextField.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Enter the Question number Before click Delete button");
                    alert.show();
                }
                else
                {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, Integer.parseInt(question_numberTextField.getText()));
                    preparedStatement.execute();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Data deleted");
                    alert.show();
                }

            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.valueOf(String.valueOf(e)));
                alert.setContentText("SQL Error");
                alert.show();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.valueOf(String.valueOf(e)));
                alert.setContentText("Database Number Problem");
                alert.show();
        }
    }
    public void onClearBtn(){
        question_numberTextField.setText("");
        question_TextArea.setText("");
        answer_TextField.setText("");
        answer_optionTextField1.setText("");
        answer_optionTextField2.setText("");
        answer_optionTextField3.setText("");
    }
    public void go_to_Quiz(ActionEvent actionEvent) throws IOException {
        startNext();
        Stage stage = (Stage)this.answer_TextField.getScene().getWindow();
        stage.close();
    }
    public void startNext() throws IOException {
        Stage stageLogin = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Entry-view.fxml"));
        Scene scene = new Scene(root);
        stageLogin.setScene(scene);
        stageLogin.show();
    }
    public void loadDataBtn() {

        String query = "Select qn,question from question";
        try{
            Connection conn = DBConnection.getConnector();
            this.questionsList = FXCollections.observableArrayList();
            ResultSet resultSet = conn.createStatement().executeQuery(query);
        while (resultSet.next())
        {
            this.questionsList.add(new Questions(resultSet.getInt(1),resultSet.getString(2)));
            this.question_number_Column.setCellValueFactory(new PropertyValueFactory<Questions,String>("Qn"));
            this.question_Column.setCellValueFactory(new PropertyValueFactory<Questions, String>("question"));
            this.tableView.setItems(null);
            this.tableView.setItems(this.questionsList);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataBtn();
        getSelected();
    }
}
