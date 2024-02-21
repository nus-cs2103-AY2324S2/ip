package taskhelper;

/**
 * Class to split words
 */
public class WordsSplit {
    private WordsSplit() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * To retrieve the action to be taken and the content separately
     * @param input Command
     * @param splitType split based on which word
     * @param isOnlyFirst do we need to only split the first word?
     * @return Array of the separated words
     */
    public static String[] separateWords(String input, String splitType, boolean isOnlyFirst) {
        int splitFirstWhitespace = 2;
        return isOnlyFirst ? input.split(splitType, splitFirstWhitespace) : input.split(splitType);
    }

    /**
     * Get the action to be taken
     * @param input Array of String
     * @param idx index of the array to retrieve
     * @return String in the index of the array
     */
    public static String getWord(String[] input, int idx) {
        return input[idx];
    }
}
