package org.example.marketeasy.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.marketeasy.IDBConfig.Database;
import org.example.marketeasy.models.Articles;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateProductController implements Initializable {

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

    String id = "";

    public void updateProd(){

        String updateReq = "UPDATE produits SET nom = '" +
                productName.getText() + "', prix = '" +
                productPrice.getText() + "', seuil = '" +
                productAlert.getText() + "', date = '" +
                productDate.getValue() + "' WHERE id = '" +
                id + "'";

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
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void close() {
        Stage stg = (Stage) addPrpduitButton.getScene().getWindow();
        stg.close();
    }

    private Integer tmpId;

    public Integer getTmpId() {
        return tmpId;
    }

    public void setTmpId(Integer tmpId) {
        this.tmpId = tmpId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //--------------------------------------

        String filePath = "C:\\Users\\adngo\\Desktop\\Projets\\easy-shop\\src\\main\\resources\\org\\example\\marketeasy\\fichierTxt\\tmp_id.txt";
        File file = new File(filePath); // Remplacez par le chemin où vous voulez créer le fichier

        //--------------------------------------

        Articles produit = new Articles();

        String query = "SELECT * FROM produits WHERE id = ?";
        DashboardController dash = new DashboardController();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Afficher chaque ligne du fichier
                id = line;
            }

            Connection connection = Database.shop_connectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
//                id = Integer.valueOf(rs.getString("id"));
                productName.setText(rs.getString("nom"));
                productPrice.setText(rs.getString("prix"));
                productDate.setValue(LocalDate.parse(rs.getString("date")));
                productAlert.setText(rs.getString("seuil"));
            }
//            System.out.println(rs.getString("nom"));
//            System.out.println(reader.readLine());
//            System.out.println(line);
//            System.out.println(rs.next());

            connection.close();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
