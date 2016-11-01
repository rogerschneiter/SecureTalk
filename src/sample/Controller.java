package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    private Stage stage = new Stage();
    private Scene sceneLogin, sceneRegister;
    private Pane loginPane, registerPane;

    public void login(ActionEvent actionEvent) {

        try {
            loginPane = FXMLLoader.load(getClass().getResource("loginform.fxml"));
            sceneLogin = new Scene(loginPane, 280, 353);
            stage.setScene(sceneLogin);
            stage.setTitle("Authentification");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void register(ActionEvent actionEvent) {

        try {
            registerPane = FXMLLoader.load(getClass().getResource("registerform.fxml"));
            sceneRegister = new Scene(registerPane, 280, 353);
            stage.setScene(sceneRegister);
            stage.setTitle("Registration");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
