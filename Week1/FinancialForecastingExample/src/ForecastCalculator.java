public class ForecastCalculator {

    // Recursive method to calculate future value
    // principal: starting amount
    // growthRate: e.g. 0.05 for 5% growth per period
    // years: number of periods remaining
    public static double calculateFutureValue(double principal, double growthRate, int years) {
        // Base case: no more years to grow
        if (years == 0) {
            return principal;
        }
        // Recursive case: grow once, then recurse for remaining years
        return calculateFutureValue(principal * (1 + growthRate), growthRate, years - 1);
    }
}