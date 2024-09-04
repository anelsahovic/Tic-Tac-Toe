module tictactoe.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires flyway.core;


    opens tictactoe to javafx.fxml;
    exports tictactoe;
}