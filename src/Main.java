import model.*;

public class Main {
    public static void main(String[] args) {

        Room standardRoom = new StandardRoom(101);
        Room deluxeRoom = new DeluxeRoom(201);
        Room suiteRoom = new SuiteRoom(301);

        System.out.println("=== Hotel Room Pricing ===");

        System.out.println(
                "Standard Room 101 | 5 nights = ₹"
                        + standardRoom.calculatePrice(5)
        );

        System.out.println(
                "Deluxe Room 201 | 5 nights = ₹"
                        + deluxeRoom.calculatePrice(5)
        );

        System.out.println(
                "Suite Room 301 | 5 nights = ₹"
                        + suiteRoom.calculatePrice(5)
        );
    }
}