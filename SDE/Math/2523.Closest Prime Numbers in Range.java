class Solution {
    public boolean isp(int x) {
        if (x <= 1) {
            return false;
        }
        if (x == 2) {
            return true;
        }
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int[] closestPrimes(int left, int right) {
        int prevPrime = -1;
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[]{-1, -1};
        for (int i = left; i <= right; i++) {
            if (isp(i)) {
                if (prevPrime != -1) {
                    int diff = i - prevPrime;
                    
                    if (diff < minDiff) {
                        minDiff = diff;
                        result[0] = prevPrime;
                        result[1] = i;
                    }
                  
                    if (minDiff <= 2) {
                        return result;
                    }
                }
            
                prevPrime = i;
            }
        }

        return result;
    }
}
