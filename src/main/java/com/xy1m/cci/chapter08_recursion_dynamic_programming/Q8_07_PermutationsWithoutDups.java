package com.xy1m.cci.chapter08_recursion_dynamic_programming;

import java.util.ArrayList;
import java.util.List;

public class Q8_07_PermutationsWithoutDups {
    // build from permutations of first n-1 characters
    static List<String> getPerms(String str) {
        if (str == null) return null;
        List<String> permutations = new ArrayList<>();
        if (str.isEmpty()) {
            permutations.add("");
            return permutations;
        } else {
            char first = str.charAt(0);
            List<String> words = getPerms(str.substring(1));
            for (String word : words) {
                for (int i = 0; i <= word.length(); i++) {
                    permutations.add(insertCharAt(word, first, i));
                }
            }
        }
        return permutations;
    }

    public static String insertCharAt(String word, char c, int index) {
        String left = word.substring(0, index);
        String right = word.substring(index);
        return left + c + right;
    }

    // build from permutations based on size
    public static List<String> getPerms2(String str) {
        if (str == null) return null;
        int len = str.length();
        List<String> result = new ArrayList<>();
        if (len == 0) {
            result.add("");
            return result;
        }
        for (int i = 0; i < len; i++) {
            String before = str.substring(0, i);
            String after = str.substring(i + 1);
            List<String> words = getPerms2(before + after);
            for (String word : words) {
                result.add(str.charAt(i) + word);
            }
        }
        return result;
    }

    public static List<String> getPerms3(String str) {
        List<String> result = new ArrayList<>();
        getPerms3("", str, result);
        return result;
    }

    public static void getPerms3(String prefix, String remainder, List<String> result) {
        if (remainder.length() == 0) {
            result.add(prefix);
        }
        int len = remainder.length();
        for (int i = 0; i < len; i++) {
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1);
            getPerms3(prefix + remainder.charAt(i), before + after, result);
        }
    }

    public static void main(String[] args) {
        System.out.println(getPerms("abc"));
        System.out.println(getPerms2("abc"));
        System.out.println(getPerms3("abc"));
    }
}
