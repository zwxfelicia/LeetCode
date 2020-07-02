/*
 * @lc app=leetcode.cn id=74 lang=java
 *
 * [74] 搜索二维矩阵
 */

// @lc code=start
// Method: Binary Search
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int y = matrix.length;
        if( y == 0 || matrix == null){
            return false;
        }
        int x = matrix[0].length;

        int l = 0;
        int r = x * y -1 ;
        int midIndex;
        int midVal;
        while(l <= r ){
            midIndex = (r + l) / 2; 
            midVal = matrix[midIndex / x][midIndex % x];
            if (target == midVal) {
                return true;
            }　else {
                if (target < midVal) {
                    r = midIndex - 1;
                }
                else {
                    l = midIndex + 1;
                }
            } 
        }
        return false;

  }
 
}
// @lc code=end

