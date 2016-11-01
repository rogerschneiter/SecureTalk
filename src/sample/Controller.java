package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public void login(ActionEvent actionEvent) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("loginform.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 280, 353));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
