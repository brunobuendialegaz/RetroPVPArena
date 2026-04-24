module org.example.retropvpadmin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;
    requires static lombok;

    opens org.example.retropvpadmin.controller to javafx.fxml;
    opens org.example.retropvpadmin.model;
    exports org.example.retropvpadmin;
}