import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean isBooked;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isBooked = false;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isBooked() { return isBooked; }

    public void bookRoom() { isBooked = true; }
    public void cancelRoom() { isBooked = false; }

    public String toString() {
        return "Room No: " + roomNumber +
                " | Category: " + category +
                " | Price: ₹" + price +
                " | Status: " + (isBooked ? "Booked" : "Available");
    }
}

class Booking {
    private static int counter = 1000;
    private int bookingId;
    private String customerName;
    private Room room;
    private String bookingTime;

    public Booking(String customerName, Room room) {
        this.bookingId = ++counter;
        this.customerName = customerName;
        this.room = room;
        this.bookingTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public int getBookingId() { return bookingId; }
    public Room getRoom() { return room; }

    public String toString() {
        return "Booking ID: " + bookingId +
                " | Customer: " + customerName +
                " | Room: " + room.getRoomNumber() +
                " | Category: " + room.getCategory() +
                " | Paid: ₹" + room.getPrice() +
                " | Date: " + bookingTime;
    }
}

public class HotelReservationSystem {

    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        initializeRooms();
        int choice;

        do {
            System.out.println("\n====== HOTEL RESERVATION SYSTEM ======");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Search Room by Category");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Booking");
            System.out.println("5. View All Bookings");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Enter number only.");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: viewAvailableRooms(); break;
                case 2: searchByCategory(); break;
                case 3: bookRoom(); break;
                case 4: cancelBooking(); break;
                case 5: viewBookings(); break;
                case 6: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 6);
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 2000));
        rooms.add(new Room(102, "Standard", 2000));
        rooms.add(new Room(201, "Deluxe", 3500));
        rooms.add(new Room(202, "Deluxe", 3500));
        rooms.add(new Room(301, "Suite", 5000));
    }

    private static void viewAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room room : rooms)
            if (!room.isBooked())
                System.out.println(room);
    }

    private static void searchByCategory() {
        System.out.print("Enter category (Standard/Deluxe/Suite): ");
        String category = sc.nextLine();

        boolean found = false;
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && !room.isBooked()) {
                System.out.println(room);
                found = true;
            }
        }
        if (!found)
            System.out.println("No available rooms in this category.");
    }

    private static void bookRoom() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        viewAvailableRooms();
        System.out.print("Enter room number: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid room number.");
            sc.next();
            return;
        }

        int roomNumber = sc.nextInt();
        sc.nextLine();

        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && !room.isBooked()) {
                System.out.println("Room Price: ₹" + room.getPrice());
                System.out.print("Confirm payment (yes/no): ");
                String confirm = sc.nextLine();

                if (confirm.equalsIgnoreCase("yes")) {
                    room.bookRoom();
                    Booking booking = new Booking(name, room);
                    bookings.add(booking);
                    System.out.println("Booking Successful!");
                    System.out.println(booking);
                } else {
                    System.out.println("Payment cancelled.");
                }
                return;
            }
        }
        System.out.println("Room not available.");
    }

    private static void cancelBooking() {
        System.out.print("Enter Booking ID to cancel: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid ID.");
            sc.next();
            return;
        }

        int id = sc.nextInt();
        sc.nextLine();

        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.getBookingId() == id) {
                booking.getRoom().cancelRoom();
                iterator.remove();
                System.out.println("Booking cancelled successfully.");
                return;
            }
        }
        System.out.println("Booking ID not found.");
    }

    private static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        for (Booking booking : bookings)
            System.out.println(booking);
    }
}