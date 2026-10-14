package ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUser {
    private static final Scanner scanner = new Scanner(System.in);

    // Read any text, trimmed
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Read an integer between min and max, repeat until valid
    public static int getInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Enter a value between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    // Read a best-before date: from today up to 14 days ahead
    public static LocalDate getBestBeforeDate() {
        LocalDate today = LocalDate.now();
        LocalDate max = today.plusDays(14);
        while (true) {
            System.out.print("Best-before date (yyyy-MM-dd, up to " + max + "): ");
            String input = scanner.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(input);
                if (!date.isBefore(today) && !date.isAfter(max)) {
                    return date;
                }
                System.out.println("Date must be between today and 14 days ahead.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Use yyyy-MM-dd.");
            }
        }
    }
}