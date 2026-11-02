package model;

public abstract class Room {
    private final int roomNumber;
    private final double basePrice;   // price per night
    private boolean booked;

    public Room(int roomNumber, double basePrice) {
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
        this.booked = false;
    }

    public int getRoomNumber() { return roomNumber; }
    public double getBasePrice() { return basePrice; }
    public boolean isBooked() { return booked; }
    public void setBooked(boolean booked) { this.booked = booked; }

    // every room type must define its own pricing
    public abstract double calculatePrice(int nights);
}
