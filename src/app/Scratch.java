package app;

import java.time.LocalDate;
import model.FoodItem;
import storage.FoodStorage;

// Quick manual tests for FoodStorage: run and compare output with the "expect" values
public class Scratch {
    public static void main(String[] args) {
        LocalDate d = LocalDate.now().plusDays(3);
        FoodStorage s = new FoodStorage();

        // Test 1: empty storage errors
        try {
            s.removeFront();
        } catch (RuntimeException e) {
            System.out.println("T1a OK: " + e.getMessage());
        }
        try {
            s.removeRear();
        } catch (RuntimeException e) {
            System.out.println("T1b OK: " + e.getMessage());
        }
        try {
            s.peekFront();
        } catch (RuntimeException e) {
            System.out.println("T1c OK: " + e.getMessage());
        }
        try {
            s.getAllFoodItems();
        } catch (RuntimeException e) {
            System.out.println("T1d OK: " + e.getMessage());
        }

        // Test 2: FIFO (add rear, remove front)
        s.addRear(new FoodItem("Burger", 200, d));
        s.addRear(new FoodItem("Pizza", 300, d));
        s.addRear(new FoodItem("Fries", 150, d));
        System.out.println("T2 expect Burger: " + s.removeFront().getName());

        // Test 3: LIFO (add rear, remove rear)
        System.out.println("T3 expect Fries: " + s.removeRear().getName());
        System.out.println("T3 peekFront expect Pizza: " + s.peekFront().getName());
        System.out.println("T3 peekRear expect Pizza: " + s.peekRear().getName());

        // Test 4: search
        System.out.println("T4 found: " + (s.search("pizza") != null));
        System.out.println("T4 missing: " + (s.search("Hotdog") == null));

        // Test 5: full storage (Pizza already inside, add 7 more = 8)
        for (int i = 0; i < 7; i++)
            s.addRear(new FoodItem("Hotdog", 100, d));
        System.out.println("T5 isFull expect true: " + s.isFull());
        try {
            s.addRear(new FoodItem("Extra", 50, d));
        } catch (RuntimeException e) {
            System.out.println("T5 OK: " + e.getMessage());
        }

        // Test 6: wrap-around (remove 3 from front, add 3 more, then print)
        s.removeFront();
        s.removeFront();
        s.removeFront();
        s.addRear(new FoodItem("Burger", 1, d));
        s.addRear(new FoodItem("Sandwich", 2, d));
        s.addRear(new FoodItem("Fries", 3, d));
        System.out.println("T6 expect size 8: " + s.size());
        System.out.println("T6 order (last 3 should be Burger, Sandwich, Fries):");
        for (FoodItem item : s.getAllFoodItems())
            System.out.println("  " + item.getName());
    }
}