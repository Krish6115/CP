class Solution {

    /*
    Problem: Pow(x, n) (LeetCode 50)

    -----------------------------------------
    Understanding My Approach
    -----------------------------------------

    Approach Name:
    Binary Exponentiation (Fast Power)

    Idea:

    Instead of multiplying x by itself n times:

    x * x * x * x * ...

    we use the mathematical observation:

    If n is even:

    x^n = (x^(n/2)) * (x^(n/2))

    If n is odd:

    x^n = (x^(n/2)) * (x^(n/2)) * x

    This reduces the exponent by half at every step.

    -----------------------------------------
    Why Convert n to long?
    -----------------------------------------

    Consider:

    n = Integer.MIN_VALUE

    n = -2147483648

    If we do:

    -n

    it overflows because:

    2147483648

    cannot fit inside an int.

    Therefore:

    long N = n;

    is used before negation.

    -----------------------------------------
    Example
    -----------------------------------------

    x = 2
    n = 10

    power(2,10)

    = power(2,5)^2

    = (power(2,2)^2 * 2)^2

    = ((power(2,1)^2)^2 * 2)^2

    = 1024

    -----------------------------------------
    Recursive Calls
    -----------------------------------------

    power(2,10)
            |
            v
    power(2,5)
            |
            v
    power(2,2)
            |
            v
    power(2,1)
            |
            v
    power(2,0)

    Depth of recursion:

    log₂(n)

    -----------------------------------------
    Is This Approach Optimal?
    -----------------------------------------

    Yes.

    Every recursive call reduces the exponent
    by half.

    This is the standard interview-optimal
    solution.

    -----------------------------------------
    Time Complexity:
    O(log n)

    Because exponent is halved in every call.

    -----------------------------------------
    Space Complexity:
    O(log n)

    Due to recursion stack.
    */

    public double myPow(double x, int n) {

        long N = n;

        if (N < 0) {
            return 1.0 / power(x, -N);
        }

        return power(x, N);
    }

    private double power(double x, long n) {

        if (n == 0) {
            return 1.0;
        }

        double half = power(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        }

        return half * half * x;
    }
}
