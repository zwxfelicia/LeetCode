/*
 * @lc app=leetcode.cn id=69 lang=java
 *
 * [69] x 的平方根
 */

// @lc code=start
class Solution {
    public int mySqrt(int x) {
        if (x <=1) {
            return x;
        }
        int l = 2;
        int r = x / 2 ;
        int m = 0;
        long num;
        while (l <= r){
            m = l + (r - l) / 2;
            num = (long) m * m;
            if (num > x) {
                r = m - 1;
            } else if (num < x) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return r;
    }
}
// @lc code=end

