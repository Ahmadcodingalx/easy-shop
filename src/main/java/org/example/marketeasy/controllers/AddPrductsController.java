package org.example.marketeasy.controllers;

import com.mysql.cj.xdevapi.PreparableStatement;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.marketeasy.IDBConfig.Database;
import org.example.marketeasy.models.Articles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddPrductsController {
    @FXML
    private Button addPrpduitButton;

    @FXML
    private Button annulerProduitButton;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextArea productAlert;

    @FXML
    private DatePicker productDate;

    @FXML
    private TextField productName;

    @FXML
    private TextArea productPrice;

    Articles articles = new Articles();

    Alert alert;

    public void addProducts() {

        articles.setNomDuProduit(productName.getText());
        articles.setPrix(productPrice.getText());
        articles.setDate(String.valueOf(productDate.getValue()));
        articles.setSeuilDAlerte(productAlert.getText());

        String sql = "INSERT INTO produits (nom, prix, seuil, date) VALUES (?, ?, ?, ?)";
        Connection connection = Database.shop_connectDB();

        try {


            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, articles.getNomDuProduit());
            preparedStatement.setString(2, articles.getPrix());
            preparedStatement.setString(3, articles.getSeuilDAlerte());
            preparedStatement.setString(4, articles.getDate());

            int row = preparedStatement.executeUpdate();

            if (productName.getText().isEmpty() || productPrice.getText().isEmpty() || productDate.getValue() == null || productAlert.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir tous les champs");
                alert.showAndWait();

            } else if (row == 0) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("L'ajout de produit a échoué");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message d'information");
                alert.setHeaderText(null);
                alert.setContentText("Produit ajouté avec succès");
                alert.showAndWait();

                productName.setText("");
                productAlert.setText("");
                productPrice.setText("");
                productDate.setValue(null);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void close() {
        Stage stg = (Stage) addPrpduitButton.getScene().getWindow();
        stg.close();
    }
}
