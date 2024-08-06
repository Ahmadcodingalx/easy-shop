package org.example.marketeasy.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.marketeasy.HelloApplication;
import org.example.marketeasy.IDBConfig.Database;
import org.example.marketeasy.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private String screenUserName;

    //    Fermer la fenêtred
    public void close() {
        System.exit(0);
    }


    //    cacher la fenêtred
    public void hide() {
        Stage stg = (Stage) loginButton.getScene().getWindow();
        stg.setIconified(true);
    }

    PreparedStatement prepStat;
    Connection connection;
    ResultSet resultSet;

    User user = new User();

    public void connection() throws SQLException {

        user.setUsername(username.getText());

        String sql = "SELECT * FROM shop_user WHERE user_name = ? AND password = ?";
        connection = Database.shop_connectDB();

        try {
            Alert alert;

            prepStat = connection.prepareStatement(sql);

            prepStat.setString(1, username.getText());
            prepStat.setString(2, password.getText());

            resultSet = prepStat.executeQuery();

            if (username.getText().isEmpty() || password.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir tout les champs");
                alert.showAndWait();

            } else if (resultSet.next()) {

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message d'information");
                alert.setHeaderText(null);
                alert.setContentText("Connection réussie!");
                alert.showAndWait();

//                loginButton.getScene().getWindow().hide();

                Stage ecran = (Stage) loginButton.getScene().getWindow();
                ecran.close();

                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("dashboard.fxml")));
                Stage stg = new Stage();

                stg.initStyle(StageStyle.TRANSPARENT);

                Scene scn = new Scene(root);
                stg.setScene(scn);
                stg.show();

//                FileChooser fileChooser = new FileChooser();
//                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
//
//                File file = fileChooser.showOpenDialog(stg);
//
//                if (file != null) {
//                    readeFile(file);
//                }

            } else {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erruer");
                alert.setHeaderText(null);
                alert.setContentText("nom d'utilisateur ou mot de passe incorrect");
                alert.showAndWait();
            }

        } catch (SQLException | IOException e ) {
            throw new RuntimeException(e);
        }
        connection.close();

    }

    public void AdminLoginkey(KeyEvent clique) throws SQLException {

        if (clique.getCode() == KeyCode.SPACE || (clique.getCode() == KeyCode.ENTER)) {
            connection(); //La methode s'execute l'orsqu'on appuie sur la touche ENTRER
        }

    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public String getScreenUserName() {
        return screenUserName;
    }

    public void setScreenUserName(String screenUserName) {
        this.screenUserName = screenUserName;
    }
}
