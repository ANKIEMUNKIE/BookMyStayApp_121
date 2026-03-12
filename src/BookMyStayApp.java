import java.util.HashMap;
import java.util.Map;

/**
 * BookMyStayApp.java - UC3: Centralized Inventory Management
 * This version replaces scattered variables with a HashMap-based RoomInventory.
 */

// --- 1. DOMAIN MODEL (What a Room is) ---

abstract class Room {
    private String type;
    private int beds;
    private double pricePerNight;

    public Room(String type, int beds, double pricePerNight) {
        this.type = type;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
    }

    public String getType() { return type; }

    public void displayDetails() {
        System.out.println(String.format("%-18s | Beds: %d | Price: $%.2f", type, beds, pricePerNight));
    }
}

class SingleRoom extends Room {
    public SingleRoom() { super("Single Standard", 1, 100.0); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Deluxe", 2, 180.0); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Executive Suite", 3, 350.0); }
}

// --- 2. INVENTORY COMPONENT (State Management) ---

class RoomInventory {
    // HashMap maps the Room Type (String) to the Count (Integer)
    private Map<String, Integer> inventory;

    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    // Registering a room type with O(1) insertion
    public void registerRoom(Room room, int initialCount) {
        inventory.put(room.getType(), initialCount);
    }

    // O(1) Lookup
    public int getCount(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Controlled State Update
    public void updateCount(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, Math.max(0, newCount)); // Ensure no negative inventory
        }
    }

    public void displayFullInventory() {
        System.out.println("--- Current Inventory Status ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(String.format("%-18s : %d available", entry.getKey(), entry.getValue()));
        }
        System.out.println("--------------------------------");
    }
}

// --- 3. MAIN APPLICATION ---

public class BookMyStayApp {
    public static void main(String[] args) {
        // Initialize Domain Objects
        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize Centralized Inventory
        RoomInventory hotelInventory = new RoomInventory();

        // Registering room types into the HashMap
        hotelInventory.registerRoom(single, 5);
        hotelInventory.registerRoom(doubleRm, 3);
        hotelInventory.registerRoom(suite, 1);

        System.out.println("==========================================");
        System.out.println("      BOOKMYSTAY CENTRALIZED SYSTEM       ");
        System.out.println("==========================================\n");

        // Display initial state
        hotelInventory.displayFullInventory();

        // --- SIMULATING A BOOKING ---
        System.out.println("\n[Action] Booking 1 Double Deluxe...");

        String targetType = doubleRm.getType();
        int currentCount = hotelInventory.getCount(targetType);

        if (currentCount > 0) {
            hotelInventory.updateCount(targetType, currentCount - 1);
            System.out.println("Booking Successful!");
        }

        // Display final state
        System.out.println();
        hotelInventory.displayFullInventory();

        System.out.println("\nApplication Terminated.");
    }
}