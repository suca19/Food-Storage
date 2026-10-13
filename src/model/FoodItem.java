package model;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FoodItem {
    
    //private final attribute to store the name of the food item;
    private final String name;
    private final int weight;
    private final LocalDate bestBeforeDate;
    private final LocalDateTime addedAt;
    
    //Contructor to initialize the food item with name, weight, and best before date
    public FoodItem(String name, int weight, LocalDate bestBeforeDate) {
        this.name = name;
        this.weight = weight;
        this.bestBeforeDate = bestBeforeDate;
        this.addedAt = LocalDateTime.now(); // Set the addedAt attribute to the current date and time
    }

    //Getters methods here

    public String getName(){
        return name;
    }

    public int getWeight(){
        return weight;
    }

    public LocalDate getBestBeforeDate(){
        return bestBeforeDate;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    //Override the toString method to provide a string representation of the food item
    @Override
    public String toString() {
        return String.format("FoodItem{name='%s', weight=%d, bestBeforeDate=%s, addedAt=%s}", name, weight, bestBeforeDate, addedAt);
    }
}