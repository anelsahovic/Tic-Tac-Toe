package tictactoe;

import javafx.event.ActionEvent;

import java.io.IOException;

public class PlayerVsPcController {

    MainController mainController = new MainController();

    public void goToHomeScreen(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/fxml/HomeScreen.fxml");
    }
}
