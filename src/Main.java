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
//
//import model.*;
//import service.HotelService;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        HotelService hotel = new HotelService();
//
//        // Adding rooms
//        hotel.addRoom(new StandardRoom(101));
//        hotel.addRoom(new DeluxeRoom(201));
//        hotel.addRoom(new SuiteRoom(301));
//
//
//        hotel.displayAllRooms();
//
//        // Display all rooms
//        System.out.println("=== All Hotel Rooms ===");
//        hotel.displayAllRooms();
//
//        // Finding a room
//        System.out.println("\n=== Searching for Room 201 ===");
//
//        Room room = hotel.findRoom(201);
//
//        if (room != null) {
//            System.out.println("Room Found: " + room.getClass().getSimpleName());
//            System.out.println("Price per night: ₹" + room.getBasePrice());
//        } else {
//            System.out.println("Room not found!");
//        }
//
//
//    }
//}

import model.*;
import service.HotelService;
import exception.RoomNotFoundException;
import exception.RoomAlreadyBookedException;

public class Main {

    public static void main(String[] args) {

        HotelService hotel = new HotelService();

        // Add rooms
        hotel.addRoom(new StandardRoom(101));
        hotel.addRoom(new DeluxeRoom(201));
        hotel.addRoom(new SuiteRoom(301));

        // Display all rooms
        System.out.println("\n=== All Hotel Rooms ===");
        hotel.displayAllRooms();

        // Check initial availability
        System.out.println("\n=== Initial Room Availability ===");

        System.out.println("Room 101: " + hotel.isRoomAvailable(101));
        System.out.println("Room 201: " + hotel.isRoomAvailable(201));

        // Book room 201
        System.out.println("\n=== Booking Room 201 ===");

        hotel.bookRoom(201, 5);


        // Try booking the same room again
        System.out.println("\n=== Trying to Book Room 201 Again ===");

        try {
            hotel.bookRoom(201, 3);
        }
        catch (RoomAlreadyBookedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // Checkout
        System.out.println("\n=== Checking Out Room 201 ===");

        hotel.checkoutRoom(201);

        // Check final availability
        System.out.println("\n=== Final Room Availability ===");

        System.out.println("Room 201: " + hotel.isRoomAvailable(201));

        // Test 1: Room does not exist
        System.out.println("\n=== Testing Invalid Room ===");

        try {
            hotel.bookRoom(999, 2);
        }
        catch (RoomNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }


// Test 2: Room is already booked
        System.out.println("\n=== Testing Already Booked Room ===");

        hotel.bookRoom(201, 2); // Book it once

        try {
            hotel.bookRoom(201, 3); // Try booking again
        }
        catch (RoomAlreadyBookedException e) {
            System.out.println("Error: " + e.getMessage());
        }


// Test 3: Invalid number of nights
        System.out.println("\n=== Testing Invalid Nights ===");

        try {
            hotel.bookRoom(101, 0);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test 4: Checkout a room that doesn't exist
        System.out.println("\n=== Testing Invalid Checkout Room ===");

        try {
            hotel.checkoutRoom(999);
        }
        catch (RoomNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }


// Test 5: Checkout a room that isn't booked
        System.out.println("\n=== Testing Checkout of Available Room ===");

        try {
            hotel.checkoutRoom(101);
        }
        catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

