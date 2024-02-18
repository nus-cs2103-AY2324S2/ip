package zhen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import zhen.command.*;
import zhen.task.*;

/**
 * The class used to parse the input from user to commands that
 * the program can execute
 */
public class Parser {
    /**
     * Parse the data in string formate to a specified LocalDate format
     * .
     * @param date the string representation of a date
     * @return the localDate representation of the data inputted by the user
     */
    public static LocalDate parseDate(String date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formattedTime = LocalDate.parse(date, timeFormatter);
        return formattedTime;
    }

    /**
     * Parse the String command by the user to command that could be executed by the program
     *
     * @param userInput The input from users
     * @return A command object that represent the action to be performed
     */
    public static Command parse(String userInput) {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.length() >= 4 && userInput.substring(0, 4).equals("todo")) {
            return new AddCommand(new Todos(userInput.substring(4)));
        } else if (userInput.length() >= 8 && userInput.substring(0, 8).equals("deadline")) {
            String[] strarr = processDeadlineMsg(userInput.substring(8));
            return new AddCommand(new Deadline(strarr[0], parseDate(strarr[1])));
        } else if (userInput.length() >= 5 && userInput.substring(0, 5).equals("event")) {
            String[] strarr = processEventMsg(userInput.substring(5));
            return new AddCommand(new Event(strarr[0], parseDate(strarr[1]), parseDate(strarr[2])));
        } else if (userInput.length() > 4 && userInput.substring(0, 4).equals("mark")) {
            int number = Integer.parseInt(userInput.substring(5));
            return new MarkCommand(number);
        } else if (userInput.length() > 6 && userInput.substring(0, 6).equals("unmark")) {
            int number = Integer.parseInt(userInput.substring(7));
            return new UnmarkCommand(number);
        } else if (userInput.length() > 6 && userInput.substring(0, 6).equals("delete")) {
            int number = Integer.parseInt(userInput.substring(7));
            return new DeleteCommand(number);
        } else if (userInput.length() > 4 && userInput.substring(0, 4).equals("find")) {
            String stringToFind = userInput.substring(5);
            return new FindCommand(stringToFind);
        } else {
            return new DontknowCommand();
        }
    }

    /**
     * Extract important information in user's command and organized them into a list of String
     *
     * @param userInput User's input with only informative parts, i.e. without the command part
     * @return A list of String of three elements, the first element is the information about the event,
     * the second element is the starting time of the event, and the third element is the ending time of the event
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
     * Extract important information in user's command and organized them into a list of String
     *
     * @param userInput User's input with only informative parts, i.e. without the command part
     * @return A list of String of two elements, the first element is the information about the event,
     * the second element is the deadline of the event
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
