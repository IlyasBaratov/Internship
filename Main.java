package Internship_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static List<String> splitString(String given_name, String range) {
        List<String> result = new ArrayList<>();
        int start_len, end_len;

        // Parse the "range" for the function
        String[] rangeParts = range.split("-"); // 4-6
        start_len = Integer.parseInt(rangeParts[0]); //4
        end_len = Integer.parseInt(rangeParts[1]); //6

        // Remove whitespaces from the string
        given_name = given_name.replaceAll(" ", "");

        // Loop through the string
        int position = 0;
        while (position < given_name.length()) {
            // Determine the length of the next word
            Random rand = new Random();
            int len = rand.nextInt((end_len - start_len) + 1) + start_len; // 7

            // Make sure we don't go past the end of the string
            if (position + len > given_name.length()) { // 7>30
                len = given_name.length() - position; // 23
            }

            // Add the word to the result array
            String word = given_name.substring(position, position + len); //0,7
            result.add(word);
            position += len;
        }

        // Sort the words using a custom comparison function
        Collections.sort(result, (a, b) -> {
            int minLength = Math.min(a.length(), b.length());
            for (int i = 0; i < minLength; i++) {
                char charA = a.charAt(i);
                char charB = b.charAt(i);
                if (Character.isDigit(charA) && !Character.isDigit(charB)) {
                    return -1;
                } else if (!Character.isDigit(charA) && Character.isDigit(charB)) {
                    return 1;
                } else if (Character.isUpperCase(charA) && !Character.isUpperCase(charB)) {
                    return -1;
                } else if (!Character.isUpperCase(charA) && Character.isUpperCase(charB)) {
                    return 1;
                } else if (charA < charB) {
                    return -1;
                } else if (charA > charB) {
                    return 1;
                }
            }
            return Integer.compare(a.length(), b.length());
        });

        // Return the result computed
        return result;
    }

    public static void main(String[] args) {
        String str = "abcdEfghijklmnoPqrsTuvwxyz";
        String range_str = "4-6";
        List<String> result = splitString(str, range_str);
        for (String word : result) {
            System.out.print(word + " ");
        }
        System.out.println();
    }
}