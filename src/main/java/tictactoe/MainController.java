package tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene(ActionEvent event, String scenePath) throws IOException {
        root = FXMLLoader.load(getClass().getResource(scenePath));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToPlVsPlMenu(ActionEvent event) throws IOException {
        switchToScene(event,"/fxml/PlVsPlMenu.fxml");
    }

    public void goToPlVsPl(ActionEvent event) throws IOException {
        switchToScene(event,"/fxml/PlayerVsPlayer.fxml");
    }

    public void goToPlVsPcMenu(ActionEvent event) throws IOException {
        switchToScene(event,"/fxml/PlVsPcMenu.fxml");
    }

    public void goToPlVsPc(ActionEvent event) throws IOException {
        switchToScene(event, "/fxml/PlayerVsPc.fxml");
    }

    public void goToAnalytics(ActionEvent event) throws IOException {
        switchToScene(event, "/fxml/Analytics.fxml");
    }

    public void goToProfiles(ActionEvent event) throws IOException {
        switchToScene(event, "/fxml/Profiles.fxml");
    }

    public void goToHomeScreen(ActionEvent event) throws IOException {
        switchToScene(event, "/fxml/HomeScreen.fxml");
    }
}
