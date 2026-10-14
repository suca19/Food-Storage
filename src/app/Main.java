package app;

import exception.StorageEmptyException;
import exception.StorageFullException;
import java.time.LocalDate;
import model.FoodItem;
import storage.FoodStorage;
import ui.InputUser;

// Entry point: menu-driven console app for the food storage unit
public class Main {

    // The five food types the restaurant offers
    private static final String[] TYPES = {"Burger", "Pizza", "Fries", "Sandwich", "Hotdog"};
    // One deque models the storage unit; the mode decides which end we remove from
    private static final FoodStorage storage = new FoodStorage();
    private static boolean sameEnd = true;   // true: add + remove at rear (LIFO)
                                             // false: add at rear, remove at front (FIFO)

    public static void main(String[] args) {
        boolean running = true;

        // Keep showing the menu until the user chooses Exit

        while (running) {
            System.out.println("\nMode: " + (sameEnd ? "Same end (LIFO)" : "Opposite end (FIFO)"));
            System.out.println("1 Add | 2 Remove | 3 Show top | 4 Display all | 5 Search | 6 Switch mode | 7 Exit");
            int choice = InputUser.getInt("Option: ", 1, 7);

            // Storage errors (full/empty) are caught here so the app never crashes
            try {
                switch (choice) {
                    case 1: addItem(); break;
                    case 2: removeItem(); break;
                    case 3: showTop(); break;
                    case 4: displayAll(); break;
                    case 5: searchItem(); break;
                    case 6: switchMode(); break;
                    case 7:
                        running = false;
                        System.out.println("Goodbye.");
                        break;
                }
            } catch (StorageEmptyException | StorageFullException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Ask for type, weight and best-before date, then add the item at the rear. O(1)
    private static void addItem() {
        // Check capacity first so the user doesn't type details for nothing
        if (storage.isFull()) {
            throw new StorageFullException(FoodStorage.MAX_CAPACITY);
        }
        System.out.println("1 Burger | 2 Pizza | 3 Fries | 4 Sandwich | 5 Hotdog");
        int type = InputUser.getInt("Type: ", 1, 5);
        int weight = InputUser.getInt("Weight (g): ", 1, 5000);
        LocalDate bestBefore = InputUser.getBestBeforeDate();

        FoodItem item = new FoodItem(TYPES[type - 1], weight, bestBefore);
        storage.addRear(item);
        System.out.println("Added: " + item);
    }

    // Same end: remove from rear (LIFO). Opposite end: remove from front (FIFO). O(1)
    private static void removeItem() {
        FoodItem removed = sameEnd ? storage.removeRear() : storage.removeFront();
        System.out.println("Removed: " + removed);
    }

    // Show the name of the item that would be removed next. O(1)
    private static void showTop() {
        FoodItem top = sameEnd ? storage.peekRear() : storage.peekFront();
        System.out.println("Top item: " + top.getName());
    }

    // List every item from front to rear. O(n)
    private static void displayAll() {
        FoodItem[] all = storage.getAllFoodItems();
        for (int i = 0; i < all.length; i++) {
            System.out.println((i + 1) + ". " + all[i]);
        }
    }

    // Find the first item with a matching name (case-insensitive). O(n)
    private static void searchItem() {
        String name = InputUser.getString("Name to search: ");
        FoodItem found = storage.search(name);
        System.out.println(found == null ? "Not found." : "Found: " + found);
    }

    // Toggle between the two scenarios from the brief
    private static void switchMode() {
        sameEnd = !sameEnd;
        System.out.println("Mode switched to: " + (sameEnd ? "Same end (LIFO)" : "Opposite end (FIFO)"));
    }
}