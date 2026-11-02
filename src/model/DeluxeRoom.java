package model;

public class DeluxeRoom extends Room {
    public DeluxeRoom(int roomNumber) {
        super(roomNumber, 4000);
    }

    @Override
    public double calculatePrice(int nights) {
        double total = getBasePrice() * nights;
        if (nights >= 5) {
            total = total * 0.90;
        }
        return total;
    }
}