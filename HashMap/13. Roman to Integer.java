// Symbol       Value
// I             1
// V             5
// X             10
// L             50
// C             100
// D             500
// M             1000
// 

// For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
// 
// Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
// 
// I can be placed before V (5) and X (10) to make 4 and 9.
// X can be placed before L (50) and C (100) to make 40 and 90.
// C can be placed before D (500) and M (1000) to make 400 and 900.
// 
// Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

// Input: "LVIII"
// Output: 58
// Explanation: L = 50, V= 5, III = 3.

// Input: "IX"
// Output: 9

//Method 1: HashMap
class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;
        int preNum = map.get(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            int num = map.get(s.charAt(i));
            // 前一位数字小于当前数字
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        // 加上最后一位数字
        sum += preNum;
        return sum;

    }
}

// Method 2: switch
// class Solution {
// public int romanToInt(String s) {
// int sum = 0;
// int preNum = getValue(s.charAt(0));
// for(int i = 1;i < s.length(); i ++) {
// int num = getValue(s.charAt(i));
// if(preNum < num) {
// sum -= preNum;
// } else {
// sum += preNum;
// }
// preNum = num;
// }
// sum += preNum;
// return sum;
// }

// private int getValue(char ch) {
// switch(ch) {
// case 'I': return 1;
// case 'V': return 5;
// case 'X': return 10;
// case 'L': return 50;
// case 'C': return 100;
// case 'D': return 500;
// case 'M': return 1000;
// default: return 0;
// }
// }
// }
