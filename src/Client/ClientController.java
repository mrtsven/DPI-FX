package Client;

import Shared.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.jms.JMSException;


public class ClientController implements IListener {
    private static Connector connector;
    @FXML
    public TextField txt_search;
    @FXML
    public TextArea view_1;
    private String userInput;

    public void initialize(){
        startConnection();
    }

    @Override
    public void onMessage(Car message) {
        CarReply carReply = (CarReply) message;
        if(carReply != null){
            updateScreen(carReply.toString());
        }
    }

    private void updateScreen(String message){
        Platform.runLater(
                () -> {
                    view_1.setText(message);
                }
        );
    }

    private void startConnection(){
        connector = new Connector("carResult", this);
    }

    public void on_search(ActionEvent actionEvent) {
        connector.sendMessage(new CarRequest(txt_search.getText()), "carSearch", "request");
    }
}
