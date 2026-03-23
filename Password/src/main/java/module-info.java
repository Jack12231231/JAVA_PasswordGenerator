module com.example.password {
    requires javafx.controls;
    requires javafx.fxml;
    requires log4j;


    opens com.example.password to javafx.fxml;
    exports com.example.password;
}