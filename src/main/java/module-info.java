module org.example.retropvpadmin {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.retropvpadmin to javafx.fxml;
    exports org.example.retropvpadmin;
}