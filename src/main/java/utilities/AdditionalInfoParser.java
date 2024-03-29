package utilities;

import java.util.ArrayList;

import exceptions.WilliamException;

/**
 * Deals with formatting the additional information
 */
public class AdditionalInfoParser {
    /**
     * Returns the split input after getting the user input
     * 
     * @param input Input from the user
     * @return string[] String[] consisting of two variables: [0] == command, [1] ==
     *         additional information
     */
    public static String[] retrieveTexts(String input) {
        String[] splitResults = new String[2];
        int indexOfFirstSpace = input.indexOf(" ");

        if (indexOfFirstSpace == -1) {
            splitResults[0] = input;
            splitResults[1] = null;
        } else {
            splitResults[0] = input.substring(0, indexOfFirstSpace);
            splitResults[1] = input.substring(indexOfFirstSpace + 1);
        }
        return splitResults;
    }

    /**
     * Splits the input regardless of '/by', '/to', '/id', '/priority', '/from'
     * 
     * @param input    Input that is the additional details
     * @param keywords The splitting conditions
     * @return string[] An array of String that contains the name and dates in
     *         separated form
     * @throws WilliamException If the input does not contain the keywords
     */
    public static String[] splitInput(String input, String... keywords) throws WilliamException {
        checkAdditionalDetailEmpty(input);

        ArrayList<String> parts = new ArrayList<>();
        String currentPart = input;

        for (String keyword : keywords) {
            if (currentPart.contains(keyword)) {
                String[] splitParts = currentPart.split(keyword, 2);
                parts.add(splitParts[0].trim());
                currentPart = splitParts.length > 1 ? splitParts[1] : "";
            } else {
                throw new WilliamException("The input does not contain the required '" + keyword
                        + "' keyword. Please try again!");
            }
        }

        parts.add(currentPart.trim());

        // Assertion to check that the array is not empty after splitting
        assert parts.isEmpty() == false : "Array should not be empty after splitting by keywords";

        return parts.toArray(new String[0]);
    }

    /**
     * Checks whether the additional detail is empty
     * 
     * @param input Input that is the additional detail
     * @throws WilliamException If the description of the additional detail is empty
     */
    public static void checkAdditionalDetailEmpty(String input) throws WilliamException {
        if (input == null || input.trim().isEmpty()) {
            throw new WilliamException(
                    "The description of a task should not be empty. Please try again!");
        }

        // Assertion to check that input is not empty after passing the initial check
        assert input != null && input.trim().isEmpty() == false : "Input should not be null";
    }
}
