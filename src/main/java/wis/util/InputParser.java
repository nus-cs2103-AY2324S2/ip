package wis.util;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import wis.Action;

/**
 * Parser for parsing real-time user inputs.
 */
public class InputParser extends Parser {
    /**
     * Parses a line of user input to determine the action.
     *
     * @param input Line of input from user.
     * @param words Line of input split into array of words.
     * @return Action commanded by the line.
     */
    public static Action parseAction(String input, String[] words) {
        if (input.isEmpty()) {
            return Action.NONE;
        }
        if (input.equals("list")) {
            return Action.LIST;
        }
        if (words[0].equals("mark")) {
            return Action.MARK;
        }
        if (words[0].equals("unmark")) {
            return Action.UNMARK;
        }
        if (words[0].equals("delete")) {
            return Action.DELETE;
        }
        if (words[0].equals("todo")) {
            return Action.ADD_TODO;
        }
        if (words[0].equals("deadline")) {
            return Action.ADD_DEADLINE;
        }
        if (words[0].equals("event")) {
            return Action.ADD_EVENT;
        }
        if (words[0].equals("find")) {
            return Action.FIND;
        }
        return Action.INVALID;
    }

    /**
     * Parses user input for description of todo task.
     *
     * @param input Line of user input.
     * @return Description of todo task.
     * @throws InputMismatchException  If parsing fails as the line has
     * unknown format.
     */
    public static String parseTodo(String input) throws InputMismatchException {
        Pattern pattern = Pattern.compile("^todo ([^ ].*)$");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new InputMismatchException();
        }
        return matcher.group(1);
    }

    private static boolean isSingleOccurrence(String pattern, String input) {
        String[] parts = input.split(pattern);
        return parts.length == 2;
    }

    /**
     * Parses user input for description and deadline of deadline task.
     *
     * @param input Line of user input.
     * @return String Array containing description and deadline of deadline task.
     * @throws InputMismatchException  If parsing fails as the line has
     * unknown format.
     */
    public static String[] parseDeadline(String input) {
        String[] ret = new String[3];
        Pattern pattern = Pattern.compile("^deadline ([^ ].*) /by ([^ ].*)$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find() && isSingleOccurrence("/by", input)) {
            ret[0] = matcher.group(1);
            ret[1] = matcher.group(2);
        } else {
            throw new InputMismatchException("Unknown input format");
        }
        return ret;
    }

    /**
     * Parses user input for description of event task.
     *
     * @param input Line of user input.
     * @return String Array containing description, start time and end time of
     * deadline task.
     * @throws InputMismatchException  If parsing fails as the line has
     * unknown format.
     */
    public static String[] parseEvent(String input) {
        String[] ret = new String[3];
        Pattern pattern = Pattern.compile("^event ([^ ].*) /from ([^ ].*) /to ([^ ].*)$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find() &&
                isSingleOccurrence("/from", input) &&
                isSingleOccurrence("/to", input)) {
            ret[0] = matcher.group(1);
            ret[1] = matcher.group(2);
            ret[2] = matcher.group(3);
        } else {
            throw new InputMismatchException("Unknown input format");
        }
        return ret;
    }

    /**
     * Parses date and time input strings to return a Java LocalDateTime object.
     *
     * @param string Strings of the form yyyy-mm-dd hh:mm.
     * @return A Java LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String string)
            throws DateTimeParseException, ArrayIndexOutOfBoundsException {
        // Input format: 2023-12-03 10:15
        String[] strings = string.split(" ");
        return LocalDateTime.parse(strings[0] + "T" + strings[1]);
    }
}
