
import model.StandardRoom;
import model.DeluxeRoom;
import model.SuiteRoom;
import service.HotelService;
import exception.RoomNotFoundException;
import exception.RoomAlreadyBookedException;

public class Main {

    public static void main(String[] args) {

        HotelService hotel = new HotelService();

        System.out.println("\n=== Adding Rooms ===");

        if (hotel.findRoom(101) == null) {
            hotel.addRoom(new StandardRoom(101));
        }

        if (hotel.findRoom(201) == null) {
            hotel.addRoom(new DeluxeRoom(201));
        }

        if (hotel.findRoom(301) == null) {
            hotel.addRoom(new SuiteRoom(301));
        }

        System.out.println("\n=== All Hotel Rooms ===");
        hotel.displayAllRooms();

        System.out.println("\n=== Initial Room Availability ===");
        System.out.println("Room 201 available? " +
                hotel.isRoomAvailable(201));

        System.out.println("\n=== Booking Room 201 ===");

        try {
            hotel.bookRoom(201, 5);
        } catch (Exception e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

        System.out.println("\n=== Checking Availability After Booking ===");
        System.out.println("Room 201 available? " +
                hotel.isRoomAvailable(201));

        System.out.println("\n=== Testing Already Booked Room ===");

        try {
            hotel.bookRoom(201, 3);
        } catch (RoomAlreadyBookedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Checkout Room 201 ===");

        try {
            hotel.checkoutRoom(201);
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }

        System.out.println("\n=== Testing Non-Existing Room ===");

        try {
            hotel.bookRoom(999, 2);
        } catch (RoomNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Testing Invalid Number of Nights ===");

        try {
            hotel.bookRoom(101, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Testing Checkout of Non-Existing Room ===");

        try {
            hotel.checkoutRoom(999);
        } catch (RoomNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Testing Checkout of Available Room ===");

        try {
            hotel.checkoutRoom(101);
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Final Checkout ===");

        try {
            hotel.checkoutRoom(201);
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }

        System.out.println("\n=== Final Room Status ===");
        System.out.println("Room 201 booked? " +
                hotel.findRoom(201).isBooked());

        System.out.println("\n=== Final Room Details ===");
        hotel.displayAllRooms();
    }
}
