module com.example.quiz {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires postgresql;
    requires java.net.http;

    opens fr.epita.quiz to javafx.fxml;
    exports fr.epita.quiz;
}