
package persistence;

import model.Room;
import model.StandardRoom;
import model.DeluxeRoom;
import model.SuiteRoom;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileManager {

    private static final String FILE_PATH = "data/rooms.txt";

    // Save all rooms into a file
    public static void saveRooms(
            ConcurrentHashMap<Integer, Room> rooms) {

        try {

            // Create data folder if it doesn't exist
            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Write room information into file
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(FILE_PATH)
            );

            for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {

                Room room = entry.getValue();

                String line = room.getRoomNumber() + ","
                        + room.getClass().getSimpleName() + ","
                        + room.isBooked();

                writer.write(line);
                writer.newLine();
            }

            writer.close();

            System.out.println("Room data saved successfully!");

        } catch (IOException e) {

            System.out.println("Error saving room data: " + e.getMessage());

        }
    }

    // Load rooms from file
    public static ConcurrentHashMap<Integer, Room> loadRooms() {

        ConcurrentHashMap<Integer, Room> rooms =
                new ConcurrentHashMap<>();

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return rooms;
        }

        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader(FILE_PATH)
            );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int roomNumber = Integer.parseInt(data[0]);
                String roomType = data[1];
                boolean booked = Boolean.parseBoolean(data[2]);

                Room room = null;

                switch (roomType) {

                    case "StandardRoom":
                        room = new StandardRoom(roomNumber);
                        break;

                    case "DeluxeRoom":
                        room = new DeluxeRoom(roomNumber);
                        break;

                    case "SuiteRoom":
                        room = new SuiteRoom(roomNumber);
                        break;
                }

                if (room != null) {
                    room.setBooked(booked);
                    rooms.put(roomNumber, room);
                }
            }

            reader.close();

            System.out.println("Room data loaded successfully!");

        } catch (IOException e) {

            System.out.println("Error loading room data: " + e.getMessage());

        }

        return rooms;
    }
}
