package org.example.marketeasy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnectionController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    //    Fermer la fenêtred
    public void close() {
        System.exit(0);
    }


    //    cacher la fenêtred
    public void hide() {
        Stage stg = (Stage) loginButton.getScene().getWindow();
        stg.setIconified(true);
    }

}
