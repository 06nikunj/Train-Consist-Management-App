import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

        train.add(2, "Pantry");

        System.out.println("\nAfter Adding Pantry at position 2:");
        System.out.println(train);

        train.removeFirst();
        train.removeLast();

        System.out.println("\nAfter Removing First and Last:");
        System.out.println(train);

        // =========================
        // UC5: LinkedHashSet (Order + Unique)
        // =========================

        LinkedHashSet<String> formation = new LinkedHashSet<>();

        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper"); // duplicate ignored

        System.out.println("\nTrain Formation (Insertion Order + Unique):");
        System.out.println(formation);

        // =========================
        // UC6: HashMap (Bogie → Capacity)
        // =========================

        Map<String, Integer> capacityMap = new HashMap<>();

        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);
        capacityMap.put("First Class", 24);

        System.out.println("\nBogie Capacity Details:");
        for (Map.Entry<String, Integer> entry : capacityMap.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        // =========================
        // UC7: Comparator (Sort Bogies)
        // =========================

        List<Bogie> bogieList = new ArrayList<>();

        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 60));
        bogieList.add(new Bogie("First Class", 24));

        System.out.println("\nBefore Sorting:");
        for (Bogie b : bogieList) {
            System.out.println(b.name + " - " + b.capacity);
        }

        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("\nAfter Sorting (by capacity):");
        for (Bogie b : bogieList) {
            System.out.println(b.name + " - " + b.capacity);
        }

        // =========================
        // UC8: Stream API (Filter Bogies)
        // =========================

        List<Bogie> filteredBogies = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered Bogies (Capacity > 60):");
        for (Bogie b : filteredBogies) {
            System.out.println(b.name + " - " + b.capacity);
        }
 // =========================
// UC9: Group Bogies by Type
// =========================

Map<String, List<Bogie>> groupedBogies = bogieList.stream()
        .collect(Collectors.groupingBy(b -> b.name));

System.out.println("\nGrouped Bogies:");

for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
    System.out.println(entry.getKey() + " → ");

    for (Bogie b : entry.getValue()) {
        System.out.println("   " + b.name + " - " + b.capacity);
    }
// =========================
// UC10: Total Seat Calculation (reduce)
// =========================

int totalCapacity = bogieList.stream()
        .map(b -> b.capacity)
        .reduce(0, Integer::sum);

System.out.println("\nTotal Seating Capacity of Train:");
System.out.println(totalCapacity);
}
    // =========================
// UC11: Regex Validation
// =========================

// Sample inputs (you can later take from user)
String trainId = "TRN-1234";
String cargoCode = "PET-AB";

// Define regex patterns
Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

// Create matchers
Matcher trainMatcher = trainPattern.matcher(trainId);
Matcher cargoMatcher = cargoPattern.matcher(cargoCode);

// Validate Train ID
if (trainMatcher.matches()) {
    System.out.println("\nTrain ID is VALID: " + trainId);
} else {
    System.out.println("\nTrain ID is INVALID: " + trainId);
}

// Validate Cargo Code
if (cargoMatcher.matches()) {
    System.out.println("Cargo Code is VALID: " + cargoCode);
} else {
    System.out.println("Cargo Code is INVALID: " + cargoCode);
}
// =========================
// UC12: Safety Compliance Check
// =========================

List<GoodsBogie> goodsList = new ArrayList<>();

// Add goods bogies
goodsList.add(new GoodsBogie("Cylindrical", "Petroleum")); // valid
goodsList.add(new GoodsBogie("Box", "Coal"));              // valid
goodsList.add(new GoodsBogie("Open", "Grain"));            // valid

// Safety check using allMatch()
boolean isSafe = goodsList.stream()
        .allMatch(b ->
                !b.type.equals("Cylindrical") ||
                b.cargo.equals("Petroleum")
        );

System.out.println("\nTrain Safety Compliance:");
if (isSafe) {
    System.out.println("Train is SAFE for operation");
} else {
    System.out.println("Train is NOT SAFE");
} 
    }
}
