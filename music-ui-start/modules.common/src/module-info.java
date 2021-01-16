module modules.common {
    requires javafx.fxml;
    requires javafx.controls;
    opens modules.common;
    exports modules.common;
}