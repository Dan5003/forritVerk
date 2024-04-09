module com.example.verk21 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.verk21 to javafx.fxml;
    exports com.example.verk21;
}