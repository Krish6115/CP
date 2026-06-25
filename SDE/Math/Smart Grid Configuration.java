/**
 * Problem Name: Smart Grid Configuration: Identifying Valid Time Intervals
 * * Problem Description:
 * Given a single integer 'n' representing a configuration metric or total time limit, 
 * find all the valid intervals (which are all the unique factors/divisors of 'n').
 * The output must list all factors in strictly ascending (sorted) order, separated by a space.
 * * Input Format:
 * An integer 'n' (1 <= n <= 10^9). Note: The system may pass multiple test cases 
 * sequentially in the input stream.
 * * Output Format:
 * Print all factors of 'n' in ascending order, separated by a space.
 * * Sample Input:
 * 12
 * * Sample Output:
 * 1 2 3 4 6 12
 */

import java.util.*;

public class SmartGridConfiguration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Loop handles multiple hidden test cases sequentially from the input stream
        while (sc.hasNextLong()) {
            long n = sc.nextLong();
            List<Long> companionFactors = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            
            // Highly optimized loop running up to sqrt(n) -> O(sqrt(n))
            for (long i = 1; i * i <= n; i++) {
                if (n % i == 0) {
                    sb.append(i).append(" "); // Add the smaller factor
                    
                    // If the companion factor is distinct, save it to print later
                    if (i * i != n) {
                        companionFactors.add(n / i);
                    }
                }
            }
            
            // Append the larger companion factors in reverse order to maintain ascending order
            for (int i = companionFactors.size() - 1; i >= 0; i--) {
                sb.append(companionFactors.get(i)).append(" ");
            }
            
            // Print the final result trimmed of any trailing space
            System.out.println(sb.toString().trim());
        }
        sc.close();
    }
}
