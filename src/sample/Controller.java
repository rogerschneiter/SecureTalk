package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;

public class Controller {

    public TextField username;
    public PasswordField password1;
    public PasswordField password2;
    public TextField usernameForLogin;
    public PasswordField passwordForLogin;
    private Stage stage;
    private Scene scene;

    public void login(ActionEvent actionEvent) {

        try {
            Pane pane1 = FXMLLoader.load(getClass().getResource("loginform.fxml"));
            stage = new Stage();
            scene = new Scene(pane1, 283, 353);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void register(ActionEvent actionEvent) {

        try {
            Pane pane1 = FXMLLoader.load(getClass().getResource("registerform.fxml"));
            stage = new Stage();
            scene = new Scene(pane1, 283, 353);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void validateRegistration(ActionEvent actionEvent) {

        String lettersOnly = "^[a-zA-Z]{4,30}$";
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,30}$";
        String usernameValue = username.getText();
        String password1Value = password1.getText();
        String password2Value = password2.getText();
        String errorText = "";

        if (usernameValue.length() < 4 || usernameValue.length() > 30) {
            errorText += "Username too long/short! \n";
        }

        if (!usernameValue.matches(lettersOnly)) {
            errorText += "Username contains invalid characters! \n";
        }

        if (password1Value.length() < 8 || password1Value.length() > 30) {
            errorText += "Password too long/short! \n";
        }

        if (!password1Value.matches(passwordRegex)) {
            errorText += "Password not valid! \n";
        }

        if (!password1Value.equals(password2Value)) {
            errorText += "Passwords not identical! \n";
        }

        if (errorText.length() != 0) {
            Alert errorInfo = new Alert(Alert.AlertType.ERROR);
            errorInfo.setTitle("Validation Information");
            errorInfo.setWidth(250);
            errorInfo.setContentText(errorText);
            errorInfo.setResizable(true);
            errorInfo.showAndWait();
        } else {
            if (registerUser(usernameValue, password1Value)) {
                System.out.println("Sick bro. Registered!");
            }
        }
    }

    public boolean registerUser(String userName, String userPasswd) {

        boolean result = true;
        createTableIfNotExists();

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:securetalk.db");

            stmt = c.createStatement();
            String sql = "SELECT * FROM secureUser WHERE Username = '"+userName+"'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                result = false;
                rs.close();
            } else {
                rs.close();
                sql = "INSERT INTO secureUser (Username, Userpassword) VALUES " +
                        "('" + userName + "','" + userPasswd + "');";

                stmt.executeUpdate(sql);
            }

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return result;
    }

    public boolean loginUser(String username, String password) {

        boolean result = true;
        createTableIfNotExists();

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:securetalk.db");

            stmt = c.createStatement();
            String sql = "SELECT * FROM secureUser WHERE Username = '"+username+"'";
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                result = false;
            }

            rs.close();

            sql = "SELECT * FROM secureUser WHERE Username = '"+username+"' AND Userpassword = '" + password + "';";
            rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                result = false;
            }

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return result;

    }

    public void createTableIfNotExists() {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:securetalk.db");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS secureUser (\n" +
                    "\tUID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\tUsername TEXT NOT NULL,\n" +
                    "\tUserpassword TEXT NOT NULL\n" +
                    ")";

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    public void loginUserClick(ActionEvent actionEvent) {

        if (loginUser(usernameForLogin.getText(), passwordForLogin.getText())) {
            System.out.println("Successfully logged in!");
        }

    }

}
