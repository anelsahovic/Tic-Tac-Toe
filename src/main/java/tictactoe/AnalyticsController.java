package tictactoe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AnalyticsController implements Initializable {
    //FXML elements
    @FXML
    private TableView<Match> matchesTable;

    @FXML
    private TableColumn<Match, Integer> mtId;

    @FXML
    private TableColumn<Match, String> mtPlayerOne;

    @FXML
    private TableColumn<Match, String> mtPlayerTwo;

    @FXML
    private TableColumn<Match, String> mtWinner;

    private ObservableList<Match> matchesList;
    MainController mainController = new MainController();

    public void goToHomeScreen(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/fxml/HomeScreen.fxml");
    }

    public ObservableList<Match> addMatchListData() {
        ObservableList<Match> listData = FXCollections.observableArrayList();
        String sqlQuery = "SELECT \n" +
                "    pm.match_id,\n" +
                "    u1.username AS player1,\n" +
                "    u2.username AS player2,\n" +
                "    u3.username AS winner\n" +
                "FROM \n" +
                "    played_match pm\n" +
                "JOIN \n" +
                "    user u1 ON pm.player1_id = u1.user_id\n" +
                "JOIN \n" +
                "    user u2 ON pm.player2_id = u2.user_id\n" +
                "JOIN \n" +
                "    user u3 ON pm.winner_id = u3.user_id;\n";

        try (Connection connection = Database.connectDB();
             PreparedStatement prepare = connection.prepareStatement(sqlQuery);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                Match match = new Match(result.getInt("match_id"), result.getString("player1"), result.getString("player2"), result.getString("winner"));
                listData.add(match);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void showMatchesTable() {
        matchesList = addMatchListData();

        mtId.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        mtPlayerOne.setCellValueFactory(new PropertyValueFactory<>("playerOne"));
        mtPlayerTwo.setCellValueFactory(new PropertyValueFactory<>("playerTwo"));
        mtWinner.setCellValueFactory(new PropertyValueFactory<>("winner"));

        matchesTable.setItems(matchesList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showMatchesTable();
    }
}
