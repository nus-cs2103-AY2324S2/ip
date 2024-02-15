package helper;

/**
 * Class to split words
 */
public class WordsSplit {
    private WordsSplit() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * To retrieve the action to be taken and the content separately
     * @param input
     * @return
     */
    public static String[] separateWords(String input, String splitType, boolean isOnlyFirst) {
        int splitFirstWhitespace = 2;
        return isOnlyFirst ? input.split(splitType, splitFirstWhitespace) : input.split(splitType);
    }

    /**
     * Get the action to be taken
     * @param input
     * @return
     */
    public static String getWord(String[] input, int idx) {
        return input[idx];
    }
}
