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
     * @return string[] String[] consisting of two variables: [0] == command, [1] == additional
     *         information
     */
    public static String[] retrieveTexts(String input) {
        String[] resultOfSplit = new String[2];
        int indexOfFirstSpace = input.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            resultOfSplit[0] = input;
            resultOfSplit[1] = null;
        } else {
            resultOfSplit[0] = input.substring(0, indexOfFirstSpace);
            resultOfSplit[1] = input.substring(indexOfFirstSpace + 1);
        }
        return resultOfSplit;
    }

    /**
     * Splits the input regardless of '/by', '/to' or '/from'
     * 
     * @param input Input that is the additional details
     * @param keywords The splitting conditions
     * @return string[] An array of String that contains the name and dates in seperated form
     * @throws WilliamException If the input does not contain the keywords
     */
    public static String[] splitInput(String input, String... keywords) throws WilliamException {
        checkAdditionalDetailEmpty(input);

        ArrayList<String> parts = new ArrayList<>();
        String currentPart = input;

        for (String keyword : keywords) {
            String[] splitParts = currentPart.split(keyword, 2);
            if (splitParts.length < 2) {
                throw new WilliamException("The input does not contain the required '" + keyword
                        + "' keyword or is missing text before/after '" + keyword
                        + "' keyword. Please try again!");
            }
            parts.add(splitParts[0]);
            currentPart = splitParts[1];
        }

        parts.add(currentPart);

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
    }
}
