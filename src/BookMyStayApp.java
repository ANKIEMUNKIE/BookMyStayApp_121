/**
 * BookMyStayApp.java
 * * Concepts demonstrated:
 * 1. Abstraction: Room cannot be instantiated.
 * 2. Inheritance: Specialized rooms reuse base logic.
 * 3. Encapsulation: Room data is protected via private fields.
 * 4. Static State: Availability is managed via simple variables (for now).
 */

// --- DOMAIN MODEL ---

abstract class Room {
    private String type;
    private int beds;
    private double pricePerNight;

    // Constructor used by subclasses to define their unique identity
    public Room(String type, int beds, double pricePerNight) {
        this.type = type;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
    }

    // A common behavior for all room types
    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds:      " + beds);
        System.out.println("Price:     $" + pricePerNight + " per night");
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Standard", 1, 100.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Deluxe", 2, 180.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Executive Suite", 3, 350.0);
    }
}

// --- MAIN APPLICATION ---

public class BookMyStayApp {
    public static void main(String[] args) {
        // 1. Instantiate Room Objects
        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        // 2. Represent Availability (Static state management)
        // This highlights the need for Data Structures in future lessons
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 1;

        System.out.println("========================================");
        System.out.println("   WELCOME TO BOOKMYSTAY INVENTORY      ");
        System.out.println("========================================\n");

        // 3. Display Information using Polymorphism
        // Even though these are different subclasses, we interact with them as 'Room'

        single.displayDetails();
        System.out.println("STATUS:    " + singleAvailable + " rooms remaining.");
        System.out.println("----------------------------------------");

        doubleRm.displayDetails();
        System.out.println("STATUS:    " + doubleAvailable + " rooms remaining.");
        System.out.println("----------------------------------------");

        suite.displayDetails();
        System.out.println("STATUS:    " + suiteAvailable + " rooms remaining.");
        System.out.println("----------------------------------------");

        System.out.println("\nApplication Terminated.");
    }
}