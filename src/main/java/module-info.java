module com.example.verk21 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.verk21 to javafx.fxml;
    exports com.example.verk21;
}