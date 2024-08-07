package org.example.marketeasy.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

public class Prod_items_view_controller {

    @FXML
    private ProgressBar ProbgressBar;

    @FXML
    private Label price;

    @FXML
    private Label prod_name_try;

    @FXML
    private ProgressIndicator progress_Ind;

    @FXML
    private Label totale_price;

    public void prodSetItemData(String name, String price_, String totalPrice, Integer P_B) {
        prod_name_try.setText(name);
        price.setText(price_);
        totale_price.setText(totalPrice);
    }

    @FXML
    public void initialize() {
//        startButton.setOnAction(event -> startTask());
    }

    @FXML
    private void startTask() {
        Task<Void> task = createTask();
        ProbgressBar.progressProperty().bind(task.progressProperty());
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(100); // Simule un travail
                    updateProgress(i, 100);
                }
                return null;
            }
        };
    }

}
