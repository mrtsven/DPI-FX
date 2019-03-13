package Server;

import Client.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CarDealerLauncher extends Application {

    private CarDealerController carDealerController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("carDealerView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();
        carDealerController = new CarDealerController();
        carDealerController.init();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
