package org.example.marketeasy.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.marketeasy.HelloApplication;
import org.example.marketeasy.IDBConfig.Database;
import org.example.marketeasy.CategoryData;
import org.example.marketeasy.ProductData;
import org.example.marketeasy.models.User;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


//    ------------------------------------Générale-debut----------------------------------------------------------------

//    -----------------------------------------Variables-Zone-debut-----------------------------------------------------

    @FXML
    private TableView<?> DatesTableView;

    @FXML
    private Button acceuilButton;

    @FXML
    private Button addCat1Buttom;

    @FXML
    private Button addProduit1Button;

    @FXML
    private Button aidesButton;

    @FXML
    private Button calculatriceButton;

    @FXML
    private Label categorieName;

    @FXML
    private Button close_button;

    @FXML
    private TableView<CategoryData> categorieTableView;

    @FXML
    private TableColumn<?, ?> colAllDates;

    @FXML
    private TableColumn<CategoryData, String> colCatDates;

    @FXML
    private TableColumn<CategoryData, String> colCatDescriptions;

    @FXML
    private TableColumn<CategoryData, String> colCatNoms;

    @FXML
    private TableColumn<CategoryData, String> colCatProduitRouges;

    @FXML
    private TableColumn<CategoryData, String> colCatTotalProduits;

    @FXML
    private TableColumn<?, ?> colDayNom;

    @FXML
    private TableColumn<?, ?> colDaySize;

    @FXML
    private TableColumn<?, ?> colDaySomme;

    @FXML
    private TableColumn<ProductData, String> colProdFreq;

    @FXML
    private TableColumn<ProductData, String> colProdName;

    @FXML
    private TableColumn<ProductData, String> colProdPrice;

    @FXML
    private TableColumn<ProductData, String> colProdSize;

    @FXML
    private TableColumn<?, ?> colRev;

    @FXML
    private Label dateDuJour;


//    Absent
    @FXML
    private Label day;

//    Absent
    @FXML
    private Label dayRev;

    @FXML
    private TableView<?> dayTableView;

    @FXML
    private Button deleteAllProdButton;

    @FXML
    private Button deleteCatButtom;

    @FXML
    private Button deleteProdButton;

    @FXML
    private Label dimanchePrix;

    @FXML
    private Label faibleStock;

    @FXML
    private Label jeudiPrix;

    @FXML
    private Button logoutButton;

    @FXML
    private Label lundiPrix;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label mardiPrix;

    @FXML
    private Label mercrediPrix;

    @FXML
    private Label moyenStock;

    @FXML
    private Label prodFreq;

    @FXML
    private Label prodName;

    @FXML
    private Label prodPrice;

    @FXML
    private Label prodSize;

    @FXML
    private TableView<ProductData> prodTableView;

    @FXML
    private Button produitsButton;

    @FXML
    private Label samediPrix;

    @FXML
    private BorderPane screen1;

    @FXML
    private AnchorPane screen2;

    @FXML
    private AnchorPane screen3;

    @FXML
    private AnchorPane screen4;

    @FXML
    private ScrollPane screen5;

    @FXML
    private Label screenName;

    @FXML
    private Label screenUsername;

    @FXML
    private ImageView searchIcone;

    @FXML
    private TextField searchTextZone;

//    Absent
    @FXML
    private AnchorPane standard_screen;

    @FXML
    private Button statistiquesButton;

    @FXML
    private Label vendrediPrix;

    @FXML
    private Label videStock;

    @FXML
    private Pane fullScreen;

    @FXML
    private Pane smaleScreen;

//    -----------------------------------Variables-Zone-fin-------------------------------------------------------------

    //    Fermer la fenêtred
    public void close() {
        System.exit(0);
    }


    //    cacher la fenêtred
    public void hide() {
        Stage stg = (Stage) screen2.getScene().getWindow();
        stg.setIconified(true);
    }

    //    agrendire ou reduire la fenêtred
    public void fullScreenFunction() {
        Stage stage = new Stage();
        stage = (Stage) screen2.getScene().getWindow();

        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
            fullScreen.setVisible(true);
            smaleScreen.setVisible(false);
        } else {
            fullScreen.setVisible(false);
            smaleScreen.setVisible(true);
            stage.setFullScreen(true);
        }
    }

    ConnectionController connectionController = new ConnectionController();

    public Label getScreenUsername(String screenUserName) {
        return screenUsername;
    }

    public void setScreenUsername(Label screenUsername) {
        this.screenUsername = screenUsername;
    }

        User user = new User();

    public void displayUsername() {
//        String userName = user.getUsername();
//        userName = userName.substring(0, 1).toUpperCase() + userName.substring(1);
//        screenUsername.setText(userName);
//        System.out.println(userName);
    }

    //    Pour basculer entre les écrans
    public void screenChange(ActionEvent event) {

        if (event.getSource() == acceuilButton) {

            screen1.setVisible(true);
            screen2.setVisible(false);
            screen3.setVisible(false);
            screen4.setVisible(false);
            screen5.setVisible(false);

            acceuilButton.setStyle("-fx-background-color : #f84df8;");
            statistiquesButton.setStyle("-fx-background-color : transparent;");
            produitsButton.setStyle("-fx-background-color : transparent;");
            calculatriceButton.setStyle("-fx-background-color : transparent;");
            aidesButton.setStyle("-fx-background-color : transparent;");

            screenName.setText("ACCEUIL");

        } else if (event.getSource() == produitsButton) {

            screen1.setVisible(false);
            screen2.setVisible(true);
            screen3.setVisible(false);
            screen4.setVisible(false);
            screen5.setVisible(false);

            acceuilButton.setStyle("-fx-background-color : transparent;");
            statistiquesButton.setStyle("-fx-background-color : transparent;");
            produitsButton.setStyle("-fx-background-color : #f84df8;");
            calculatriceButton.setStyle("-fx-background-color : transparent;");
            aidesButton.setStyle("-fx-background-color : transparent;");

            screenName.setText("PRODUITS");

            addCategoryShowData();
            addProductShowData();
            searchProd();

        } else if (event.getSource() == statistiquesButton) {

            screen1.setVisible(false);
            screen2.setVisible(false);
            screen3.setVisible(false);
            screen4.setVisible(true);
            screen5.setVisible(false);

            acceuilButton.setStyle("-fx-background-color : transparent;");
            statistiquesButton.setStyle("-fx-background-color : #f84df8;");
            produitsButton.setStyle("-fx-background-color : transparent;");
            calculatriceButton.setStyle("-fx-background-color : transparent;");
            aidesButton.setStyle("-fx-background-color : transparent;");

            screenName.setText("STATISTIQUES");

        } else if (event.getSource() == calculatriceButton) {

            screen1.setVisible(false);
            screen2.setVisible(false);
            screen3.setVisible(true);
            screen4.setVisible(false);
            screen5.setVisible(false);

            acceuilButton.setStyle("-fx-background-color : transparent;");
            statistiquesButton.setStyle("-fx-background-color : transparent;");
            produitsButton.setStyle("-fx-background-color : transparent;");
            calculatriceButton.setStyle("-fx-background-color : #f84df8;");
            aidesButton.setStyle("-fx-background-color : transparent;");

            screenName.setText("CALCULATRICE");

        } else if (event.getSource() == aidesButton) {

            screen1.setVisible(false);
            screen2.setVisible(false);
            screen3.setVisible(false);
            screen4.setVisible(false);
            screen5.setVisible(true);

            acceuilButton.setStyle("-fx-background-color : transparent;");
            statistiquesButton.setStyle("-fx-background-color : transparent;");
            produitsButton.setStyle("-fx-background-color : transparent;");
            calculatriceButton.setStyle("-fx-background-color : transparent;");
            aidesButton.setStyle("-fx-background-color : #f84df8;");

            screenName.setText("AIDES");

        }

    }

    public void addProd() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("addProduct.fxml")));
        Stage stg = new Stage();

        Scene scn = new Scene(root);

        stg.initStyle(StageStyle.TRANSPARENT);

        stg.setTitle("Ajout de produit");
        stg.setScene(scn);
        stg.show();

    }

    public void updateProd() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("updateProduct.fxml")));
        Stage stg = new Stage();

        Scene scn = new Scene(root);

        stg.initStyle(StageStyle.TRANSPARENT);

        stg.setTitle("Modifier un produit");
        stg.setScene(scn);
        stg.show();

    }

    public void deleteProd() {

        String sqldelete = "DELETE FROM produits WHERE nom = '" +
                prodName.getText() + "'";

        Connection connection = Database.shop_connectDB();

        try {
            Alert alert;

            if (prodName.getText().equals("Nom du produit")) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Choisir d'abord le produit à supprimer");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confiramtion");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment supprimer " +prodName.getText()+ " ?");

                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    PreparedStatement prepar = connection.prepareStatement(sqldelete);
                    prepar.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Suppression réussie!");
                    alert.showAndWait();

                } else {
                    return;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void searchProd() {

        FilteredList<ProductData> filter = new FilteredList<>(addProductList, e-> true);

        searchTextZone.textProperty().addListener((Observable, oldeValue, newValues) -> {

            filter.setPredicate(predicateProductData ->{

                if (newValues == null || newValues.isEmpty()) {
                    return true;
                }

                String searchKey = newValues.toLowerCase();

                return predicateProductData.getProdName().toLowerCase().contains(searchKey);

//                else if (predicateProductData.getProdPrice().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else if (predicateProductData.getProdSize().toLowerCase().contains(searchKey)){
//                    return true;
//                } else return false;
//                else if (predicateProductData.getProdFreq().toLowerCase().contains(searchKey)) {
////                    return true;
//                }
            });
        });

        SortedList<ProductData> sortedList = new SortedList<>(filter);

        sortedList.comparatorProperty().bind(prodTableView.comparatorProperty());
        prodTableView.setItems(sortedList);

    }

    public void addCat() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("addCategories.fxml")));
        Stage stg = new Stage();

        Scene scn = new Scene(root);

        stg.initStyle(StageStyle.TRANSPARENT);

        stg.setTitle("Ajout de categories");
        stg.setScene(scn);
        stg.show();

    }

//    pours ajouter les éléments de la bd à l'Observablelist
    public ObservableList<CategoryData> addCategoryListeData() {

        ObservableList<CategoryData> catListe = FXCollections.observableArrayList();
        String sql = "SELECT * FROM categories";
        Connection connection = Database.shop_connectDB();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CategoryData categoryData = new CategoryData(
                        resultSet.getString("nom"),
                        resultSet.getString("date_d_ajout"),
                        resultSet.getString("total_de_produits"),
                        resultSet.getString("produits_rouges"));

                catListe.add(categoryData);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return catListe;
    }

    public void addCatSelect() {

        CategoryData categoryData = categorieTableView.getSelectionModel().getSelectedItem();

        int number = categorieTableView.getSelectionModel().getSelectedIndex();

        if ((number - 1) < -1) {
            return;
        }

        categorieName.setText(categoryData.getCatName());

    }

    private ObservableList<CategoryData> addCategoryList;

    public void addCategoryShowData() {  //pour afficher les éléments de la bd

        addCategoryList = addCategoryListeData();

        colCatNoms.setCellValueFactory(new PropertyValueFactory<>("catName"));
        colCatDates.setCellValueFactory(new PropertyValueFactory<>("catDate"));
        colCatTotalProduits.setCellValueFactory(new PropertyValueFactory<>("catTotProd"));
        colCatProduitRouges.setCellValueFactory(new PropertyValueFactory<>("catRedProd"));

        categorieTableView.setItems(addCategoryList);

    }

    public ObservableList<ProductData>  addProductListeData() {

        ObservableList<ProductData> prodListe = FXCollections.observableArrayList();
        String sql = "SELECT * FROM produits";
        Connection connection = Database.shop_connectDB();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProductData productData = new ProductData(
                        resultSet.getString("nom"),
                        resultSet.getString("prix"),
                        resultSet.getString("frequence"),
                        resultSet.getString("quantite"));

                prodListe.add(productData);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodListe;
    }

    private ObservableList<ProductData> addProductList;

    public void addProductShowData() {  //pour afficher les éléments de la bd

        addProductList = addProductListeData();

        colProdName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        colProdPrice.setCellValueFactory(new PropertyValueFactory<>("prodPrice"));
        colProdFreq.setCellValueFactory(new PropertyValueFactory<>("prodFreq"));
        colProdSize.setCellValueFactory(new PropertyValueFactory<>("prodSize"));

        prodTableView.setItems(addProductList);

    }

    public void addProdSelect() {

        ProductData productData = prodTableView.getSelectionModel().getSelectedItem();

        int number = prodTableView.getSelectionModel().getSelectedIndex();

        if ((number - 1) < -1) {
            return;
        }

        prodName.setText(productData.getProdName());
        prodSize.setText(productData.getProdSize());
        prodPrice.setText(productData.getProdPrice() + "fcfa");
        prodFreq.setText(productData.getProdFreq() + "/jour");

    }




    @Override
//    Oppération à éffectuer dès le lancement de l'appli
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        addHistoryShowData();
        displayUsername();
        screenName.setText("ACCEUIL");
        acceuilButton.setStyle("-fx-background-color : #f84df8;");
//        screenUsername.setText(user.getUsername());

        addCategoryShowData();
        addProductShowData();
    }



//    --------------------------------------Générale fin----------------------------------------------------------------
//    ---------------------------Calculatrice Zone debut----------------------------------------------------------------

    //    -----------------------------------Variables-Zone-debut-------------------------------------------------------------
    @FXML
    private TextField answerZone;

    @FXML
    private TextField operationZone;

    private String actionGenerale;

    private double firstNumber;

    private double result = 0;

    private boolean decimaleClick = true;

    private double secondNumber;

    @FXML
    private ChoiceBox<String> calc_type;

    private Statement statement;

    @FXML
    private TableColumn<?, String> historyOfCalc;

    @FXML
    private TableView<?> historyTable;

//    -----------------------------------Variables-Zone-fin-------------------------------------------------------------

    @FXML
    public void virgule(ActionEvent event) {
//        if(decimaleClick) {
//            String nmbrdecimale = ((Button)event.getSource()).getText();
//            String ancienNombre = answerZone.getText();
//            String nouveauNombre = ancienNombre + nmbrdecimale;
//            answerZone.setText(nouveauNombre);
//            decimaleClick = false;
//        }
    }

    //    Générateur de contenu pour la base de donné
//    private String hist(String signe) {
//
////        String format = String.format("%.2f" ,result);
////        answerZone.setText(format);
////
////        String hist1 = String.valueOf(firstNumber);
////        String hist2 = String.valueOf(secondNumber);
////        String historique = hist1 + " " + signe + " " + hist2 + " " + "=" + " " + format;
////        String operation = hist1 + " " + signe + " " + hist2;
////        operationZone.setText(operation);
//        return historique;
//    }

    @FXML
//    Saisie des nombres entier
    public void nombreEntié(MouseEvent event) {
//        String nmbrEntier = ((Button)event.getSource()).getText();
//        String ancienNombre = answerZone.getText();
//        String nouveauNombre = ancienNombre + nmbrEntier;
//        answerZone.setText(nouveauNombre);
    }

    @FXML
//    Opération génerale
    public void generale(MouseEvent event) {
//        actionGenerale = ((Button)event.getSource()).getText();
//        switch (actionGenerale) {
//            case "C" :
//                reset();
//                decimaleClick = true;
//                break;
//            case "+/-" :
//                double changeSigne = Double.parseDouble(answerZone.getText());
//                changeSigne = changeSigne * (-1);
//                answerZone.setText(String.valueOf(changeSigne));
//                break;
//            case "+" :
//            case "-" :
//            case "*" :
//            case "/" :
//            case "EXP" :
//            case "%" :
//                String firstText = answerZone.getText();
//                firstNumber = Double.parseDouble(firstText);
//                answerZone.setText("");
//                operationZone.setText(String.valueOf(firstNumber));
//                decimaleClick = true;
//                break;
//            default:
//        }
    }

    //    Effacer l'ecran de la calc.
    public void reset() {
//        operationZone.setText("");
//        answerZone.setText("");
    }

    //    Ajout des éléments de la base de donné au "ObservableListe"
//    public ObservableList<HistData> addHistoryListeData() {
//        ObservableList<HistData> histList = FXCollections.observableArrayList();
//        String sql = "SELECT * FROM hist_calc";
//
//        //   DATABASE TOOLS
//        Connection connection = Database.shop_connectDB();
//        try {
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                HistData hist = new HistData(resultSet.getString("Historique"));
//                histList.add(hist);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
////            throw new RuntimeException(e);
//        }
//        return histList;
//    }

    @FXML
//    Le nom de la fonction est clair
    public void egale(MouseEvent event) throws SQLException {

//        String secondText = answerZone.getText();
//
//        secondNumber = Double.parseDouble(secondText);
//
//        History history = new History();
//        switch (actionGenerale) {
//            case "+" :
//                result = firstNumber + secondNumber;
//                hist("+");
//                history.historiques(hist("+"));
//                break;
//            case "-" :
//                result = firstNumber - secondNumber;
//                hist("-");
//                history.historiques(hist("-"));
//                break;
//            case "/" :
//                result = firstNumber / secondNumber;
//                hist("/");
//                history.historiques(hist("/"));
//                break;
//            case "*" :
//                result = firstNumber * secondNumber;
//                hist("*");
//                history.historiques(hist("*"));
//                break;
//            default:
//        }
//        String format = String.format("%.2f" ,result);
//        answerZone.setText(format);
//
//        addHistoryShowData();
    }

//    Ajout des élement de la base de donné au tableView
//    public void addHistoryShowData() {
//
//        ObservableList<HistData> addHistorylist = addHistoryListeData();
//
//        historyOfCalc.setCellValueFactory(new PropertyValueFactory<>("Historique"));
//
//        historyTable.setItems(addHistorylist);
//
//    }

//    Ajout des élement de la base de donné au tableView
//    public void historiques(String recents) throws SQLException {
//        addHistoryShowData();
//    }

    public void number(MouseEvent event) {

    }

//    @FXML
//    Effacer l'historique (Vider la base de donné)
    public void deleteHist() throws SQLException {
//        Connection connection = Database.shop_connectDB();
//        String req = "DELETE FROM hist_calc";
//
//        try {
//
//            assert connection != null;
//            PreparedStatement preparedStatement = connection.prepareStatement(req);
//            preparedStatement.executeUpdate();
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

}

//    -------------------------------Calculatrice Zone fin--------------------------------------------------------------
