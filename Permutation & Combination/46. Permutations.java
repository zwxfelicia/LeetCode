// Given a collection of distinct integers, return all possible permutations

// Input: [1,2,3]
// Output:
// [
//   [1,2,3],
//   [1,3,2],
//   [2,1,3],
//   [2,3,1],
//   [3,1,2],
//   [3,2,1]
// ]



class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(nums, results, new ArrayList<Integer>());
        return results;
    }

    private void dfs(int nums[], List<List<Integer>> results, List<Integer> temp) {
        if (temp.size() == nums.length) {
            //深拷贝，因为每一层传递下来的temp不是新建的
            //temp.add(num) 重新new新的temp则不需要深拷贝
            results.add(new ArrayList<>(temp));
        } else {
            for (int num : nums) {
                if (!temp.contains(num)) {
                    temp.add(num);
                    dfs(nums, results, temp);
                    temp.remove(temp.size() - 1);
                }
                
            }
        }
    }
}