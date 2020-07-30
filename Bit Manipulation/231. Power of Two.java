Given an integer, write a function to determine if it is a power of two.

Input: 1
Output: true 
Explanation: 20 = 1


**Method 1: Math**
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 2 == 0) {
            n /= 2;
        }

        return n == 1;
    }
}
```

**Method 2: Bit Manipulation**
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

```
2^x	      n	        n - 1	       n & (n - 1)
2^0      0001       0000            0
2^1      0010       0001            0
2^2      0100       0011            0
```