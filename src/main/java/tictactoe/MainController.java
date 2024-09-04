package tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ChoiceBox<String> playerOneChoicebox;
    @FXML
    private ChoiceBox<String> playerTwoChoicebox;
    @FXML
    private Label titleLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToScene(ActionEvent event, String scenePath) throws IOException {
        root = FXMLLoader.load(getClass().getResource(scenePath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToPlVsPlMenu(ActionEvent event) throws IOException {
        switchToScene(event, "/fxml/PlVsPlMenu.fxml");
    }

    public void goToPlVsPl(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayerVsPlayer.fxml"));
        root = loader.load();

        PlayerVsPlayerController playerVsPlayerController = loader.getController();
        playerVsPlayerController.setPlayerOneName(playerOneChoicebox.getValue());
        playerVsPlayerController.setPlayerTwoName(playerTwoChoicebox.getValue());

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void goToPlVsPcMenu(ActionEvent event) throws IOException {
        switchToScene(event, "/fxml/PlVsPcMenu.fxml");
    }

    public void goToPlVsPc(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayerVsPc.fxml"));
        root = loader.load();

        PlayerVsPcController playerVsPcController = loader.getController();
        playerVsPcController.setPlayerOneName(playerOneChoicebox.getValue());

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    public void populatePlayerOneChoicebox() {
        playerOneChoicebox.getItems().addAll(getUsernamesFromDb());
    }

    public void populatePlayerTwoChoicebox() {
        playerTwoChoicebox.getItems().addAll(getUsernamesFromDb());
    }

    public ArrayList<String> getUsernamesFromDb() {
        ArrayList<String> usernames = new ArrayList<>();
        String sqlQuery = "SELECT username FROM user WHERE NOT name='PC';";
        try (Connection connection = Database.connectDB();
             PreparedStatement prepare = connection.prepareStatement(sqlQuery);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                usernames.add(result.getString("username"));
               // System.out.println(result.getString("username"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usernames;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(titleLabel.getText().equals("Player Vs Player")){
            populatePlayerOneChoicebox();
            populatePlayerTwoChoicebox();
        } else if(titleLabel.getText().equals("Player Vs Computer")){
            populatePlayerOneChoicebox();
        }
    }
}
