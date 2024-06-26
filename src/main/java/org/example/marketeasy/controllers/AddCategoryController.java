package org.example.marketeasy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.marketeasy.IDBConfig.Database;
import org.example.marketeasy.models.Categories;

import java.sql.*;

public class AddCategoryController {

    @FXML
    private Button addCategorieButton;

    @FXML
    private Button annulerCategorieButton;

    @FXML
    private DatePicker categoryDate;

    @FXML
    private TextArea categoryDescription;

    @FXML
    private TextField categoryName;

    @FXML
    private AnchorPane main_form;

    Categories categories = new Categories();

    Alert alert;

    public void addCategory() {

        categories.setNomCategorie(categoryName.getText());
        categories.setDescription(categoryDescription.getText());
        categories.setDate(String.valueOf(categoryDate.getValue()));

        String sql = "INSERT INTO categories (nom, description, date_d_ajout) VALUES (?, ?, ?)";
        Connection connection = Database.shop_connectDB();

        try {

            if (categoryName.getText().isEmpty() || categoryDate.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir tous les champs");
                alert.showAndWait();

            } else {

                String check = "SELECT nom FROM categories WHERE nom = '" + categoryName.getText() +
                        "'";

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(check);

                if (resultSet.next()) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Le produits " + categoryName.getText() + " existe déjà!");
                    alert.showAndWait();

                } else {

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Catégorie ajouté avec succès");
                    alert.showAndWait();


                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, categories.getNomCategorie());
                    preparedStatement.setString(2, categories.getDescription());
                    preparedStatement.setString(3, categories.getDate());

                    preparedStatement.executeUpdate();

                    categoryDescription.setText("");
                    categoryName.setText("");
                    categoryDate.setValue(null);

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void close() {
        Stage stg = (Stage) addCategorieButton.getScene().getWindow();
        stg.close();
    }

}
