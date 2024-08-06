package org.example.marketeasy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Cat_items_view_controller {

    @FXML
    private Label cat_name_try;

    public void setItemData(String name) {
        cat_name_try.setText(name);
    }
}
