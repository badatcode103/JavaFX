module org.example.hsf302_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens org.example.hsf302_javafx to javafx.fxml;
    opens org.example.pojo to  jakarta.persistence,org.hibernate.orm.core, javafx.base;

    exports org.example.hsf302_javafx;
    exports org.example.controller;
    opens org.example.controller to javafx.fxml;

}