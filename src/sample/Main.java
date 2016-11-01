package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // add samle.fxml as main FXML file for primaryStage
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("SecureTalk V1.0");

        // create primaryScene and add style.css as stylesheet for primaryScene
        Scene primaryScene = new Scene(root, 600, 400);
        primaryScene.getStylesheets().add(getClass().getResource("\\res\\style.css").toExternalForm());

        // set icon for primaryStage
        Image icon = new Image(getClass().getResourceAsStream("\\res\\icon.png"));
        primaryStage.getIcons().add(icon);

        // setScene and resizable false
        primaryStage.setScene(primaryScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Check if admin in a group which requires new vigenere phrase
    }


    public static void main(String[] args) {
        launch(args);
    }
}
