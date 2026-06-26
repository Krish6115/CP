/*
Question: Finding the Oldest Artifact in a Rotated Historical Timeline

An archaeological team is analyzing a historical timeline containing artifacts' years of origin. 
The timeline was initially sorted in chronological order (oldest to newest), but due to an earthquake, 
some parts of the timeline shifted, forming a rotated sorted sequence.

Given this rotated timeline of unique years, help the team find the oldest artifact (minimum year) 
using an efficient algorithm with a time complexity of O(log n).

Input Format:
1. The first line contains an integer n, the number of years recorded in the timeline.
2. The second line contains n space-separated integers, representing the rotated sorted list of years.

Constraints:
- 1 ≤ n ≤ 10^5
- -10^6 ≤ year[i] ≤ 10^6
- The array is sorted in increasing order before rotation.

Output Format:
- Print the oldest artifact's year (minimum value in the rotated sorted array).

Example:
Input:
5 
3 4 5 1 2 

Output:
1
*/

import java.util.*;

public class OldestArtifact {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        // Read the number of elements
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int low = 0, high = n - 1;
        
        // Binary Search to find the minimum element in a rotated sorted array
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            // If mid element is greater than the high element, 
            // the minimum must be in the right half
            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } 
            // Otherwise, the minimum is in the left half (including mid)
            else {
                high = mid;
            }
        }
        
        // 'low' will point to the minimum element
        System.out.println(arr[low]);
        sc.close();
    }
}
