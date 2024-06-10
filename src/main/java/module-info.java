module org.example.marketeasy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jdi;


    opens org.example.marketeasy to javafx.fxml;
    exports org.example.marketeasy;

    opens org.example.marketeasy.IDBConfig to javafx.fxml;
    exports org.example.marketeasy.IDBConfig;

    opens org.example.marketeasy.controllers to javafx.fxml;
    exports org.example.marketeasy.controllers;

    opens org.example.marketeasy.interfaces to javafx.fxml;
    exports org.example.marketeasy.interfaces;
}