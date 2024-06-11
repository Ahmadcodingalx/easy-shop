package org.example.marketeasy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.marketeasy.IDBConfig.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UpdateProductController {

    @FXML
    private Button addPrpduitButton;

    @FXML
    private ComboBox<?> prodChoice;

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

    public void updateProd(){

        String updateReq = "UPDATE produits SET nom = '" +
                productName.getText() + "', prix = '" +
                productPrice.getText() + "', seuil = '" +
                productAlert.getText() + "', date = '" +
                productDate.getValue() + "' WHERE nom = '" +
                prodChoice.getValue() + "'";

        Connection connection = Database.shop_connectDB();

        try {
            Alert alert;

            if (productName.getText().isEmpty() ||
                    productPrice.getText().isEmpty() ||
                    productAlert.getText().isEmpty() ||
                    productDate.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir tout les champs");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment modifier le produit " + productName.getText() + " ?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    Statement statement = connection.createStatement();
                    statement.executeUpdate(updateReq);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Produit modifer avec succès!");
                    alert.showAndWait();

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
