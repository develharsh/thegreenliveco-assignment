package main;
/*
Q1: Given three sorted arrays A, B, and C. Write the most efficient algorithm to find the 5th largest element among the three arrays?
Q2: Write a program to eliminate duplicates in a sorted array.
Q3: Write a program to convert uppercase to lower case and vice versa of a given string or sentence ?
Q4: write a program to count the no of words in the given string?
Q5: Write a program to swap two numbers without using a third variable ?
*/

class Pair {
    int x, y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Utils {
    public int[] utilMerge(int[] a, int[] b) {
        int la = a.length, lb = b.length;
        int[] temp = new int[la + lb];
        int x = 0, y = 0, i = 0;
        for (i = 0; i < Math.min(la, lb); ++i) {
            if (a[x] <= b[y]) {
                temp[i] = a[x++];
            } else {
                temp[i] = b[y++];
            }
        }
        if (x < la) {
            while (x < la) temp[i++] = a[x++];
        }
        if (y < lb) {
            while (y < lb) temp[i++] = b[y++];
        }
        return temp;
    }
}

class Solution extends Utils {
    int getFifthLargest(int[] a, int[] b, int[] c) {
        int la = a.length, lb = b.length, lc = c.length;
        if (la + lb + lc < 5) return -1;//sanity check
        int[] temp = utilMerge(a, b);
        temp = utilMerge(temp, c);
        int length = temp.length;
        return temp[length - 5];
    }

    int eliminateDuplicates(int[] arr) {
        int n = arr.length;
        if (n == 0 || n == 1) return n;
        int[] temp = new int[n];
        int a = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (arr[i] != arr[i + 1]) {
                temp[a++] = arr[i];
            }
        }
        temp[a++] = arr[n - 1];//last element
        for (int i = 0; i < a; ++i) {
            arr[i] = temp[i];
        }
        return a;
    }

    String alterCase(String str) {
        int length = str.length();
        String ans = "";
        for (int i = 0; i < length; ++i) {
            char x = str.charAt(i);
            if (Character.isUpperCase(x)) {
                ans += Character.toLowerCase(x);
            } else {
                ans += Character.toUpperCase(x);
            }
        }
        return ans;
    }

    int getNoOfWords(String str) {
        return str.split("\s+").length;
    }

    Pair swap(Pair pair) {
        pair.x = pair.x + pair.y;
        pair.y = pair.x - pair.y;
        pair.x = pair.x - pair.y;
        return pair;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String str;
        int[] arr;

        //answer 1
        int ans1 = sol.getFifthLargest(new int[]{1, 2, 3}, new int[]{16, 17, 18, 20, 25}, new int[]{34, 44, 57});
        System.out.println("Answer 1: " + ans1);

        //answer 2
        arr = new int[]{1, 2, 3, 4, 4, 5, 6, 7, 7, 7, 7, 7, 8};
        int ans2 = sol.eliminateDuplicates(arr);
        System.out.println("Answer 2:");
        for (int i = 0; i < ans2; ++i) System.out.println("Answer 2." + (i + 1) + ": " + arr[i]);

        //answer 3
        str = "asbndbADJNSJ_bjsdb  #*U*HBDHB bsdbHbsUiKKNsdbd";
        String ans3 = sol.alterCase(str);
        System.out.println("Answer 3: " + ans3);

        //answer 4
        str = "asbndbADJNSJ_bjsdb  #*U*H BDHB bsdbHbsUiKKNsdbd";
        int ans4 = sol.getNoOfWords(str);
        System.out.println("Answer 4: " + ans4);

        //answer 5
        int x = 129, y = 748;
        Pair pair = new Pair(x, y);
        Pair ans5 = sol.swap(pair);
        System.out.println("Answer 5: X is now " + ans5.x + " & Y is now " + ans5.y);
    }
}