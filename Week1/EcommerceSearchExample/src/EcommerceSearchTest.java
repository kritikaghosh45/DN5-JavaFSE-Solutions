/*
 * ANALYSIS:
 * Linear Search: O(n) - worst case checks every element. Works on unsorted data.
 * Binary Search: O(log n) - worst case repeatedly halves the search space. Requires sorted data.
 *
 * For an e-commerce platform with a large, frequently-searched product catalog,
 * Binary Search is far more suitable once the data is sorted (e.g. by productId),
 * since search time grows much more slowly as the catalog size increases.
 * Linear Search is only preferable for very small or rarely-searched datasets,
 * or when the data changes too often to justify maintaining a sorted order.
 */

public class EcommerceSearchTest {
    public static void main(String[] args) {
        // Unsorted array for linear search
        Product[] products = {
            new Product(105, "Wireless Mouse", "Electronics"),
            new Product(102, "Yoga Mat", "Fitness"),
            new Product(110, "Coffee Maker", "Home"),
            new Product(101, "Bluetooth Speaker", "Electronics"),
            new Product(108, "Running Shoes", "Footwear")
        };

        // Sorted array (by productId) for binary search
        Product[] sortedProducts = {
            new Product(101, "Bluetooth Speaker", "Electronics"),
            new Product(102, "Yoga Mat", "Fitness"),
            new Product(105, "Wireless Mouse", "Electronics"),
            new Product(108, "Running Shoes", "Footwear"),
            new Product(110, "Coffee Maker", "Home")
        };

        int targetId = 108;

        long startLinear = System.nanoTime();
        Product foundLinear = SearchAlgorithms.linearSearch(products, targetId);
        long endLinear = System.nanoTime();

        long startBinary = System.nanoTime();
        Product foundBinary = SearchAlgorithms.binarySearch(sortedProducts, targetId);
        long endBinary = System.nanoTime();

        System.out.println("Linear Search Result: " + foundLinear);
        System.out.println("Linear Search Time: " + (endLinear - startLinear) + " ns");

        System.out.println("Binary Search Result: " + foundBinary);
        System.out.println("Binary Search Time: " + (endBinary - startBinary) + " ns");
    }
}