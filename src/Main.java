import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // UC1: Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // UC1: Initialize Train Consist
        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial number of bogies: " + trainConsist.size());

        System.out.println("System initialized successfully.");

        // =========================
        // UC2 STARTS HERE
        // =========================

        // Add bogies
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("\nAfter Adding Bogies:");
        System.out.println(trainConsist);

        // Remove bogie
        trainConsist.remove("AC Chair");

        System.out.println("\nAfter Removing AC Chair:");
        System.out.println(trainConsist);

        // Check existence
        boolean exists = trainConsist.contains("Sleeper");
        System.out.println("\nIs Sleeper present? " + exists);

        // Final list
        System.out.println("\nFinal Bogie List:");
        System.out.println(trainConsist);
    }
}