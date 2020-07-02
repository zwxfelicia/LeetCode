/*
 * @lc app=leetcode.cn id=55 lang=java
 *
 * [55] 跳跃游戏 Jump Game
 * Given an array of non-negative integers, you are initially positioned at the first index of the array. 
 *
 * Each element in the array represents your maximum jump length at that position. 
 *
 * Determine if you are able to reach the last index.
 */

/*
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
*/

// @lc code=start
class Solution {
    public boolean canJump(int[] nums) {
    	int len = nums.length;
        // dp[i] 表示是否能到达nums[i]这个位置
        // 因为最后一个位置是nums[l], 所以new boolean[l]的时候不需要l + 1
        boolean[] dp = new boolean[len];

        //nums[0]都可以到达，初始化为true
        dp[0] = true;

        //dp[0]初始化为true，所以从i = 1开始
        for (int i = 1; i < len; i++) {
            // j遍历i之前的位置，看能不能到达
            for (int j = 0; j < i; j ++) {
                // 到达i的条件
                // 1. 能到达j
                // 2. nums[j]的值加上j (j所在的位置) 是否大于等于 i (i所在的位置)
                // [2, 3, 1, 1, 4]
                // i = 3 => 在1这个位置
                // j = 1 => 在3这个位置
                // nums[1] = 3 => 说明可以最多跳3步
                // 3 + 1 = 4 >= 3    => 超过了i所在的位置
                // dp[1] = true
                if (dp[j] && nums[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        // 判断是否能到达nums[l - 1]
        return dp[len - 1];
    }
}
// @lc code=end

