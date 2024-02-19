package zhen;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import zhen.command.*;
import zhen.task.*;

/**
 * Parser class parses the input from user to commands.
 */
public class Parser {
    /**
     * Parses the data in string format to the specified LocalDate format.
     *
     * @param date String representation of a date.
     * @return LocalDate representation of the data inputted by the user.
     */
    public static LocalDate parseDate(String date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formattedTime = LocalDate.parse(date, timeFormatter);
        return formattedTime;
    }

    /**
     * Parses the string format command from the user to command that could be executed by the program.
     *
     * @param userInput The input from users.
     * @return Command object that represent the action to be performed.
     */
    public static Command parse(String userInput) {
        if (parseAddCommand(userInput) != null) {
            return parseAddCommand(userInput);
        }
        if (parseMarkingCommands(userInput) != null) {
            return parseMarkingCommands(userInput);
        }
        if (parseDeleteCommand(userInput) != null) {
            return parseDeleteCommand(userInput);
        }
        if (parseFindCommand(userInput) != null) {
            return parseFindCommand(userInput);
        }
        if (parseSingleWordCommands(userInput) != null) {
            return parseSingleWordCommands(userInput);
        } else {
            return new DontknowCommand();
        }
    }
    private static Command parseAddCommand(String userInput) {
        if (userInput.length() >= 4 && userInput.substring(0, 4).equals("todo")) {
            return new AddCommand(new Todos(userInput.substring(4)));
        } else if (userInput.length() >= 8 && userInput.substring(0, 8).equals("deadline")) {
            String[] strarr = processDeadlineMsg(userInput.substring(8));
            try {
                return new AddCommand(new Deadline(strarr[0], parseDate(strarr[1])));
            } catch (Exception e) {
                return new AddCommand(new Deadline(strarr[0], strarr[1]));
            }
        } else if (userInput.length() >= 5 && userInput.substring(0, 5).equals("event")) {
            String[] strarr = processEventMsg(userInput.substring(5));
            try {
                return new AddCommand(new Event(strarr[0], parseDate(strarr[1]), parseDate(strarr[2])));
            } catch (Exception e) {
                return new AddCommand(new Event(strarr[0], strarr[1], strarr[2]));
            }
        }
        return null;
    }

    private static Command parseMarkingCommands(String userInput) {
        if (userInput.length() > 4 && userInput.substring(0, 4).equals("mark")) {
            int number = Integer.parseInt(userInput.substring(5));
            return new MarkCommand(number);
        } else if (userInput.length() > 6 && userInput.substring(0, 6).equals("unmark")) {
            int number = Integer.parseInt(userInput.substring(7));
            return new UnmarkCommand(number);
        }
        return null;
    }

    private static Command parseDeleteCommand(String userInput) {
        if (userInput.length() > 6 && userInput.substring(0, 6).equals("delete")) {
            int number = Integer.parseInt(userInput.substring(7));
            return new DeleteCommand(number);
        }
        return null;
    }

    private static Command parseFindCommand(String userInput) {
        if (userInput.length() > 4 && userInput.substring(0, 4).equals("find")) {
            String stringToFind = userInput.substring(5);
            return new FindCommand(stringToFind);
        }
        return null;
    }

    private static Command parseSingleWordCommands(String userInput) {
        switch (userInput) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        default:
            return null;
        }
    }
    /**
     * Extracts important information in user's command and organizes them into a list of String.
     *
     * @param userInput User's input with only informative parts, i.e. without the command part.
     * @return A list of String with three elements, the first element is the information about the event,
     * the second element is the starting time of the event, and the third element is the ending time of the event.
     */
    static String[] processEventMsg(String userInput) {
        String[] eventDetails = new String[3];
        String[] splitByFrom = userInput.split("/from");
        if (splitByFrom.length == 2) {
            eventDetails[0] = splitByFrom[0].trim();
            String[] strArr = splitByFrom[1].split("/to");
            eventDetails[1] = strArr[0].trim();
            if (strArr.length == 2) {
                eventDetails[2] = strArr[1].trim();
            } else {
                eventDetails[2] = "";
            }
        } else {
            String[] strArr = splitByFrom[0].split("/to");
            if (strArr.length == 2) {
                eventDetails[0] = strArr[0].trim();
                eventDetails[1] = "";
                eventDetails[2] = strArr[1].trim();
            } else {
                eventDetails[0] = strArr[0].trim();
                eventDetails[1] = "";
                eventDetails[2] = "";
            }
        }
        return eventDetails;
    }
    /**
     * Extracts important information in user's command and organizes them into a list of String.
     *
     * @param userInput User's input with only informative parts, i.e. without the command part
     * @return A list of String of two elements, the first element is the information about the event,
     * the second element is the deadline of the event.
     */
    static String[] processDeadlineMsg(String userInput) {
        String[] deadlineDetails = new String[2];
        String[] splitByBy = userInput.split("/by");
        if (splitByBy.length == 2) {
            deadlineDetails[0] = splitByBy[0].trim();
            deadlineDetails[1] = splitByBy[1].trim();
        } else {
            deadlineDetails[0] = (splitByBy[0].trim());
            deadlineDetails[1] = "";
        }
        return deadlineDetails;
    }
}
