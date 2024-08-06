package org.example.marketeasy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.marketeasy.IDBConfig.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class HelloApplication extends Application {


    private double x = 0;
    private double y = 0;
    private String test;
    private boolean develloper = true;

    private void verification() {
        String sqlReq = "SELECT * FROM shop_user";
        Connection connection = Database.shop_connectDB();

        try {
            PreparedStatement prepStat = connection.prepareStatement(sqlReq);
            ResultSet resultSet = prepStat.executeQuery();

            if (resultSet.next()) {
                test = resultSet.getString("user_name");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
////        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("broullion.fxml"));
////        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Acceuil");
//        stage.setScene(scene);
//        stage.show();

        verification();

        String pages;
        if (test == null) {
            pages = "creationDeCompte.fxml";
        } else if (develloper) {
            pages = "dashboard.fxml";
        } else {
            pages = "connection.fxml";
        }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(pages)));
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);

        root.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) ->{ // Responsable de l'oppaciter l'orsqu'on traîne l'écran.
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(.8);
        });

        root.setOnMouseReleased((MouseEvent event) ->{
            stage.setOpacity(1);
        });

        stage.initStyle(StageStyle.TRANSPARENT); // Pour faire disparaitre la petite bande en haut de l'écran

        stage.setTitle("Connection");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}