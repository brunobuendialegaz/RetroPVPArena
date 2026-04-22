module org.example.retropvpadmin {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;


    opens org.example.retropvpadmin to javafx.fxml;
    opens org.example.retropvpadmin.model to org.hibernate.orm.core;
    exports org.example.retropvpadmin;
}