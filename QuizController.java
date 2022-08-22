package fr.epita.quiz;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
public class QuizController implements Initializable {
    public TextArea questionTextArea;
    public CheckBox firstCheckBox;
    public CheckBox secondCheckBox;
    public CheckBox thirdCheckBox;
    public CheckBox forthCheckBox;
    public TableView<Score> quizTableView;
    public TableColumn<Score, String> nameColumn;
    public TableColumn<Score, String> scoreColumn;
    public Label labelQuestionNumber;
    public Button nextQuestionBtn;

    Connection connection = DBConnection.getConnector();
    Statement statement;
    ResultSet resultSet;
    public ObservableList<Quiz> quizObservableList;
    int x = 0;
    ArrayList<String> questionNumberList = new ArrayList<>();
    ArrayList<String> questionList = new ArrayList<>();
    ArrayList<String> answerList = new ArrayList<>();
    ArrayList<String> answerOption1list = new ArrayList<>();
    ArrayList<String> answerOption2list = new ArrayList<>();
    ArrayList<String> answerOption3list = new ArrayList<>();
    public void  fetchQuestion() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from question");

            while (resultSet.next()) {
                //resultSet.getString("sn")+ " " +
                String questionNumber = resultSet.getString("qn");
                String question = resultSet.getString("question");
                String answer = resultSet.getString("answer");
                String answerOption1 = resultSet.getString("answerOption1");
                String answerOption2 = resultSet.getString("answerOption2");
                String answerOption3 = resultSet.getString("answerOption3");
                // Below values are add in the Arraylist with corresponding above defined ArrayList
                questionNumberList.add(String.valueOf(questionNumber));
                questionList.add(question);
                answerList.add(answer);
                answerOption1list.add(answerOption1);
                answerOption2list.add(answerOption2);
                answerOption3list.add(answerOption3);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // From here value of the CheckBox generate  randomly which set random value in Checkbox
        ArrayList<String> setCheckboxRandom = new ArrayList<>();
        setCheckboxRandom.add(answerList.get(x));
        setCheckboxRandom.add(answerOption1list.get(x));
        setCheckboxRandom.add(answerOption2list.get(x));
        setCheckboxRandom.add(answerOption3list.get(x));

        int []indic = new int[setCheckboxRandom.size()];
        Random rnd = new Random();

        for(int i=0; i<indic.length; i++){
            int j = rnd.nextInt(i+1);
            indic[i] = indic[j];
            indic[j] = i;
        }
        ArrayList listSet = new ArrayList<>();
        for (int i : indic) {
            listSet.add(setCheckboxRandom.get(i));
        }
        // From here End the set of the Random value in CheckBox
                    labelQuestionNumber.setText("Qn : " + questionNumberList.get(x) + " ");
                    questionTextArea.setText(questionList.get(x));
                    firstCheckBox.setText(listSet.get(0).toString());
                    secondCheckBox.setText(listSet.get(1).toString());
                    thirdCheckBox.setText(listSet.get(2).toString());
                    forthCheckBox.setText(listSet.get(3).toString());
        ++x;

    }
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
    public void loadScore() {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
