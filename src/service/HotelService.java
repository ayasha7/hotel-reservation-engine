
package service;

import model.Room;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HotelService {

    private final ConcurrentHashMap<Integer, Room> rooms =
            new ConcurrentHashMap<>();

    // Add a room
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

    // Find a room using room number
    public Room findRoom(int roomNumber) {
        return rooms.get(roomNumber);
    }

    // Display all registered rooms
    public void displayAllRooms() {

        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {

            System.out.println(
                    "Room Number: " + entry.getKey()
                            + " | Room Type: " + entry.getValue().getClass().getSimpleName()
                            + " | Price: ₹" + entry.getValue().getBasePrice()
            );
        }
    }
}
