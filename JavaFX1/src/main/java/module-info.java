module javafx1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens echo to javafx.fxml;
    exports echo;
}