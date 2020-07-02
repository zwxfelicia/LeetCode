/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串 Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000
 */

/*
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
*/

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {
		if (s == null || s.length() < 2 ){
            return s;
        }

        int len = s.length();
        int start = 0;
        int end = 0;
        int maxLength = 1;

        boolean[][] dp = new boolean[len][len];
        for(int r = 1; r < len; r++){
            for(int l = 0; l < r; l++){
                //r - 1<= 2 => r-1<=l+1
                //r-1<l+1 => one char initialized to be true
                //r-1=l+1 => two same chars initialized to be true
                if(s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l+1][r-1])) {
                    dp[l][r] = true;
                    if(r - l + 1 > maxLength) {
                        maxLength = r - l + 1;
                        start = l;
                        end = r;
                    }
                }
            }
        }
        
        //substring [ , )
        return s.substring(start, end+1);
    }
}
// @lc code=end

