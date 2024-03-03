package dude.utils;

/**
 * The utils class contains utility methods that are used throughout the application.
 */
public class Utils {


    // From chat GPT https://chat.openai.com/share/1848f5d2-1197-418e-86a9-bccdf69fc790

    /**
     * Discards the first word from the input string.
     *
     * @param input The input string.
     * @return The input string with the first word discarded.
     */
    public static String discardFirstWord(String input) {
        assert (input != null);
        // Split the string by whitespace
        String[] words = input.split(" ", 2);
        // Check if there are at least two words
        if (words.length > 1) {
            // Return the substring starting from the index of the second word
            return input.substring(input.indexOf(words[1]));
        } else {
            // If there is only one word, return an empty string
            return "";
        }
    }

    /**
     * Counts the occurrences of a target string in an array of strings.
     *
     * @param array  The array of strings.
     * @param target The target string.
     * @return The number of occurrences of the target string in the array.
     */
    public static int countOccurrences(String[] array, String target) {
        int count = 0;
        for (String str : array) {
            if (str.equals(target)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Finds the index of the first occurrence of a target string in an array of strings.
     *
     * @param array  The array of strings.
     * @param target The target string.
     * @return The index of the first occurrence of the target string in the array. If the target string is not found,
     * -1 is returned.
     */
    public static int findIndex(String[] array, String target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i; // Return the index if the target string is found
            }
        }
        return -1; // Return -1 if the target string is not found in the array
    }

}
