package tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerVsPlayerController implements Initializable {
    //FXML Elements
    @FXML
    Button btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22;
    @FXML
    private Rectangle playerOneBox;
    @FXML
    private Rectangle playerTwoBox;
    @FXML
    private Label playerOneName;
    @FXML
    private Label playerTwoName;

    Button[][] buttons = new Button[3][3];
    String[][] symbols = new String[3][3];
    MainController mainController = new MainController();
    boolean playerOneTurn = true;
    boolean playerTwoTurn = false;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToHomeScreen(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/fxml/HomeScreen.fxml");
    }

    public void symbolBtnClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (buttons[i][j] == clickedButton) {
                    // Do something with the clicked button
                    if (playerOneTurn) {
                        clickedButton.setText("X");
                        symbols[i][j] = "X";
                        setPlayerTwoTurn();
                    } else if (playerTwoTurn) {
                        clickedButton.setText("O");
                        symbols[i][j] = "O";
                        setPlayerOneTurn();
                    }
                    clickedButton.setDisable(true);
                    System.out.println("Button[" + i + "][" + j + "]");

                }

            }
        }
        checkWinner();
        checkDraw();
    }

    public void setPlayerOneTurn() {
        playerOneTurn = true;
        playerTwoTurn = false;
        playerOneBox.setStrokeWidth(4);
        playerTwoBox.setStrokeWidth(1);
    }

    public void setPlayerTwoTurn() {
        playerOneTurn = false;
        playerTwoTurn = true;
        playerOneBox.setStrokeWidth(1);
        playerTwoBox.setStrokeWidth(4);
    }

    public void declarePlayerOneWinner() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setHeaderText("Player One Won!");
        alert.setContentText(playerOneName.getText() + " won the match!");

        // Set action to be performed when the alert is closed
        alert.setOnHidden(e -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/HomeScreen.fxml"));
                // Use alert's current window (stage) to navigate
                stage = (Stage) btn00.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        alert.showAndWait();
    }

    public void declarePlayerTwoWinner() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setHeaderText("Player Two Won!");
        alert.setContentText(playerTwoName.getText() + " won the match!");

        // Set action to be performed when the alert is closed
        alert.setOnHidden(e -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/HomeScreen.fxml"));
                // Use alert's current window (stage) to navigate
                stage = (Stage) btn11.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        alert.showAndWait();
    }

    public void declareDraw() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Draw!");
        alert.setHeaderText("There was a draw!");
        alert.setContentText("Nobody won the match!");

        // Set action to be performed when the alert is closed
        alert.setOnHidden(e -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/HomeScreen.fxml"));
                // Use alert's current window (stage) to navigate
                stage = (Stage) btn11.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        alert.showAndWait();
    }

    public void checkWinner() {

        //check main diagonal
        if (symbols[0][0] != null && !symbols[0][0].isEmpty() && symbols[0][0].equals(symbols[1][1]) && symbols[1][1].equals(symbols[2][2])) {
            if (symbols[0][0].equals("X")) {
                declarePlayerOneWinner();
            } else {
                declarePlayerTwoWinner();
            }
        }
        //check secondary diagonal
        if (symbols[0][2] != null && !symbols[0][2].isEmpty() && symbols[0][2].equals(symbols[1][1]) && symbols[1][1].equals(symbols[2][0])) {
            if (symbols[1][1].equals("X")) {
                declarePlayerOneWinner();
            } else if (symbols[1][1].equals("O")) {
                declarePlayerTwoWinner();
            }
        }
        //check rows
        for (int i = 0; i <= 2; i++) {
            if (symbols[i][0] != null && !symbols[i][0].isEmpty() && symbols[i][0].equals(symbols[i][1]) && symbols[i][0].equals(symbols[i][2])) {
                if (symbols[i][0].equals("X")) {
                    declarePlayerOneWinner();
                } else if (symbols[i][0].equals("O")) {
                    declarePlayerTwoWinner();
                }
            }
        }
        //check colls
        for (int i = 0; i <= 2; i++) {
            if (symbols[0][i] != null && !symbols[0][i].isEmpty() && symbols[0][i].equals(symbols[1][i]) && symbols[0][i].equals(symbols[2][i])) {
                if (symbols[0][i].equals("X")) {
                    declarePlayerOneWinner();
                } else if (symbols[0][i].equals("O")) {
                    declarePlayerTwoWinner();
                }
            }
        }


    }

    public void checkDraw() {
        // Check for any empty cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (symbols[i][j] == null || symbols[i][j].isEmpty()) {
                    // If there's an empty cell, it's not a draw yet
                    return;
                }
            }
        }
        // If all cells are filled and there's no winner, declare a draw
        declareDraw();
    }

    public void initializeButtons() {

        buttons[0][0] = btn00;
        buttons[0][1] = btn01;
        buttons[0][2] = btn02;
        buttons[1][0] = btn10;
        buttons[1][1] = btn11;
        buttons[1][2] = btn12;
        buttons[2][0] = btn20;
        buttons[2][1] = btn21;
        buttons[2][2] = btn22;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeButtons();
        setPlayerOneTurn();
    }
}
