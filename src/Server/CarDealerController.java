package Server;

import Shared.*;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;

public class CarDealerController implements IListener {
    // Stuff
    private static Connector connector;
    private List<String> availableCars;

    @Override
    public void onMessage(Car message) {
        CarRequest carRequest = (CarRequest) message;

        // set list-view
//        System.out.println("Customer is looking for a: " + carRequest.getCar());

        if(checkForCar(carRequest.getCar())) {
            // do we have?
            System.out.println("We have a: " + carRequest.getCar());

            try {
                // Reply
                System.out.println("Sending a reply to customer.");
                CarReply reply = new CarReply("BMW", "ws-xs-tt", "blue", 3, 40000);
                System.out.println("Sending this car: " + reply.getType());

                connector.sendMessage(reply, "carResult", "reply");

            } catch (JMSException e) {
                e.printStackTrace();
            }
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
