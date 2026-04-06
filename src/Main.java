import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        // =========================
        // UC1: Initialize Train
        // =========================
        System.out.println("=== Train Consist Management App ===");

        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial number of bogies: " + trainConsist.size());

        System.out.println("System initialized successfully.");

        // =========================
        // UC2: ArrayList Operations
        // =========================

        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("\nPassenger Bogies after addition:");
        System.out.println(trainConsist);

        trainConsist.remove("AC Chair");

        System.out.println("\nPassenger Bogies after removal:");
        System.out.println(trainConsist);

        boolean exists = trainConsist.contains("Sleeper");
        System.out.println("\nIs Sleeper present? " + exists);

        System.out.println("\nFinal Bogie List:");
        System.out.println(trainConsist);

        // =========================
        // UC3: HashSet (Unique IDs)
        // =========================

        Set<String> bogieIds = new HashSet<>();

        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101"); // duplicate
        bogieIds.add("BG103");
        bogieIds.add("BG102"); // duplicate

        System.out.println("\nUnique Bogie IDs:");
        System.out.println(bogieIds);

        // =========================
        // UC4: LinkedList (Ordered Train)
        // =========================

        LinkedList<String> train = new LinkedList<>();

        train.add("Engine");
        train.add("Sleeper");
        train.add("AC");
        train.add("Cargo");
        train.add("Guard");

        System.out.println("\nInitial Train Consist:");
        System.out.println(train);

        // Insert Pantry at position 2
        train.add(2, "Pantry");

        System.out.println("\nAfter Adding Pantry at position 2:");
        System.out.println(train);

        // Remove first and last
        train.removeFirst();
        train.removeLast();

        System.out.println("\nAfter Removing First and Last:");
        System.out.println(train);
    }
}