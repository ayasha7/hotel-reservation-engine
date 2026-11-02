package model;

public class StandardRoom extends Room {
    public StandardRoom(int roomNumber) {
        super(roomNumber, 2000);
    }

    @Override
    public double calculatePrice(int nights) {
        return getBasePrice() * nights;
    }
}