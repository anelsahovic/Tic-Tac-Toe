package tictactoe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ProfilesController implements Initializable {
    //FXML Elements
    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, Integer> utId;

    @FXML
    private TableColumn<User, String> utName;

    @FXML
    private TableColumn<User, String> utUsername;

    @FXML
    private TableColumn<User, Integer> utWins;
    @FXML
    private TextField nameTxtfield;

    @FXML
    private TextField usernameTxtfield;
    @FXML
    private TextField idTextField;

    private ObservableList<User> usersList;
    MainController mainController = new MainController();

    public void goToHomeScreen(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/fxml/HomeScreen.fxml");
    }

    public ObservableList<User> addUserListData() {
        ObservableList<User> listData = FXCollections.observableArrayList();
        String sqlQuery = "SELECT * FROM user";

        try (Connection connection = Database.connectDB();
             PreparedStatement prepare = connection.prepareStatement(sqlQuery);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                User user = new User(result.getInt("user_id"), result.getString("name"), result.getString("username"), result.getInt("wins"));
                listData.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void showTeamsTable() {
        usersList = addUserListData();

        utId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        utName.setCellValueFactory(new PropertyValueFactory<>("name"));
        utUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        utWins.setCellValueFactory(new PropertyValueFactory<>("wins"));

        usersTable.setItems(usersList);
    }

    public boolean usernameExists(String username) {
        String sqlQuery = "SELECT COUNT(*) FROM user WHERE username = ?;";
        int count = 0;
        try (Connection connection = Database.connectDB();
             PreparedStatement prepare = connection.prepareStatement(sqlQuery);
        ) {
            prepare.setString(1, username);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                count = result.getInt(1);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return count > 0;
    }

    public boolean userIdExists(int id) {
        String sqlQuery = "SELECT COUNT(*) FROM user WHERE user_id = ?;";
        int count = 0;
        try (Connection connection = Database.connectDB();
             PreparedStatement prepare = connection.prepareStatement(sqlQuery);
        ) {
            prepare.setInt(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                count = result.getInt(1);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return count > 0;
    }

    public void addNewUser() {
        String sqlQuery = "INSERT INTO user(name, username, wins) VALUES(?, ?, ?);";

        //check for empty fields and  if username exists
        if (nameTxtfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Name field!");
            alert.setContentText("Please enter Name!");
            alert.showAndWait();
        } else if (usernameTxtfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Username field!");
            alert.setContentText("Please enter Username!");
            alert.showAndWait();
        } else if (usernameExists(usernameTxtfield.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username already exists!");
            alert.setContentText("Please enter different username!");
            alert.showAndWait();
        } else {
            try (Connection connection = Database.connectDB();
                 PreparedStatement prepare = connection.prepareStatement(sqlQuery)) {

                prepare.setString(1, nameTxtfield.getText());
                prepare.setString(2, usernameTxtfield.getText());
                prepare.setInt(3, 0);
                prepare.executeUpdate();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            showTeamsTable();
        }
    }

    public void updateUser() {

        String sqlQuery = "UPDATE user SET name=?, username=? WHERE user_id = ?";

        if (nameTxtfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Name field!");
            alert.setContentText("Please enter Name!");
            alert.showAndWait();
        } else if (usernameTxtfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Username field!");
            alert.setContentText("Please enter Username!");
            alert.showAndWait();
        } else if (usernameExists(usernameTxtfield.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username already exists!");
            alert.setContentText("Please enter different username!");
            alert.showAndWait();
        } else if (!userIdExists(Integer.parseInt(idTextField.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User ID doesnt exist!");
            alert.setContentText("Please valid user ID!");
            alert.showAndWait();
        } else {
            try (Connection connection = Database.connectDB();
                 PreparedStatement prepare = connection.prepareStatement(sqlQuery)) {

                prepare.setString(1, nameTxtfield.getText());
                prepare.setString(2, usernameTxtfield.getText());
                prepare.setInt(3, Integer.parseInt(idTextField.getText()));
                prepare.executeUpdate();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            showTeamsTable();
        }


    }

    public void deleteUser() {
        String sqlQuery = "DELETE FROM user WHERE user_id=?;";

        if (!userIdExists(Integer.parseInt(idTextField.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User ID doesn't exist!");
            alert.setContentText("Please enter valid user ID!");
            alert.showAndWait();
        } else {
            try (Connection connection = Database.connectDB();
                 PreparedStatement prepare = connection.prepareStatement(sqlQuery)) {

                prepare.setInt(1, Integer.parseInt(idTextField.getText()));
                prepare.executeUpdate();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            showTeamsTable();
        }

    }

    public void selectUser() {
        String sqlQuery = "SELECT * FROM user WHERE user_id=?;";
        if (!userIdExists(Integer.parseInt(idTextField.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User ID doesn't exist!");
            alert.setContentText("Please enter valid user ID!");
            alert.showAndWait();
        } else {
            try (Connection connection = Database.connectDB();
                 PreparedStatement prepare = connection.prepareStatement(sqlQuery)) {
                prepare.setInt(1, Integer.parseInt(idTextField.getText()));
                ResultSet result = prepare.executeQuery();
                if(result.next()){
                    nameTxtfield.setText(result.getString(2));
                    usernameTxtfield.setText(result.getString(3));
                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void clearFields() {
        idTextField.clear();
        nameTxtfield.clear();
        usernameTxtfield.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTeamsTable();
    }
}
