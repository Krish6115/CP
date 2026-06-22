/*
# LeetCode 876 - Middle of the Linked List

Problem:
Given the head of a singly linked list, return the middle node.

If there are two middle nodes (even length list),
return the SECOND middle node.

Input:
1 -> 2 -> 3 -> 4 -> 5

Output:
3

Input:
1 -> 2 -> 3 -> 4 -> 5 -> 6

Output:
4

Key Observation:
A normal traversal requires counting nodes first.

Can we find the middle in a single traversal?

Yes.

This is a classic Fast-Slow Pointer problem.

=================================================
VALIDATION OF YOUR APPROACH
=================================================

Your Approach:
Optimal

Strengths:

✓ Single traversal
✓ O(N) Time
✓ O(1) Space
✓ Most commonly expected interview solution
✓ Uses Floyd's Fast-Slow Pointer Technique

Why It Works:

Slow moves 1 step.

Fast moves 2 steps.

When fast reaches the end,
slow automatically reaches the middle.

=================================================
PATTERN IDENTIFICATION
=================================================

Pattern Used:
- Fast-Slow Pointer
- Two Pointer

Famous Trick:
- Floyd's Hare and Tortoise Technique

Difficulty:
Easy

Interview Tags:
- Linked List
- Two Pointer
- Fast-Slow Pointer

=================================================
APPROACH 1 : BRUTE FORCE
=================================================

Approach Name:
Count Length First

Intuition:

Find total length N.

Middle Index = N / 2

Traverse again to reach that node.

Why It Works:

After knowing total nodes,
we can directly move to the middle.

Dry Run:

List:

1 -> 2 -> 3 -> 4 -> 5

Length = 5

Middle Index = 5 / 2 = 2

Move 2 steps from head:

1 -> 2 -> 3

Answer = 3

TC: O(N + N/2)

SC: O(1)
*/

class BruteForce {

    public ListNode middleNode(ListNode head) {

        int count = 0;

        ListNode temp = head;

        while(temp != null){
            count++;
            temp = temp.next;
        }

        int middle = count / 2;

        temp = head;

        while(middle-- > 0){
            temp = temp.next;
        }

        return temp;
    }
}

/*
=================================================
APPROACH 2 : BETTER
=================================================

Approach Name:
Store Nodes in ArrayList

Intuition:

Store every node inside an ArrayList.

Middle node becomes:

list.get(size / 2)

Why It Works:

Array provides O(1) indexing.

Dry Run:

1 -> 2 -> 3 -> 4 -> 5

Array:

[1, 2, 3, 4, 5]

size = 5

Middle Index = 2

Answer = 3

TC: O(N)

SC: O(N)
*/

class Better {

    public ListNode middleNode(ListNode head) {

        ArrayList<ListNode> list = new ArrayList<>();

        ListNode temp = head;

        while(temp != null){
            list.add(temp);
            temp = temp.next;
        }

        return list.get(list.size() / 2);
    }
}

/*
=================================================
APPROACH 3 : OPTIMAL
=================================================

Approach Name:
Fast-Slow Pointer

Famous Trick:
Floyd's Hare and Tortoise Algorithm

Intuition:

Slow moves 1 step.

Fast moves 2 steps.

When fast reaches the end,
slow reaches the middle.

Reason:

Fast covers twice the distance.

Therefore slow reaches half the distance
when fast reaches the end.

=================================================
POINTER MOVEMENT ANALYSIS
=================================================

Slow Pointer:

Why Created?

To identify the middle node.

Why It Moves?

Moves one step each iteration.

s = s.next

When Does It Stop?

When fast reaches the end.

At that moment slow is at the middle.

-------------------------------------------------

Fast Pointer:

Why Created?

To traverse the list twice as fast.

Why It Moves?

Moves two steps each iteration.

f = f.next.next

When Does It Stop?

Case 1:

f == null

Even Length

Case 2:

f.next == null

Odd Length

=================================================
VISUAL POINTER DIAGRAM
=================================================

Example:

1 -> 2 -> 3 -> 4 -> 5

Initially:

s
↓
1 -> 2 -> 3 -> 4 -> 5
↑
f

---------------------------------

Iteration 1:

s
    ↓
1 -> 2 -> 3 -> 4 -> 5

          ↑
          f

---------------------------------

Iteration 2:

s
         ↓
1 -> 2 -> 3 -> 4 -> 5

                    ↑
                    f

---------------------------------

Iteration 3:

fast becomes NULL

s
         ↓
1 -> 2 -> 3 -> 4 -> 5

Answer = 3

=================================================
COMPLETE DRY RUN (ODD LENGTH)
=================================================

Input:

1 -> 2 -> 3 -> 4 -> 5

Initial:

s = 1
f = 1

---------------------------------

Iteration 1:

s = 2
f = 3

---------------------------------

Iteration 2:

s = 3
f = 5

---------------------------------

Iteration 3:

f.next == null

Loop Ends

Return s = 3

=================================================
COMPLETE DRY RUN (EVEN LENGTH)
=================================================

Input:

1 -> 2 -> 3 -> 4 -> 5 -> 6

Initial:

s = 1
f = 1

---------------------------------

Iteration 1:

s = 2
f = 3

---------------------------------

Iteration 2:

s = 3
f = 5

---------------------------------

Iteration 3:

s = 4
f = null

Loop Ends

Return s = 4

This automatically returns
the SECOND middle node.

=================================================
WHY THIS APPROACH IS OPTIMAL
=================================================

Why Fewer Traversals Aren't Possible?

Every node contributes to determining
the middle.

Therefore O(N) is unavoidable.

-------------------------------------------------

Why Less Memory Isn't Possible?

Only two pointers are used.

No extra data structure.

Extra Space = O(1)

=================================================
INTERVIEW DERIVATION
=================================================

Brute Force:

Count Length

↓

Find N/2

↓

Requires Two Traversals

↓

Can we find middle while traversing?

↓

Need one pointer for position

↓

Need another pointer moving faster

↓

Fast moves 2x

Slow moves 1x

↓

Fast reaches end

↓

Slow reaches middle

↓

Fast-Slow Pointer Solution

=================================================
EDGE CASES
=================================================

1. Empty List

head = null

Output:

null

-------------------------------------------------

2. Single Node

1

Output:

1

-------------------------------------------------

3. Two Nodes

1 -> 2

Output:

2

(Second Middle)

-------------------------------------------------

4. Odd Length

1 -> 2 -> 3 -> 4 -> 5

Output:

3

-------------------------------------------------

5. Even Length

1 -> 2 -> 3 -> 4 -> 5 -> 6

Output:

4

-------------------------------------------------

6. Duplicate Values

1 -> 1 -> 1 -> 1

Works Correctly

=================================================
TIME COMPLEXITY
=================================================

O(N)

=================================================
SPACE COMPLEXITY
=================================================

O(1)

=================================================
OPTIMAL JAVA CODE
=================================================
*/

class Solution {

    public ListNode middleNode(ListNode head) {

        ListNode s = head;
        ListNode f = head;

        while(f != null && f.next != null){

            s = s.next;
            f = f.next.next;
        }

        return s;
    }
}

/*
=================================================
APPROACH SUMMARY
=================================================

| Approach          | Idea                     | TC   | SC   |
|------------------|--------------------------|------|------|
| Brute Force      | Count Length First       | O(N) | O(1) |
| Better           | Store Nodes In ArrayList | O(N) | O(N) |
| Optimal          | Fast-Slow Pointer        | O(N) | O(1) |

=================================================
INTERVIEW NOTES
=================================================

Pattern Used:
Fast-Slow Pointer

Famous Trick:
Floyd's Hare and Tortoise

Common Mistakes:

1. Using

while(f.next != null)

instead of

while(f != null && f.next != null)

causes NullPointerException.

-------------------------------------------------

2. Returning first middle node
instead of second middle node.

-------------------------------------------------

3. Moving fast only one step.

=================================================
INTERVIEW FOLLOW UPS
=================================================

1. Can you return FIRST middle node
   for even length lists?

2. Can you find middle recursively?

3. Can you find quarter point?

4. How would you split a linked list
   into two halves?

=================================================
SIMILAR PROBLEMS
=================================================

1. LC 141 - Linked List Cycle

2. LC 142 - Linked List Cycle II

3. LC 234 - Palindrome Linked List

4. LC 143 - Reorder List

5. LC 19 - Remove Nth Node From End

=================================================
MEMORY TRICK
=================================================

Whenever the problem asks:

"Middle of Linked List"

Immediately think:

Slow = 1 step
Fast = 2 steps

Fast reaches end
Slow reaches middle

=================================================
60 SECOND INTERVIEW EXPLANATION
=================================================

I use two pointers: slow and fast.

Slow moves one node at a time,
while fast moves two nodes.

Since fast travels twice as quickly,
when fast reaches the end of the list,
slow will have covered exactly half the distance,
placing it at the middle node.

This requires only one traversal,
giving O(N) time and O(1) extra space.
*/
