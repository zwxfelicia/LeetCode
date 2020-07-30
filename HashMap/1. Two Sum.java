
// Given nums = [2, 7, 11, 15], target = 9,

// Because nums[0] + nums[1] = 2 + 7 = 9,
// return [0, 1].


//1. HashMap
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums.length == 0 || nums == null) {
            return new int[]{};
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;  i < nums.length; i++) {
            int complement = target - nums[i]
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
    }
}

