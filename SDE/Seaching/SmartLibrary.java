/*
Question: Smart Library - Finding Books with Binary Search

Alex is a librarian at a large digital library where books are stored in an automated retrieval system. 
Each book is assigned a unique serial number, and the system maintains the list of books in sorted order. 
Due to the vast number of books, searching manually is inefficient. When a visitor requests a book, 
Alex needs to find its position in the system quickly.

Given a sorted list of book serial numbers, help Alex locate the requested book's index using binary search. 
If the book is not available, return -1.

Input Format:
1. The first line contains an integer N, representing the number of books in the system.
2. The second line contains N space-separated integers representing the sorted book serial numbers.
3. The third line contains an integer X, representing the target book serial number requested by a visitor.

Constraints:
- 1 ≤ N ≤ 10^5
- 1 ≤ Book Serial Number ≤ 10^9
- The book serial numbers are given in sorted ascending order.

Output Format:
- Print the 0-based index of the book where X is located.
- If the book is not found, print -1.

Example:
Input:
6  
101 205 306 408 512 619  
408  

Output:
3
*/

import java.util.*;

public class SmartLibrary {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        // Read the total number of books
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        
        // Populate the sorted book serial numbers array
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        // Read the target book serial number to find
        int k = sc.nextInt();
        
        int low = 0;
        int high = n - 1;
        boolean found = false;
        
        // Perform standard Binary Search on the sorted array
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (arr[mid] == k) {
                System.out.print(mid);
                found = true;
                break;
            } else if (arr[mid] > k) {
                high = mid - 1; // Target is in the left half
            } else {
                low = mid + 1;  // Target is in the right half
            }
        }
        
        // If the book is not found in the system, output -1
        if (!found) {
            System.out.print(-1);
        }
        
        sc.close();
    }
}
