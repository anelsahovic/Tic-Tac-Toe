module tictactoe.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.flywaydb.core;
    opens db.migration;

    opens tictactoe to javafx.fxml;
    exports tictactoe;
}