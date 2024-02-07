public class utils {


    // From chat GPT https://chat.openai.com/share/1848f5d2-1197-418e-86a9-bccdf69fc790
    public static String discardFirstWord(String input) {
        // Split the string by whitespace
        String[] words = input.split("\\s+", 2);
        // Check if there are at least two words
        if (words.length > 1) {
            // Return the substring starting from the index of the second word
            return input.substring(input.indexOf(words[1]));
        } else {
            // If there is only one word, return an empty string
            return "";
        }
    }


}
