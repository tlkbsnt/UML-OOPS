package fr.epita.quiz;

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
    ArrayList<String> questionNumberList = new ArrayList<>();
    ArrayList<String> questionList = new ArrayList<>();
    ArrayList<String> answerList = new ArrayList<>();
    ArrayList<String> answerOption1List = new ArrayList<>();
    ArrayList<String> answerOption2List = new ArrayList<>();
    ArrayList<String> answerOption3List = new ArrayList<>();
    int x = 0;

   ArrayList<String> optionList = new ArrayList<>();
    public void  fetchQuestion() {

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from question");
            while (resultSet.next()) {
                questionNumberList.add(resultSet.getString("qn"));
                questionList.add(resultSet.getString("question"));
                answerList.add(resultSet.getString("answer"));
                answerOption1List.add(resultSet.getString("answerOption1"));
                answerOption2List.add(resultSet.getString("answerOption2"));
                answerOption3List.add(resultSet.getString("answerOption3"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> setCheckboxRandom = new ArrayList<>();
        setCheckboxRandom.add(answerList.get(x));
        setCheckboxRandom.add(answerOption1List.get(x));
        setCheckboxRandom.add(answerOption2List.get(x));
        setCheckboxRandom.add(answerOption3List.get(x));
        // From here value of the CheckBox generate  randomly which set random value in Checkbox
        int []indic = new int[setCheckboxRandom.size()];
        Random rnd = new Random();
        for(int i=0; i<indic.length; i++){
            int j = rnd.nextInt(i+1);
            indic[i] = indic[j];
            indic[j] = i;
        }
        ArrayList<Object> listSet = new ArrayList<>();
        for (int i : indic) {
            listSet.add(setCheckboxRandom.get(i));
        }
        // From here End the set of the Random value in CheckBox
        ArrayList<String> checkBoxOption = new ArrayList<>();
        String first = listSet.get(0).toString();
        String second = listSet.get(1).toString();
        String third = listSet.get(2).toString();
        String fourth = listSet.get(3).toString();
        optionList.clear();

        optionList.add(first);
        optionList.add(second);
        optionList.add(third);
        optionList.add(fourth);


        checkBoxOption.add(first);
        checkBoxOption.add(second);
        checkBoxOption.add(third);
        checkBoxOption.add(fourth);

        questionTextArea.setText(questionList.get(x));
        labelQuestionNumber.setText("Qn : " + questionNumberList.get(x) + " ");
        firstCheckBox.setText(listSet.get(0).toString());
        secondCheckBox.setText(listSet.get(1).toString());
        thirdCheckBox.setText(listSet.get(2).toString());
        forthCheckBox.setText(listSet.get(3).toString());
        ++x;

        
    }
    public void onSubmitBtn() {
        int count = 0;
        if(firstCheckBox.isSelected())
        {
            ++ count;
        }
        if (secondCheckBox.isSelected()){
            ++ count;
        }
        if (thirdCheckBox.isSelected()){
            ++ count;
        }
        if (forthCheckBox.isSelected()){
            ++ count;
        }

        int score = 0;
        if(count == 1){
            if(firstCheckBox.isSelected()){
                if(optionList.get(0).equals(answerList.get(x))){
                    score ++;
                    System.out.println(score);
                }
            }
            if(secondCheckBox.isSelected()) {
                if (optionList.get(1).equals(answerList.get(x))) {
                    score ++;
                    System.out.println(score);
                }
            }
            if(thirdCheckBox.isSelected()) {
                if (optionList.get(2).equals(answerList.get(x))) {
                    score ++;
                    System.out.println(score);
                }
            }
            if(forthCheckBox.isSelected()) {
                if (optionList.get(3).equals(answerList.get(x))) {
                    score ++;
                    System.out.println(score);
                }
            }

        }


    }
    public void onNextQuestionBtn() {
       fetchQuestion();
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

    public void onTableView(SortEvent<TableView<Score>> tableViewSortEvent) {

    }
}
