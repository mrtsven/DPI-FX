package Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class ClientLauncher extends Application {

    private ClientController clientController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("clientLauncherView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();
        clientController = new ClientController();
        clientController.init();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
