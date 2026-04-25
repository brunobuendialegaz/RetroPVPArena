module org.example.retropvpadmin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;
    requires static lombok;
    requires java.desktop;

    opens org.example.retropvpadmin.controller to javafx.fxml;
    opens org.example.retropvpadmin.model;
    opens org.example.retropvpadmin.util to lombok;
    opens org.example.retropvpadmin.service to lombok;
    exports org.example.retropvpadmin;
}