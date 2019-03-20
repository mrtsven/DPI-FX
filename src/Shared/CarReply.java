package Shared;

public class CarReply extends Car {

    // Car properties for Reply
    private String carName;
    private String color;
    private Integer doors;
    private Integer price;

    public CarReply(String message){
        type = CarRequestType.REPLY;
        this.carName = message;
    }

    public CarReply(String carName, String color, Integer doors, Integer price) {
        type = CarRequestType.REPLY;

        this.carName = carName;
        this.color = color;
        this.doors = doors;
        this.price = price;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CarRequestType getType() {
        return type;
    }

    @Override
    public String toString() {
        if(price == null){
            return "We do not have a " + carName;
        } else {
            return "We have a " +
                    carName + ' ' + ", it's color is " + color + ", it has " + doors + " doors and it's priced at " + price;
        }
    }
}
