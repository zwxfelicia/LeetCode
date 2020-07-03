/*
 * @lc app=leetcode.cn id=93 lang=java
 *
 * [93] 复原IP地址
 */

// @lc code=start
class Solution {
    private List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() == 0) {
            return result;
        }

        dfs(s, 0, new ArrayList<>());
        return result;
    }

    private void dfs(String s, int pos, List<String> cur) {
        //valid IP consists of exactly 4 integers
        if (cur.size() == 4) {
            //如果此时pos也刚好遍历玩整个s
            if (pos == s.length()) {
                // join 用法：例如 [[255],[255],[111],[35]] -> 255.255.111.35
                result.add(String.join(".", cur));
            }
            return;
        }
        // ip 地址每段最多有三个数字
        for (int i = 1; i <= 3; i++) {
            // 如果当前位置距离 s 末尾小于 3 就不用再分段了，直接跳出循环即可。
            if (pos + i > s.length()) {
                break;
            }
            // 将 s 的子串开始分段
            String segment = s.substring(pos, pos + i);
            // 剪枝条件：段的起始位置不能为 0，段拆箱成 int 类型的长度不能大于 255
            if (segment.startsWith("0") && segment.length() > 1 || (i == 3 && Integer.parseInt(segment) > 255)) {
                continue;
            }

            // 符合要求就加入到 cur 数组中
            cur.add(segment);
            // 继续递归遍历下一个位置
            dfs(s, pos + i, cur);
            // 回退到上一个元素，即回溯
            cur.remove(cur.size() - 1);
        }
    }
}
// @lc code=end

