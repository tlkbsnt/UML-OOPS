package fr.epita.quiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public TextField admin_number;
    public TextField name_TextField;
    public TextField email_TextField;
    public PasswordField password_TextField;
    public TableView<Admin> tableView;
    public TableColumn<Admin, Integer> adminSnColumn;
    public TableColumn<Admin,  String> adminNameColumn;
    public TableColumn<Admin,String> adminEmailColumn;
    public TableColumn<Admin, String> adminPasswordColumn;
    public ObservableList<Admin> adminList;
    public int index = -1;
    public void onCreateAdminBtn() throws SQLException {
            Admin admin;
        admin = new Admin(Integer.parseInt(admin_number.getText()), name_TextField.getText(), email_TextField.getText(), password_TextField.getText());
        PreparedStatement preparedStatement = null;
            try {
                Connection connection = DBConnection.getConnector();
                String sql = "INSERT INTO ADMIN(an,name,email,password) VALUES(?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,admin.getAdminNo());
                preparedStatement.setString(2, admin.getFullName());
                preparedStatement.setString(3, admin.getEmailId());
                preparedStatement.setString(4,admin.getPassword());
                preparedStatement.execute();
                connection.close();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Data Added");
                alert.show();

            } catch (SQLException e) {
                name_TextField.setText(String.valueOf(e));
            } finally {
                Objects.requireNonNull(preparedStatement).close();
            }
        }

        public void onUpdateAdminBtn() {
        }
        public void onPlayQuizBtn(ActionEvent actionEvent) throws IOException {
            Stage stageLogin = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Entry-view.fxml")));
            Scene scene = new Scene(root);
            stageLogin.setScene(scene);
            stageLogin.show();
            Stage stage = (Stage)this.name_TextField.getScene().getWindow();
            stage.close();
        }
    void getSelected(){
        index = tableView.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        tableView.getItems().remove(index);
        admin_number.setText(String.valueOf(adminSnColumn.getCellData(index)));
        name_TextField.setText(adminNameColumn.getCellData(index));
        email_TextField.setText(adminEmailColumn.getCellData(index));
        password_TextField.setText(adminPasswordColumn.getCellData(index));
    }
    public void loadDataAdminBtn() {
            String query = "Select an, name, email, password from admin";
            try{
                Connection conn = DBConnection.getConnector();
                this.adminList = FXCollections.observableArrayList();
                ResultSet resultSet = conn.createStatement().executeQuery(query);
                while (resultSet.next())
                {
                    adminList.add(new Admin(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                            ));
                    this.adminSnColumn.setCellValueFactory(new PropertyValueFactory<Admin,Integer>("an"));
                    this.adminNameColumn.setCellValueFactory(new PropertyValueFactory<Admin, String>("name"));
                    this.adminEmailColumn.setCellValueFactory(new PropertyValueFactory<Admin, String>("email"));
                    this.adminPasswordColumn.setCellValueFactory(new PropertyValueFactory<Admin, String>("password"));
                    this.tableView.setItems(this.adminList);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public void onDeleteAdminBtn(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataAdminBtn();
    }
}


