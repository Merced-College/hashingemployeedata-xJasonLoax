// Jason Loa, Miguel Mendoza
// 11/6/2025
// Processes employee data using a chaining hash table

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // ChainingHashTable using zyBook code
        ChainingHashTable<String, Employee> table = new ChainingHashTable<>(11);

        // ArrayList to store duplicate Employee objects
        ArrayList<Employee> duplicates = new ArrayList<>();

        // Counters
        int totalLoaded = 0;
        int duplicatesFound = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("Employee_data.csv"))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] cols = line.split(",", -1);
                if (cols.length < 6) continue;

                // Create the Employee object from the CSV
                Employee emp = new Employee(
                        cols[0].trim(),  // LAST NAME
                        cols[1].trim(),  // FIRST NAME
                        cols[2].trim(),  // JOB TITLE
                        cols[3].trim(),  // DEPARTMENT
                        parseMoney(cols[4]),
                        parseMoney(cols[5])
                );

                totalLoaded++;

                // Hash key: last + first name
                String key = (emp.lastName + emp.firstName).toLowerCase();

                // Check for duplicates
                Employee existing = table.get(key);
                if (existing != null) {
                    if (existing.department.equalsIgnoreCase(emp.department)) {
                        duplicates.add(emp);
                        duplicatesFound++;
                    } else {
                        table.insert(key, emp);
                    }
                } else {
                    table.insert(key, emp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Employee testEmp = new Employee(
            "Loa",
            "Jason",
            "Engineer",
            "Sales",
            75000.0,
            72000.0
        );
        String testKey = (testEmp.lastName + testEmp.firstName).toLowerCase();
        table.insert(testKey, testEmp);
        System.out.println("Test employee inserted: " + table.get(testKey));

        // === SEARCH SECTION ===
        System.out.println();
        System.out.println("=== Employee Search ===");
        Employee found = searchByName(table, "Loa", "Jason");
        if (found != null) {
            System.out.println("Employee Found: " + found);
        } else {
            System.out.println("Employee not found");
        }
        System.out.println();

        // === REMOVE SECTION ===
        System.out.println("=== Remove Employee ===");
        String removeKey = ("Loa" + "Jason").toLowerCase();
        table.remove(removeKey);
        System.out.println("Jason Loa has been removed.");
        
        // Verify removal
        Employee afterRemoval = searchByName(table, "Loa", "Jason");
        if (afterRemoval == null) {
            System.out.println("Verification: Employee successfully removed from table.");
        } else {
            System.out.println("Verification: Employee still exists in table.");
        }
        System.out.println();

        // === OUTPUT SECTION ===
        System.out.println("=== Employee Hash Table Report ===");
        System.out.println("Total Employees Loaded: " + totalLoaded);
        System.out.println("Duplicate Employees Found: " + duplicatesFound);
        System.out.println();

        // Print duplicates (if any)
        if (!duplicates.isEmpty()) {
            System.out.println("Duplicate Entries:");
            for (Employee dup : duplicates) {
                System.out.println("  " + dup);
            }
        } else {
            System.out.println("No duplicates found.");
        }
    }

    // Helper: cleans up salary strings like "$45,000" → 45000.0
    private static double parseMoney(String s) {
        if (s == null || s.isBlank()) return 0.0;
        try {
            return Double.parseDouble(s.replace("$", "").replace(",", "").trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // Search for an employee by name
    private static Employee searchByName(ChainingHashTable<String, Employee> table, String lastName, String firstName) {
        String key = (lastName + firstName).toLowerCase();
        return table.get(key);
    }
}
