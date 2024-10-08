package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/HomeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            System.out.println("Starting database migration...");
            Database.migrate();
            System.out.println("Database migration completed.");
        } catch (Exception e) {
            System.err.println("Database migration failed.");
            e.printStackTrace();
        }

        launch(args);
    }
}
