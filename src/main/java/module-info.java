module App {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens App to javafx.fxml;
    exports App;
}
