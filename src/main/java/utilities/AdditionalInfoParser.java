package utilities;

import exceptions.WilliamException;

/**
 * The AdditonalInformationParser deals with formatting the additional
 * information
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
     * Split the method by "/by"
     * 
     * @param input Input that is the additional details
     * @return string[] String[] that contains the name and the date
     * @throws WilliamException If the input does not have the "/by" or missing text
     *                          before/after "/by"
     */
    public static String[] splitBy(String input) throws WilliamException {
        checkAdditionalDetailEmpty(input);
        String[] twoParts = input.split(" /by ", 2);
        if (twoParts.length < 2) {
            throw new WilliamException(
                    "The input does not contain the required '/by' keyword or is missing text before/after '/by' keyword. Please try again!");
        }
        DateAndTimeParser.acceptDateAndTime(twoParts[1]);
        return twoParts;
    }

    /**
     * Split the method by "/to" and "/from"
     * 
     * @param input Input that is the additional details
     * @return string[] String[] that contains the name, from and to date
     * @throws WilliamException If the input does not contain "/to", "/from"
     *                          keywords
     *                          and is missing text before/after the keywords
     */
    public static String[] splitToAndFrom(String input) throws WilliamException {
        checkAdditionalDetailEmpty(input);
        String[] firstSplit = input.split(" /from ", 2);
        if (firstSplit.length < 2) {
            throw new WilliamException(
                    "The input does not contain the required '/from' keyword or is missing text before/after '/from' keyword. Please try again!");
        }
        String[] secondSplit = firstSplit[1].split(" /to ", 2);
        if (secondSplit.length < 2) {
            throw new WilliamException(
                    "The input does not contain the required '/to' keyword or is missing text before/after '/to' keyword. Please try again!");
        }
        DateAndTimeParser.acceptDateAndTime(secondSplit[0]);
        DateAndTimeParser.acceptDateAndTime(secondSplit[1]);
        DateAndTimeParser.checkWhetherToAndFromValid(secondSplit[0], secondSplit[1]);
        String[] threeParts = { firstSplit[0], secondSplit[0], secondSplit[1] };
        return threeParts;
    }

    /**
     * Check whether the additional detail is empty
     * 
     * @param input Input that is the additional detail
     * @throws WilliamException If the description of the additional detail is empty
     */
    public static void checkAdditionalDetailEmpty(String input) throws WilliamException {
        if (input == null || input.trim().isEmpty()) {
            throw new WilliamException("The description of a task should not be empty. Please try again!");
        }
    }
}
