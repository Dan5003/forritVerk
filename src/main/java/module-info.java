module com.example.verk21 {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.example.verk21 to javafx.fxml;
    exports com.example.verk21;
}