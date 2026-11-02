
package service;

import model.Room;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HotelService {

    // M3: Room registry
    private final ConcurrentHashMap<Integer, Room> rooms =
            new ConcurrentHashMap<>();

    // M3: Add a room (prevent duplicate room numbers)
    public void addRoom(Room room) {

        Room existingRoom = rooms.putIfAbsent(
                room.getRoomNumber(), room
        );

        if (existingRoom != null) {
            System.out.println(
                    "Room " + room.getRoomNumber() + " already exists!"
            );
        } else {
            System.out.println(
                    "Room " + room.getRoomNumber() + " added successfully!"
            );
        }
    }

    // M3: Find a room using room number
    public Room findRoom(int roomNumber) {
        return rooms.get(roomNumber);
    }

    // M3: Display all registered rooms
    public void displayAllRooms() {

        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {

            System.out.println(
                    "Room Number: " + entry.getKey()
                            + " | Room Type: " + entry.getValue().getClass().getSimpleName()
                            + " | Price: ₹" + entry.getValue().getBasePrice()
            );
        }
    }

    // M4: Check room availability
    public boolean isRoomAvailable(int roomNumber) {

        Room room = findRoom(roomNumber);

        if (room == null) {
            return false;
        }

        return !room.isBooked();
    }

    // M4: Book a room
    public void bookRoom(int roomNumber, int nights) {

        Room room = findRoom(roomNumber);

        if (room == null) {
            System.out.println("Room does not exist!");
            return;
        }

        if (room.isBooked()) {
            System.out.println("Room is already booked!");
            return;
        }

        double price = room.calculatePrice(nights);

        room.setBooked(true);

        System.out.println("Booking successful!");
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + room.getClass().getSimpleName());
        System.out.println("Number of Nights: " + nights);
        System.out.println("Total Price: ₹" + price);
    }

    // M4: Checkout a room
    public void checkoutRoom(int roomNumber) {

        Room room = findRoom(roomNumber);

        if (room == null) {
            System.out.println("Room does not exist!");
            return;
        }

        if (!room.isBooked()) {
            System.out.println("Room is not currently booked!");
            return;
        }

        room.setBooked(false);

        System.out.println("Checkout successful!");
        System.out.println("Room " + roomNumber + " is now available.");
    }
}
