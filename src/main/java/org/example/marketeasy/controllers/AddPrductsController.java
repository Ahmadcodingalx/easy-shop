package org.example.marketeasy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.marketeasy.IDBConfig.Database;
import org.example.marketeasy.models.Articles;

import java.sql.*;
import java.time.LocalDate;

public class AddPrductsController {
    @FXML
    private Button addPrpduitButton;

    @FXML
    private Button annulerProduitButton;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField productAlert;

    @FXML
    private DatePicker productDate;

    @FXML
    private TextField productName;

    @FXML
    private TextField productPrice;

    Articles articles = new Articles();

    Alert alert;

    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    public void addProducts() {

        articles.setNomDuProduit(productName.getText());
        articles.setPrix(productPrice.getText());
        articles.setDate(String.valueOf(productDate.getValue()));
        articles.setSeuilDAlerte(productAlert.getText());

        String sql = "INSERT INTO produits (nom, prix, quantite, frequence, seuil, date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = Database.shop_connectDB();

        try {

            if (productName.getText().isEmpty() ||
                    productPrice.getText().isEmpty() ||
                    productDate.getValue() == null ||
                    productAlert.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir tous les champs");
                alert.showAndWait();

            } else {

                String check = "SELECT nom FROM produits WHERE nom = '" + productName.getText() +
                        "'";

                statement = connection.createStatement();
                resultSet = statement.executeQuery(check);

                if (resultSet.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Le produits " + productName.getText() + " existe déjà!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Produit ajouté avec succès");
                    alert.showAndWait();

                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, articles.getNomDuProduit());
                    preparedStatement.setString(2, articles.getPrix());
                    preparedStatement.setString(3, "0");
                    preparedStatement.setString(4, "0");
                    preparedStatement.setString(5, articles.getSeuilDAlerte());
                    preparedStatement.setString(6, articles.getDate());

                    preparedStatement.executeUpdate();

                    productName.setText("");
                    productAlert.setText("");
                    productPrice.setText("");
                    productDate.setValue(null);
                }
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
