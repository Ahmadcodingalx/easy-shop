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

    //instantiation de la classe Categories (un model) par l'objet "categories"
    Categories categories = new Categories();

    Alert alert;

    //Methode pour ajouter une catégorie
    public void addCategory() throws SQLException {

        //pour envoyer des données aux setters de la classe "Categories"
        categories.setNomCategorie(categoryName.getText());
        categories.setDescription(categoryDescription.getText());
        categories.setDate(String.valueOf(categoryDate.getValue()));

        //requête pour l'insertion dans la bes de donnée
        String sql = "INSERT INTO categories (nom, description, date_d_ajout) VALUES (?, ?, ?)";

        //appel de la methode de connexion à la base de donnée
        Connection connection = Database.shop_connectDB();

        try {

            //pour vérifier si la personne à remplie touts les champs du formulaire
            if (categoryName.getText().isEmpty() || categoryDate.getValue() == null) {

                // bloc permettant d'afficher un alerte
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir tous les champs");
                alert.showAndWait();

            } else {

                // requette permettant de vérifer si la catégorie exist déjà...
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

                    // pour vider les textfields
                    categoryDescription.setText("");
                    categoryName.setText("");
                    categoryDate.setValue(null);

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        connection.close();

    }

    // methode permetant de fermer l'écran
    public void close() {
        Stage stg = (Stage) addCategorieButton.getScene().getWindow();
        stg.close();
    }

}
