/*
 * @lc app=leetcode.cn id=209 lang=java
 *
 * [209] 长度最小的子数组
 */

// @lc code=start
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if (nums == null || len == 0) {
            return 0;
        }

        int left = 0;       // 左指针
        int right = 0;      // 右指针
        int sum = 0;        // 窗口中元素的和
        int minSize = Integer.MAX_VALUE;    // 最小窗口

        while (right < len) {
            // 窗口中的元素小于目标值，右指针向右移，扩大窗口
            sum += nums[right++];

            // 窗口中的元素大于目标值，左指针向右移，缩小窗口
            while (sum >= s) {
                minSize = Math.min(minSize, right - left);
                sum -= nums[left++];
            }
        }
        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }
}
// @lc code=end

