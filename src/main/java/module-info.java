module tictactoe.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens tictactoe to javafx.fxml;
    exports tictactoe;
}