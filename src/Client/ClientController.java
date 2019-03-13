package Client;

import Shared.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.jms.JMSException;
import java.awt.*;


public class ClientController implements IListener {
    private static Connector connector;
    @FXML
    public TextField txt_search;
    // will remove locked most likely
    private boolean locked = false;
    private String userInput;

    public void init(){
        startConnection();
    }

    @Override
    public void onMessage(Car message) {
        CarReply carReply = (CarReply) message;
    }

    private void startConnection(){
        connector = new Connector("carResult", this);
    }

    public void on_search(ActionEvent actionEvent) {
        try {
            connector.sendMessage(new CarRequest(txt_search.getText()), "carSearch", "request");
        } catch (JMSException e){
            e.printStackTrace();
        }
    }
}
