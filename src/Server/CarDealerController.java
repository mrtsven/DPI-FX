package Server;

import Shared.*;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;

public class CarDealerController implements IListener {
    // Stuff
    private static Connector connector;
    private List<String> availableCars;

    public void init(){
        connection();
        setAvailableCars();
    }

    @Override
    public void onMessage(Car message) {
        CarRequest carRequest = (CarRequest) message;
        CarReply reply = null;
        // set list-view

        if(checkForCar(carRequest.getCar())) {
            // do we have?
            System.out.println("We have a: " + carRequest.getCar());

            // set Reply message
            switch(carRequest.getCar()) {
                case "BMW":
                    reply = new CarReply(carRequest.getCar(),"black", 3, 40000);
                    break;
                case "Mercedes":
                    reply = new CarReply(carRequest.getCar(), "red", 2, 800000);
                    break;
                case "SAAB":
                    reply = new CarReply(carRequest.getCar(),"blue", 4, 20300);
                    break;
                case "Volkswagen":
                    reply = new CarReply(carRequest.getCar(), "white", 3, 40000);
                    break;
                default: reply = new CarReply("Something went wrong");
            }
            // Send reply
            connector.sendMessage(reply, "carResult", "reply");
        } else {
            reply = new CarReply(carRequest.getCar());

            connector.sendMessage(reply, "carResult", "reply");
        }
    }

    private void connection(){
        connector = new Connector("carSearch", this);
    }

    private void setAvailableCars(){
        System.out.println("Producing a bunch of cars...");
        availableCars = new ArrayList<>();
        availableCars.add("BMW");
        availableCars.add("Mercedes");
        availableCars.add("SAAB");
        availableCars.add("Volkswagen");
    }

    private Boolean checkForCar(String carName){
        return availableCars.contains(carName);
    }


}
