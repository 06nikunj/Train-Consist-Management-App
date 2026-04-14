import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // =========================
        // UC1
        // =========================
        System.out.println("=== Train Consist Management App ===");

        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial number of bogies: " + trainConsist.size());
        System.out.println("System initialized successfully.");

        // =========================
        // UC2
        // =========================
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("\nPassenger Bogies after addition:");
        System.out.println(trainConsist);

        trainConsist.remove("AC Chair");

        System.out.println("\nPassenger Bogies after removal:");
        System.out.println(trainConsist);

        System.out.println("Is Sleeper present? " + trainConsist.contains("Sleeper"));

        // =========================
        // UC3
        // =========================
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101");

        System.out.println("\nUnique Bogie IDs:");
        System.out.println(bogieIds);

        // =========================
        // UC4
        // =========================
        LinkedList<String> train = new LinkedList<>();
        train.add("Engine");
        train.add("Sleeper");
        train.add("AC");
        train.add("Cargo");
        train.add("Guard");

        train.add(2, "Pantry");
        train.removeFirst();
        train.removeLast();

        System.out.println("\nOrdered Train:");
        System.out.println(train);

        // =========================
        // UC5
        // =========================
        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println("\nFormation:");
        System.out.println(formation);

        // =========================
        // UC6
        // =========================
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);
        capacityMap.put("First Class", 24);

        System.out.println("\nCapacity:");
        for (Map.Entry<String, Integer> e : capacityMap.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        // =========================
        // UC7
        // =========================
        List<Bogie> bogieList = new ArrayList<>();

        try {
            bogieList.add(new Bogie("Sleeper", 72));
            bogieList.add(new Bogie("AC Chair", 60));
            bogieList.add(new Bogie("First Class", 24));
        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
        }

        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("\nSorted Bogies:");
        for (Bogie b : bogieList) {
            System.out.println(b.name + " - " + b.capacity);
        }

        // =========================
        // UC8
        // =========================
        List<Bogie> filtered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered:");
        filtered.forEach(b -> System.out.println(b.name));

        // =========================
        // UC9
        // =========================
        Map<String, List<Bogie>> grouped =
                bogieList.stream().collect(Collectors.groupingBy(b -> b.name));

        System.out.println("\nGrouped:");
        grouped.forEach((k, v) -> System.out.println(k + " -> " + v.size()));

        // =========================
        // UC10
        // =========================
        int total = bogieList.stream().map(b -> b.capacity).reduce(0, Integer::sum);
        System.out.println("\nTotal Capacity: " + total);

        // =========================
        // UC11
        // =========================
        Pattern p = Pattern.compile("TRN-\\d{4}");
        Matcher m = p.matcher("TRN-1234");
        System.out.println("\nTrain ID Valid: " + m.matches());

        // =========================
        // UC12
        // =========================
        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goods.add(new GoodsBogie("Box", "Coal"));

        boolean safe = goods.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));

        System.out.println("\nSafety: " + safe);

        // =========================
        // UC13
        // =========================
        List<Bogie> testList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            try {
                testList.add(new Bogie("Sleeper", 72));
                testList.add(new Bogie("AC Chair", 60));
                testList.add(new Bogie("First Class", 24));
            } catch (InvalidCapacityException e) {}
        }

        long start = System.nanoTime();
        List<Bogie> loop = new ArrayList<>();
        for (Bogie b : testList) {
            if (b.capacity > 60) loop.add(b);
        }
        long loopTime = System.nanoTime() - start;

        start = System.nanoTime();
        List<Bogie> stream = testList.stream()
                .filter(b -> b.capacity > 60)
                .toList();
        long streamTime = System.nanoTime() - start;

        System.out.println("\nLoop Time: " + loopTime);
        System.out.println("Stream Time: " + streamTime);

        // =========================
        // UC14
        // =========================
        try {
            new Bogie("Invalid", 0);
        } catch (InvalidCapacityException e) {
            System.out.println("\nException: " + e.getMessage());
        }
    // =========================
// UC15: Final Train Summary
// =========================

System.out.println("\n=========================");
System.out.println("FINAL TRAIN SUMMARY");
System.out.println("=========================");

// Passenger bogies
System.out.println("\nPassenger Bogies:");
for (Bogie b : bogieList) {
    System.out.println(b.name + " - Capacity: " + b.capacity);
}

// Total capacity
int totalCapacity = bogieList.stream()
        .map(b -> b.capacity)
        .reduce(0, Integer::sum);

System.out.println("\nTotal Passenger Capacity: " + totalCapacity);

// Goods bogies
System.out.println("\nGoods Bogies:");
for (GoodsBogie g : goods) {
    System.out.println(g.type + " - Cargo: " + g.cargo);
}

// Safety status
boolean isSafeFinal = goods.stream()
        .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));

System.out.println("\nSafety Status: " + (isSafeFinal ? "SAFE" : "NOT SAFE"));

// Unique bogie count
System.out.println("\nTotal Unique Bogies: " + formation.size());

System.out.println("\n=========================");
System.out.println("SYSTEM EXECUTION COMPLETE");
System.out.println("=========================");
     
    
// =========================
// UC16: Bubble Sort (Manual Sorting)
// =========================

// Create array of capacities
int[] capacities = {72, 56, 24, 70, 60};

System.out.println("\nBefore Sorting:");
for (int c : capacities) {
    System.out.print(c + " ");
}

// Bubble Sort Logic
int n = capacities.length;

for (int i = 0; i < n - 1; i++) {
    for (int j = 0; j < n - i - 1; j++) {

        if (capacities[j] > capacities[j + 1]) {
            // swap
            int temp = capacities[j];
            capacities[j] = capacities[j + 1];
            capacities[j + 1] = temp;
        }
    }
}

// After sorting
System.out.println("\n\nAfter Sorting (Bubble Sort):");
for (int c : capacities) {
    System.out.print(c + " ");
}


// =========================
// UC17: Arrays.sort() (Alphabetical Sorting)
// =========================

// Create array of bogie names
String[] bogieNames = {
        "Sleeper",
        "AC Chair",
        "First Class",
        "General",
        "Luxury"
};

// Before sorting
System.out.println("\nBefore Sorting (Names):");
System.out.println(Arrays.toString(bogieNames));

// Sort using built-in method
Arrays.sort(bogieNames);

// After sorting
System.out.println("\nAfter Sorting (Alphabetical):");
System.out.println(Arrays.toString(bogieNames));


// =========================
// UC18: Linear Search (Bogie ID)
// =========================

// Array of bogie IDs
String[] bogieIdsArray = {
        "BG101", "BG205", "BG309", "BG412", "BG550"
};

// Search key
String searchKey = "BG309";   // change to test

boolean found = false;

// Linear Search
for (int i = 0; i < bogieIdsArray.length; i++) {

    if (bogieIdsArray[i].equals(searchKey)) {
        found = true;
        System.out.println("\nBogie Found at position: " + i);
        break; // stop when found
    }
}

// Result
if (!found) {
    System.out.println("\nBogie NOT Found");
}
}
}