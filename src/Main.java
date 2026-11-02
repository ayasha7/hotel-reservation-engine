//import model.*;
//
//public class Main {
//    public static void main(String[] args) {
//
//        Room standardRoom = new StandardRoom(101);
//        Room deluxeRoom = new DeluxeRoom(201);
//        Room suiteRoom = new SuiteRoom(301);
//
//        System.out.println("=== Hotel Room Pricing ===");
//
//        System.out.println(
//                "Standard Room 101 | 5 nights = ₹"
//                        + standardRoom.calculatePrice(5)
//        );
//
//        System.out.println(
//                "Deluxe Room 201 | 5 nights = ₹"
//                        + deluxeRoom.calculatePrice(5)
//        );
//
//        System.out.println(
//                "Suite Room 301 | 5 nights = ₹"
//                        + suiteRoom.calculatePrice(5)
//        );
//    }
//}

import model.*;
import service.HotelService;

public class Main {

    public static void main(String[] args) {

        HotelService hotel = new HotelService();

        // Adding rooms
        hotel.addRoom(new StandardRoom(101));
        hotel.addRoom(new DeluxeRoom(201));
        hotel.addRoom(new SuiteRoom(301));


        hotel.displayAllRooms();

        // Display all rooms
        System.out.println("=== All Hotel Rooms ===");
        hotel.displayAllRooms();

        // Finding a room
        System.out.println("\n=== Searching for Room 201 ===");

        Room room = hotel.findRoom(201);

        if (room != null) {
            System.out.println("Room Found: " + room.getClass().getSimpleName());
            System.out.println("Price per night: ₹" + room.getBasePrice());
        } else {
            System.out.println("Room not found!");
        }

    }
}
