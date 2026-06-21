/*
# LeetCode 206 - Reverse Linked List

Problem:
Given the head of a singly linked list, reverse the list.

Input:
1 -> 2 -> 3 -> 4 -> 5

Output:
5 -> 4 -> 3 -> 2 -> 1

Key Observation:
Instead of changing node values, reverse the links.

=================================================
PATTERN IDENTIFICATION
=================================================

Pattern:
- Reversal Pattern
- Three Pointer Technique

Difficulty:
Easy

Interview Tags:
- Linked List
- Reversal
- Iteration

=================================================
APPROACH 1 : BRUTE FORCE
=================================================

Approach:
Store values in an array and rewrite them in reverse order.

Intuition:
...

Why It Works:
...

Dry Run:
...

TC: O(N)
SC: O(N)
*/

class BruteForce {

    public ListNode reverseList(ListNode head) {

        List<Integer> arr = new ArrayList<>();

        ListNode temp = head;

        while(temp != null){
            arr.add(temp.val);
            temp = temp.next;
        }

        temp = head;

        for(int i = arr.size() - 1; i >= 0; i--){
            temp.val = arr.get(i);
            temp = temp.next;
        }

        return head;
    }
}

/*
=================================================
APPROACH 2 : BETTER
=================================================

Approach:
Stack Based Reversal

Intuition:
...

Why It Works:
...

Dry Run:
...

TC: O(N)
SC: O(N)
*/

class Better {

    public ListNode reverseList(ListNode head) {

        if(head == null) return null;

        Stack<ListNode> st = new Stack<>();

        ListNode temp = head;

        while(temp != null){
            st.push(temp);
            temp = temp.next;
        }

        ListNode newHead = st.pop();
        temp = newHead;

        while(!st.isEmpty()){
            temp.next = st.pop();
            temp = temp.next;
        }

        temp.next = null;

        return newHead;
    }
}

/*
=================================================
APPROACH 3 : OPTIMAL
=================================================

Approach:
Three Pointer In-place Reversal

Famous Trick:
In-place Reversal

Intuition:
Reverse each link while traversing.

Pointer Analysis:

current:
- Traverses the list
- Moves using current = next

previous:
- Maintains reversed portion
- Becomes new head

next:
- Prevents losing remaining list

Visual Diagram:

NULL <- 1 <- 2 <- 3 <- 4 <- 5

Complete Dry Run:

Iteration 1:
prev = NULL
curr = 1

next = 2
1.next = NULL

prev = 1
curr = 2

...

Why Optimal:

Every node must be visited once.
Lower bound = O(N)

Extra memory = O(1)

Edge Cases:

1. Empty List
2. Single Node
3. Two Nodes
4. Odd Length
5. Even Length
6. Duplicate Values

TC: O(N)
SC: O(1)
*/

class Solution {

    public ListNode reverseList(ListNode head) {

        ListNode current = head;
        ListNode previous = null;
        ListNode next;

        while(current != null){

            next = current.next;

            current.next = previous;

            previous = current;

            current = next;
        }

        return previous;
    }
}

/*
=================================================
APPROACH SUMMARY
=================================================

| Approach    | TC   | SC   |
|------------|------|------|
| Brute      | O(N) | O(N) |
| Better     | O(N) | O(N) |
| Optimal    | O(N) | O(1) |

=================================================
INTERVIEW NOTES
=================================================

Pattern:
Reversal Pattern

Common Mistakes:

1. Forgetting to save next node
2. Returning head instead of previous
3. Wrong pointer update order

Interview Follow Ups:

1. Recursive solution?
2. Reverse between L and R?
3. Reverse every K nodes?

Similar Problems:

- LC 92 Reverse Linked List II
- LC 25 Reverse Nodes in K Group
- LC 143 Reorder List
- LC 234 Palindrome Linked List

Memory Trick:

SNRM

Save Next
Reverse Link
Move Prev
Move Curr

=================================================
60 SECOND INTERVIEW EXPLANATION
=================================================

We use three pointers:
prev, curr and next.

Before reversing a node's link we store
the next node. Then we reverse the pointer,
move prev forward and continue.

After processing all nodes, prev points to
the original tail which becomes the new head.

TC: O(N)
SC: O(1)
*/
