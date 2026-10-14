package app;

import java.time.LocalDate;
import model.FoodItem;
import storage.FoodStorage;

public class Scratch {
    public static void main(String[] args) {
        FoodStorage s = new FoodStorage();
        LocalDate d = LocalDate.now().plusDays(3);
        s.addRear(new FoodItem("Burger", 200, d));
        s.addRear(new FoodItem("Pizza", 300, d));
        s.addRear(new FoodItem("Fries", 150, d));
        System.out.println(s.removeFront().getName());  // expect Burger
        System.out.println(s.removeRear().getName());   // expect Fries
        System.out.println(s.peekFront().getName());    // expect Pizza
    }
}