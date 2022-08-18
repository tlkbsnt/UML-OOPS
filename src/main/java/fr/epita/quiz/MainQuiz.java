package fr.epita.quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainQuiz extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainQuiz.class.getResource("Entry-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 460, 430);
        stage.setTitle("Quiz Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}