package duke;

import java.time.LocalDateTime;
import java.util.Arrays;

import duke.exceptions.HistoryIndexException;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidParametersException;
import duke.exceptions.InvalidTaskException;

/**
 * This class represents the parser used to receive and handle user inputs to the UI.
 */
public class Parser {
    /**
     * Parses command from array.
     *
     * @param arr The array to parse.
     * @return A correct command enum assigned to the array.
     * @throws InvalidTaskException When the command does not match any of the enums.
     */
    public static Ui.Command getCommand(String[] arr) throws InvalidTaskException {
        switch (arr[0]) {
        case "bye":
            return Ui.Command.BYE;
        case "todo":
            return Ui.Command.TODO;
        case "event":
            return Ui.Command.EVENT;
        case "deadline":
            return Ui.Command.DEADLINE;
        case "list":
            return Ui.Command.LIST;
        case "unmark":
            return Ui.Command.UNMARK;
        case "mark":
            return Ui.Command.MARK;
        case "delete":
            return Ui.Command.DELETE;
        case "find":
            return Ui.Command.FIND;
        default:
            throw new InvalidTaskException();
        }
    }
    /**
     * Generates the index with respect to a zero-indexed task array.
     *
     * @param s The index in question.
     * @param bounds The bounds of the task array.
     * @return The correct index with respect to the task array.
     * @throws HistoryIndexException The index is invalid.
     */
    public static Integer checkIndexGiven(String s, int bounds) throws HistoryIndexException {
        Integer parsed = Integer.parseInt(s);
        if (parsed <= 0 || parsed > bounds) {
            throw new HistoryIndexException();
        }
        return parsed - 1;
    }
    /**
     * Extracts description data from an array.
     *
     * @param descriptionArray The array that holds description data to parse.
     * @return An array representing the extracted data from the description array.
     * @throws InvalidInputException The given input has something wrong (Parameter wise or command wise).
     */
    public static String[] extractDescriptionData(String... descriptionArray) throws
            InvalidInputException {
        String[] returnArray = new String[3];
        String taskDesc;
        switch(descriptionArray[0]) {
        case "todo":
            taskDesc = String.join(" ",
                Arrays.copyOfRange(descriptionArray, 1, descriptionArray.length));
            returnArray[0] = taskDesc;
            break;
        case "find":
            String searchDesc = String.join(" ",
                Arrays.copyOfRange(descriptionArray, 1, descriptionArray.length));
            returnArray[0] = searchDesc;
            break;
        case "event":
            Integer startIndex = -1;
            Integer endIndex = -1;
            for (int i = 0; i < descriptionArray.length; i++) {
                if (descriptionArray[i].equals("/from")) {
                    startIndex = i;
                }
                if (descriptionArray[i].equals("/to")) {
                    endIndex = i;
                }
            }
            if (startIndex.equals(-1) || endIndex.equals(-1)) { // we cannot find start or end.
                throw new InvalidParametersException();
            }
            taskDesc = String.join(" ",
                Arrays.copyOfRange(descriptionArray, 1, startIndex));
            String start = String.join(" ",
                Arrays.copyOfRange(descriptionArray, startIndex + 1, endIndex));
            String end = String.join(" ",
                Arrays.copyOfRange(descriptionArray, endIndex + 1, descriptionArray.length));
            returnArray[0] = taskDesc;
            returnArray[1] = start;
            returnArray[2] = end;
            break;
        case "deadline":
            startIndex = -1;
            for (int i = 0; i < descriptionArray.length; i++) {
                if (descriptionArray[i].equals("/by")) { // we cannot find by event.
                    startIndex = i;
                }
            }
            if (startIndex.equals(-1)) {
                throw new InvalidParametersException();
            }
            taskDesc = String.join(" ",
              Arrays.copyOfRange(descriptionArray, 1, startIndex));
            start = String.join(" ",
              Arrays.copyOfRange(descriptionArray, startIndex + 1, descriptionArray.length));
            returnArray[0] = taskDesc;
            returnArray[1] = start;
            break;
        default:
            return returnArray;
        }
        return returnArray;
    }

    /**
     * Takes in a potential date and parses it into a LocalDateTime format.
     *
     * @param potentialDate The String version of the input date.
     * @return A LocalDateTime version of the input.
     * @throws InvalidDateTimeException Thrown if error in conversion process.
     */
    public static LocalDateTime parseDate(String potentialDate) throws InvalidDateTimeException {
        String time;
        Integer year;
        Integer month;
        Integer day;
        try {
            String[] dd = potentialDate.split(" ");
            String[] dateArr = dd[0].split("-");
            time = dd[1];
            year = Integer.parseInt(dateArr[0]);
            month = Integer.parseInt(dateArr[1]);
            day = Integer.parseInt(dateArr[2]);
        } catch (Exception e) {
            throw new InvalidDateTimeException();
        }
        try {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2));
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (Exception e) {
            throw new InvalidDateTimeException();
        }
    }
}
