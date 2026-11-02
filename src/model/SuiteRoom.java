package model;

public class SuiteRoom extends Room {
    public SuiteRoom(int roomNumber) {
        super(roomNumber, 8000);
    }

    @Override
    public double calculatePrice(int nights) {
        double total = getBasePrice() * nights;
        if (nights >= 3) {
            total = total * 0.85;
        }
        return total;
    }
}