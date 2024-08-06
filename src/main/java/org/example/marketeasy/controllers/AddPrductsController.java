package org.example.marketeasy.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.marketeasy.IDBConfig.Database;
import org.example.marketeasy.models.Articles;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddPrductsController implements Initializable {
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

    @FXML
    private ComboBox<String> category;

    @FXML
    private Label cat_id;

    Articles articles = new Articles();

    Alert alert;

    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    Connection connection = Database.shop_connectDB();

    public void addProducts() throws SQLException {

        articles.setNomDuProduit(productName.getText());
        articles.setPrix(productPrice.getText());
        articles.setDate(String.valueOf(productDate.getValue()));
        articles.setSeuilDAlerte(productAlert.getText());

        String req = "SELECT id FROM categories WHERE nom = ?";
        String categoryId = "";

        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, category.getValue());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                categoryId = resultSet.getString("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String sql = "INSERT INTO produits (nom, prix, quantite, frequence, seuil, date, categories_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

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
                    preparedStatement.setString(7, categoryId);

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

    public void close() throws SQLException {
        connection.close();
        Stage stg = (Stage) addPrpduitButton.getScene().getWindow();
        stg.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String sql = "SELECT nom FROM categories";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                category.getItems().add(resultSet.getString("nom"));
            }

//            category.valueProperty().addListener((observable, oldValue, newValue) -> {
//                try {
//                    preparedStatement = connection.prepareStatement(sql2);
//                    preparedStatement.setString(1, category.getValue());
//                    if (resultSet.next()) {
//                        cat_id.setText("Vous avez sélectionné : " + resultSet.getString("nom"));
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
