// The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

// Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

// Input: nums = [1,2,2,4]
// Output: [2,3]

//Method 1: sort

// Time: O(nLogn)
// Space: (logn)

class Solution {
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        Arrays.sort(nums);
        int dup = -1;
        int missing = -1;
        int n = nums.length;
        
        // [1, 2, 2, 4]
        // i = 3   nums[3] = 4 > 3 = nums[2] + 1
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                dup = nums[i];
            } else if (nums[i] > nums[i - 1] + 1) {
                missing = nums[i - 1] + 1;
            }
        }

        //[0,1,2]  missing = 3
        return new int[]{dup, nums[n - 1] != n ? n : missing};
    }
}
```

// Method 2: Bit Manipulation

// Time: O(n)
// Space: O(1)

// 一个长度为 n-1 的数组包含 1 到 n 中的 n-1个数字，有一个数字缺失，如何找出这个缺失的数字?
// 1. 首先使用 1 到 n 的所有数字做异或运算，
// 2. 然后再使用数组中的所有数字与这个数字异或，
// 3. 最后得到的值就是缺失数字。
// 因为数字与其本身做异或运算结果为0，因此所有数字做完异或后，剩余的就是缺失的数字。

// 按照这个方法，将 nums 中所有的数字与 1 到 n 的所有数字做异或运算，得到的结果为 x^y，
// x 和 y 分别表示 nums 中重复数字和缺失数字。

// 在异或结果 xor 的二进制中，
// 值为 1 的位置表示 x 和 y 在该位置的值不同，
// 值为 0 的位置表示 x 和 y 在该位置的值相同。
// 我们称 xor 最右边比特位为 rightmostbit，且使该位置为 1。

// 根据 rightmostbit 不同将数组 nums 分为两部分。
// 第一部分所有数字的 rightmostbit 为 1，
// 第二部分所有数字的 rightmostbit 为 0。
// 那么 x 和 y 会被分配到不同的部分。此时问题就简化为最开始的简单问题。

// 根据 rightmostbit 的不同，将 1 到 n 的所有元素分为两部分。

// 现在分别使用从 nums 中分出来 rightmostbit 为 1 的部分与 1 到 n 中分出来 rightmostbit 为 1 的部分做异或。
// 在结果中，相同的元素异或为 0，最终只会剩下重复数字或缺失数字，即 x 或 y。
// 同理也对 rightmostbit 为 0 的部分异或。

// original numbers    1   2   3   4   5   6
// nums                1   2   4   4   5   6

// 2 ^ 4 = 1 1 0
//           | 
//         rightmost 1 bit
        
// original numbers       nums
//    001 ~               001 ~                      ~ numbers in set 1
//    010 ^               010 ^                      ^ numbers in set 2
//    011 x               100 x                      x missing/repeated term
//    100 ~               100 ~
//    101 ~               101 ~
//    110 ^               110 ^


// class Solution {
//     public int[] findErrorNums(int[] nums) {
//         int xor = 0, xor0 = 0, xor1 = 0;
//         for (int n: nums)
//             xor ^= n;
//         for (int i = 1; i <= nums.length; i++)
//             xor ^= i;
//         int rightmostbit = xor & ~(xor - 1);
//         for (int n: nums) {
//             if ((n & rightmostbit) != 0)
//                 xor1 ^= n;
//             else
//                 xor0 ^= n;
//         }
//         for (int i = 1; i <= nums.length; i++) {
//             if ((i & rightmostbit) != 0)
//                 xor1 ^= i;
//             else
//                 xor0 ^= i;
//         }
//         for (int i = 0; i < nums.length; i++) {
//             if (nums[i] == xor0)
//                 return new int[]{xor0, xor1};
//         }
//         return new int[]{xor1, xor0};

//     }
// }


// Method 3: Map
// Time: O(n)
// Space: On)

class Solution {
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        Map<Integer, Integer> map = new HashMap<>();
        int dup = -1;
        int missing = -1;

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for (int i = 1; i <= nums.length; i++) {
            if (map.containsKey(i)) {
                //map存在i, 并且出现了2次
                if (map.get(i) == 2) {
                    dup = i;
                }
            } else {
                missing = i;
            }
        }

        return new int[]{dup, missing};
    }
}
