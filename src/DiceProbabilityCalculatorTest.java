import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class DiceProbabilityCalculatorTest {

    // Normal cases

    @Test
    void testRollDiceOnceWithinValidRange() {
        int numberOfSides = 6;
        int numberOfDice = 2;

        int result = DiceProbabilityCalculator.rollDiceOnce(numberOfSides, numberOfDice);

        assertTrue(result >= 2 && result <= 12);
    }

    @Test
    void testSimulateMultipleRollsProducesCorrectTotalCount() {
        int numberOfSides = 6;
        int numberOfDice = 2;
        int numberOfRolls = 1000;

        Map<Integer, Integer> rollCounts =
                DiceProbabilityCalculator.simulateMultipleRolls(numberOfSides, numberOfDice, numberOfRolls);

        int totalCount = 0;
        for (int count : rollCounts.values()) {
            totalCount += count;
        }

        assertEquals(numberOfRolls, totalCount);
    }

    @Test
    void testProbabilityDistributionSumsCloseToOne() {
        int numberOfSides = 6;
        int numberOfDice = 2;
        int numberOfRolls = 5000;

        Map<Integer, Integer> rollCounts =
                DiceProbabilityCalculator.simulateMultipleRolls(numberOfSides, numberOfDice, numberOfRolls);

        Map<Integer, Double> probabilityDistribution =
                DiceProbabilityCalculator.calculateProbabilityDistribution(rollCounts, numberOfRolls);

        double totalProbability = 0.0;
        for (double probability : probabilityDistribution.values()) {
            totalProbability += probability;
        }

        assertEquals(1.0, totalProbability, 0.0001);
    }

    // Edge cases

    @Test
    void testOneDieOneSideAlwaysReturnsOne() {
        int result = DiceProbabilityCalculator.rollDiceOnce(1, 1);
        assertEquals(1, result);
    }

    @Test
    void testSingleRollCreatesExactlyOneRecordedOutcome() {
        Map<Integer, Integer> rollCounts =
                DiceProbabilityCalculator.simulateMultipleRolls(6, 2, 1);

        int totalCount = 0;
        for (int count : rollCounts.values()) {
            totalCount += count;
        }

        assertEquals(1, totalCount);
    }

    @Test
    void testCalculateProbabilityDistributionWithKnownCounts() {
        Map<Integer, Integer> rollCounts = new TreeMap<>();
        rollCounts.put(2, 1);
        rollCounts.put(3, 2);
        rollCounts.put(4, 1);

        Map<Integer, Double> probabilityDistribution =
                DiceProbabilityCalculator.calculateProbabilityDistribution(rollCounts, 4);

        assertEquals(0.25, probabilityDistribution.get(2), 0.00001);
        assertEquals(0.50, probabilityDistribution.get(3), 0.00001);
        assertEquals(0.25, probabilityDistribution.get(4), 0.00001);
    }
}