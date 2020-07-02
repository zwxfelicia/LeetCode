/*
 * @lc app=leetcode.cn id=76 lang=java
 *
 * [76] 最小覆盖子串
 */

// @lc code=start
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int start = 0;
        int minlen = Integer.MAX_VALUE;
        int valid = 0;

        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 进行窗口内数据的一系列更新
            // 如果需要c
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                //如果窗口里c的次数 = 需要c的次数
                //注意：Integer不能用==比较，要用compareTo
                if (window.get(c).compareTo(need.get(c)) == 0) {
                    //已经匹配的character+1
                    valid++;
                }
            }
            // 右移窗口
            right++;

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < minlen) {
                    start = left;
                    minlen = right - left;
                }
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                
                // 进行窗口内数据的一系列更新
                //如果需要最左边的char
                if (need.containsKey(d)) {
                    //先将char从窗口中移除
                    window.put(d, window.get(d) - 1);
                    //窗口中的char数量 < 所需的char数量
                    if (window.get(d) < need.get(d)) {
                        //已经匹配的数量-1
                        valid--;
                    }
                }
                left++;
            }
        }
        return minlen == Integer.MAX_VALUE ? "" : s.substring(start, minlen + start);
    }
}
// @lc code=end

