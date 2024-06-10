package org.example.marketeasy.controllers;

import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.marketeasy.HelloApplication;
import org.example.marketeasy.IDBConfig.Database;
import org.example.marketeasy.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class CreationDeCompteController {

    @FXML
    private TextField creationNom;

    @FXML
    private TextField creationPassword;

    @FXML
    private TextField creationPrenom;

    @FXML
    private TextField creationQuartier;

    @FXML
    private TextArea creationQuestion;

    @FXML
    private TextArea creationReponse;

    @FXML
    private TextField creationTel;

    @FXML
    private TextField creationUsername;

    @FXML
    private Button signinButton;

    User user = new User();

    PreparedStatement prepStat;
    Connection connection;

    //    Fermer la fenêtred
    public void close() {
        System.exit(0);
    }


    //    cacher la fenêtred
    public void hide() {
        Stage stg = (Stage) signinButton.getScene().getWindow();
        stg.setIconified(true);
    }

    public void creerUnCompte() {

        user.setNom(creationNom.getText());
        user.setPrenom(creationPrenom.getText());
        user.setTel(creationTel.getText());
        user.setUsername(creationUsername.getText());
        user.setPassword(creationPassword.getText());
        user.setQuartier(creationQuartier.getText());
        user.setQuestion(creationQuestion.getText());
        user.setAnswers(creationReponse.getText());

        String sql = "INSERT INTO shop_user (user_name, password, name, question, answer, prenom, quartier, tel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        connection = (Connection) Database.shop_connectDB();

        try {
            Alert alert;

            assert connection != null;
            prepStat = connection.prepareStatement(sql);

            prepStat.setString(1, user.getUsername());
            prepStat.setString(2, user.getPassword());
            prepStat.setString(3, user.getNom());
            prepStat.setString(4, user.getQuestion());
            prepStat.setString(5, user.getAnswers());
            prepStat.setString(6, user.getPrenom());
            prepStat.setString(7, user.getQuartier());
            prepStat.setString(8, user.getTel());

            int row = prepStat.executeUpdate();

            if (row == 0) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("La création de compte n'a pas aboutie, vérifier les information saisies!");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message d'information");
                alert.setHeaderText(null);
                alert.setContentText("Compte créer avec succès");
                alert.showAndWait();

//                pour cacher l'écran
                signinButton.getScene().getWindow().hide();

//                pour fermer l'écran
                Stage stg = (Stage) signinButton.getScene().getWindow();
                stg.close();

                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("connection.fxml")));
                Stage stage = new Stage();

                stage.initStyle(StageStyle.TRANSPARENT);

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
