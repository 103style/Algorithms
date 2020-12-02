package com.tcl.algorithm;

public class KMP {

    public static void main(String[] args) {
        new KMP().test();
    }

    void test() {
        System.out.println(shortestPalindrome("aacecaaa"));
        System.out.println(shortestPalindrome("abcd"));
    }


    public String shortestPalindrome(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse();
        int[] table = getTable(temp.toCharArray());
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    /**
     * 在每个位置时 记录此时前缀和后缀相同的最长长度
     */
    int[] getTable(char[] arr) {
        int[] table = new int[arr.length];
        //index mean in range [0, j], [0,index] and [j - index, j] is the same
        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[index] == arr[i]) {
                table[i] = table[i - 1] + 1;
                index++;
                //or index++; table[i] = index;
                //or table[i] = ++index;
            } else {
                index = table[i - 1];
                while (index > 0 && arr[index] != arr[i]) {
                    index = table[index - 1];
                }
                if (arr[index] == arr[i]) {
                    index++;
                }
                table[i] = index;
            }
        }
        return table;
    }
}
