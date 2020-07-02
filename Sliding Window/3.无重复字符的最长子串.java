/*
 * @lc app=leetcode.cn id=3 lang=java
 *
 * [3] 无重复字符的最长子串 Longest Substring Without Repeating Characters
 * Given a string, find the length of the longest substring without repeating characters.
 */

/*
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3.
*/

/*
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
*/

// @lc code=start
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int ans = 0;
        int right = 0;

        for (int i = 0; i < chars.length; i++) {
            // 当i = 0时， i - 1越界
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                set.remove(chars[i - 1]);
            }
            while (right < chars.length && !set.contains(chars[right])) {
                set.add(chars[right]);
                right++;
            }
            // 第 i 到 right 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, right - i);
        }
        return ans;
    }
}
// @lc code=end

