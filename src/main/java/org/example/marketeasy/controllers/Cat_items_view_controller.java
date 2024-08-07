package org.example.marketeasy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Cat_items_view_controller {

    @FXML
    private Label cat_name_try;

    @FXML
    private Label description_category;

    @FXML
    private Label prod_red;

    @FXML
    private Label total_prod;

    public void catSetItemData(String name, String desc, String totalProd, String prodRed) {
        cat_name_try.setText(name);
        description_category.setText(desc);
        total_prod.setText(totalProd);
        prod_red.setText(prodRed);
    }
}
