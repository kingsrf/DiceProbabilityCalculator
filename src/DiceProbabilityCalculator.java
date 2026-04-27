import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class DiceProbabilityCalculator {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Probability Calculator for N-sided Dice ===");

        boolean continueRunning = true;

        while (continueRunning) {
            int numberOfSides = promptPositiveInt(scanner, "Enter N (number of sides per die): ");
            int numberOfDice = promptPositiveInt(scanner, "Enter M (number of dice rolled): ");
            int numberOfRolls = promptPositiveInt(scanner, "Enter K (number of simulations): ");

            Map<Integer, Integer> rollCounts = simulateMultipleRolls(numberOfSides, numberOfDice, numberOfRolls);
            Map<Integer, Double> probabilityDistribution = calculateProbabilityDistribution(rollCounts, numberOfRolls);

            displayResults(numberOfSides, numberOfDice, numberOfRolls, probabilityDistribution);

            continueRunning = promptYesNo(scanner, "\nDo you want to run another simulation? (y/n): ");
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    // Task 1: Roll M number of N-sided dice once and return the sum
    public static int rollDiceOnce(int numberOfSides, int numberOfDice) {
        int sum = 0;

        for (int die = 0; die < numberOfDice; die++) {
            sum += RANDOM.nextInt(numberOfSides) + 1;
        }

        return sum;
    }

    // Task 2: Simulate rolling M number of N-sided dice K times
    public static Map<Integer, Integer> simulateMultipleRolls(int numberOfSides, int numberOfDice, int numberOfRolls) {
        Map<Integer, Integer> rollCounts = new TreeMap<>();

        for (int roll = 0; roll < numberOfRolls; roll++) {
            int sum = rollDiceOnce(numberOfSides, numberOfDice);
            rollCounts.put(sum, rollCounts.getOrDefault(sum, 0) + 1);
        }

        return rollCounts;
    }

    // Task 3: Calculate probability distribution
    public static Map<Integer, Double> calculateProbabilityDistribution(Map<Integer, Integer> rollCounts, int numberOfRolls) {
        Map<Integer, Double> probabilityDistribution = new TreeMap<>();

        for (Map.Entry<Integer, Integer> entry : rollCounts.entrySet()) {
            int sum = entry.getKey();
            int count = entry.getValue();
            double probability = (double) count / numberOfRolls;

            probabilityDistribution.put(sum, probability);
        }

        return probabilityDistribution;
    }

    // Task 4: Display results
    public static void displayResults(int numberOfSides, int numberOfDice, int numberOfRolls,
                                      Map<Integer, Double> probabilityDistribution) {
        System.out.println("\nSimulation Results");
        System.out.println("N = " + numberOfSides + ", M = " + numberOfDice + ", K = " + numberOfRolls);
        System.out.println("\nSum\tProbability");

        for (Map.Entry<Integer, Double> entry : probabilityDistribution.entrySet()) {
            System.out.printf("%d\t%.5f%n", entry.getKey(), entry.getValue());
        }
    }

    public static int promptPositiveInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);

            if (!scanner.hasNextInt()) {
                System.out.println("Error: Please enter a valid integer.");
                scanner.next();
                continue;
            }

            int value = scanner.nextInt();

            if (value <= 0) {
                System.out.println("Error: Value must be greater than 0.");
                continue;
            }

            return value;
        }
    }

    public static boolean promptYesNo(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String choice = scanner.next().trim().toLowerCase();

            if (choice.equals("y")) {
                return true;
            }

            if (choice.equals("n")) {
                return false;
            }

            System.out.println("Error: Please enter 'y' or 'n'.");
        }
    }
}