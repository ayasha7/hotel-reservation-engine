
import service.HotelService;

public class MultithreadingDemo {

    public static void main(String[] args) throws InterruptedException {

        HotelService hotel = new HotelService();

        // Make sure room 201 is available before the test
        if (hotel.findRoom(201) == null) {
            System.out.println("Room 201 does not exist!");
            return;
        }

        if (hotel.findRoom(201).isBooked()) {
            hotel.checkoutRoom(201);
        }

        System.out.println("Room 201 is available.");
        System.out.println("\n=== Starting Two Customers ===");

        // Task performed by both customers
        Runnable bookingTask = () -> {

            try {
                hotel.bookRoom(201, 2);

            } catch (Exception e) {
                System.out.println(
                        Thread.currentThread().getName()
                                + " failed: " + e.getMessage()
                );
            }
        };

        // Create two threads
        Thread customerA = new Thread(
                bookingTask, "Customer-A"
        );

        Thread customerB = new Thread(
                bookingTask, "Customer-B"
        );

        // Start both threads
        customerA.start();
        customerB.start();

        // Wait for both threads to finish
        customerA.join();
        customerB.join();

        System.out.println("\n=== Final Room Status ===");
        System.out.println(
                "Room 201 booked? " +
                        hotel.findRoom(201).isBooked()
        );
    }
}
