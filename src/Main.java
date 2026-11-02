import model.*;

public class Main {
    public static void main(String[] args) {
        Room r1 = new StandardRoom(101);
        Room r2 = new DeluxeRoom(201);
        Room r3 = new SuiteRoom(301);

        Room[] rooms = { r1, r2, r3 };

        for (Room room : rooms) {
            System.out.println("Room " + room.getRoomNumber()
                    + " | 5 nights = " + room.calculatePrice(5));
        }
    }
}