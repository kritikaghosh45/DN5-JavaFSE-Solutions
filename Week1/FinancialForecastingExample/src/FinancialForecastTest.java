/*
 * ANALYSIS:
 * Time Complexity: O(n), where n = number of years. Each call does constant work
 * and recurses exactly once, so the recursion depth and total calls both scale linearly with years.
 *
 * Optimization: This recursion is NOT tail-call optimized by the JVM, so for very large
 * numbers of years it risks a StackOverflowError. It can be optimized by:
 *  1. Converting to an iterative loop (no call stack growth at all), or
 *  2. Using the closed-form formula directly: futureValue = principal * (1 + growthRate)^years,
 *     which runs in O(log n) using fast exponentiation, or O(1) using Math.pow().
 */

public class FinancialForecastTest {
    public static void main(String[] args) {
        double initialInvestment = 10000.0;
        double annualGrowthRate = 0.07; // 7% growth per year
        int yearsToForecast = 5;

        double futureValue = ForecastCalculator.calculateFutureValue(
                initialInvestment, annualGrowthRate, yearsToForecast);

        System.out.println("Initial Investment: " + initialInvestment);
        System.out.println("Annual Growth Rate: " + (annualGrowthRate * 100) + "%");
        System.out.println("Years: " + yearsToForecast);
        System.out.printf("Forecasted Future Value: %.2f%n", futureValue);
    }
}