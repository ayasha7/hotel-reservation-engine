
package service;

import model.Room;
import exception.RoomNotFoundException;
import exception.RoomAlreadyBookedException;
import persistence.FileManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class HotelService {

    // Load previously saved rooms when the hotel starts
    private final ConcurrentHashMap<Integer, Room> rooms =
            FileManager.loadRooms();

    // Lock to prevent simultaneous booking and checkout operations
    private final ReentrantLock bookingLock = new ReentrantLock();


    // Add a new room
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

            FileManager.saveRooms(rooms);
        }
    }


    // Find a room using its room number
    public Room findRoom(int roomNumber) {
        return rooms.get(roomNumber);
    }


    // Display all rooms
    public void displayAllRooms() {

        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {

            Room room = entry.getValue();

            System.out.println(
                    "Room Number: " + entry.getKey()
                            + " | Room Type: " + room.getClass().getSimpleName()
                            + " | Price: ₹" + room.getBasePrice()
                            + " | Booked: " + room.isBooked()
            );
        }
    }


    // Check whether a room is available
    public boolean isRoomAvailable(int roomNumber) {

        Room room = findRoom(roomNumber);

        if (room == null) {
            return false;
        }

        return !room.isBooked();
    }


    // Book a room using ReentrantLock
    public void bookRoom(int roomNumber, int nights) {

        // Acquire the lock
        bookingLock.lock();

        try {

            Room room = findRoom(roomNumber);

            if (room == null) {
                throw new RoomNotFoundException(
                        "Room " + roomNumber + " does not exist!"
                );
            }

            if (room.isBooked()) {
                throw new RoomAlreadyBookedException(
                        "Room " + roomNumber + " is already booked!"
                );
            }

            if (nights <= 0) {
                throw new IllegalArgumentException(
                        "Number of nights must be greater than zero!"
                );
            }

            double price = room.calculatePrice(nights);

            room.setBooked(true);

            FileManager.saveRooms(rooms);

            System.out.println("Booking successful!");
            System.out.println("Room Number: " + roomNumber);
            System.out.println(
                    "Room Type: " + room.getClass().getSimpleName()
            );
            System.out.println("Number of Nights: " + nights);
            System.out.println("Total Price: ₹" + price);

        } finally {

            // Always release the lock
            bookingLock.unlock();
        }
    }


    // Checkout a room using the same lock
    public void checkoutRoom(int roomNumber) {

        bookingLock.lock();

        try {

            Room room = findRoom(roomNumber);

            if (room == null) {
                throw new RoomNotFoundException(
                        "Room " + roomNumber + " does not exist!"
                );
            }

            if (!room.isBooked()) {
                throw new IllegalStateException(
                        "Room " + roomNumber + " is not currently booked!"
                );
            }

            room.setBooked(false);

            FileManager.saveRooms(rooms);

            System.out.println("Checkout successful!");
            System.out.println(
                    "Room " + roomNumber + " is now available."
            );

        } finally {

            // Always release the lock
            bookingLock.unlock();
        }
    }
}
