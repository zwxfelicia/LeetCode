/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子序和 Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number) 
 * which has the largest sum and return its sum
 */

/*
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation:[4,-1,2,1] has the largest sum = 6.
*/

// @lc code=start
class Solution {
    public int maxSubArray(int[] nums) {
    	if(nums == null || nums.length == 0){
            return 0;
        }
        int maxSum = 0;
         // 1. 状态定义
        // dp[i] 表示前 i 个元素的最大连续子数组的和
        int[] dp = new int[nums.length];

        // 2. 状态初始化，数组中第一个元素的最大和就是第一个元素值
        dp[0] = nums[0];
        maxSum = nums[0];

        // 3. 状态转移
        // 转移方程：dp[i] = max(dp[i - 1], 0) + nums[i]
        // dp 当前元素的值等于前一个元素值和 0 的最大值再加上 nums[i]
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            // 更新最大和
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    }
}
// @lc code=end

