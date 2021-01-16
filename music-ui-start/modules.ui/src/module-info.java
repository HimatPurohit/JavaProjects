module modules.ui {
    requires javafx.fxml;
    requires javafx.controls;
    requires modules.db;
    // acquired through transitive dependency
//    requires modules.common;
    opens modules.ui;

}