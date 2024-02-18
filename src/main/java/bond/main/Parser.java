package bond.main;

import java.util.Arrays;
import java.util.HashMap;

import bond.command.AddDeadlineCommand;
import bond.command.AddEventCommand;
import bond.command.AddTodoCommand;
import bond.command.Command;
import bond.command.DeleteCommand;
import bond.command.ExitCommand;
import bond.command.FindCommand;
import bond.command.InvalidCommand;
import bond.command.ListCommand;
import bond.command.MarkCommand;
import bond.command.UnmarkCommand;


/**
 * The Parser class is used to parse user input and create the appropriate
 * Command object.
 *
 * @author Benny Loh
 * @version 0.2
 */
public abstract class Parser {

    private static final HashMap<String, String> MONTH_FORMATS = new HashMap<>() {
        {
            put("Jan", "01");
            put("Feb", "02");
            put("Mar", "03");
            put("Apr", "04");
            put("May", "05");
            put("Jun", "06");
            put("Jul", "07");
            put("Aug", "08");
            put("Sep", "09");
            put("Oct", "10");
            put("Nov", "11");
            put("Dec", "12");
        }
    };



    /**
     * Changes the date format from "MMM dd yyyy" to "yyyy-MM-dd".
     *
     * @param month The month in the date.
     * @param day   The day in the date.
     * @param year  The year in the date.
     * @return The date in the format "yyyy-MM-dd".
     */
    public static String changeDateFormat(String month, String day, String year) {
        String newMonth = "";
        String newDay;

        if (MONTH_FORMATS.containsKey(month)) {
            newMonth = MONTH_FORMATS.get(month);
        }

        if (day.length() == 1) {
            newDay = "0" + day;
        } else {
            newDay = day;
        }

        return year + "-" + newMonth + "-" + newDay;
    }



    /**
     * Checks if the user input is a valid command.
     *
     * @param input The user input to be checked.
     * @return True if the user input is a valid command, false otherwise.
     */
    public static boolean isValidCommand(String input) {
        return Command.COMMANDS.contains(input.toLowerCase());
    }



    /**
     * Checks if the input is a number.
     *
     * @param input The input to be checked.
     * @return True if the input is a number, false otherwise.
     */
    public static boolean isNumber(String input) {
        char[] digits = input.toCharArray();
        boolean isNumber = true;

        for (char c : digits) {
            if (!Character.isDigit(c)) {
                isNumber = false;
                break;
            }
        }

        return isNumber;
    }



    /**
     * Checks if the String representation of time is in HHmm format.
     *
     * @param time The timing to be checked.
     * @return True if the time is in HHmm format, false otherwise.
     */
    private static boolean isInTwentyFourHourFormat(String time) {
        return Parser.isNumber(time) && time.length() == 4;
    }



    /**
     * Checks if a number is within a specified range.
     *
     * @param number     The number to be checked.
     * @param lowerBound The lower bound of the range.
     * @param upperBound The upper bound of the range.
     * @return True if the number is within the range, false otherwise.
     */
    private static boolean inBounds(int number, int lowerBound, int upperBound) {
        return number >= lowerBound && number <= upperBound;
    }



    /**
     * Checks if the String representation of time is in Am/Pm format.
     *
     * @param time The timing to be checked.
     * @return True if the time is in Am/Pm format, false otherwise.
     */
    private static boolean isAmPmFormat(String time) {
        return time.toLowerCase().endsWith("am") || time.toLowerCase().endsWith("pm");
    }

    /**
     * Checks if the String representation of timing is in a valid format.
     *
     * @param timing The timing to be checked.
     * @return True if the timing is in a valid format, false otherwise.
     */
    public static boolean isValidTiming(String timing) {

        int minHourForTwentyFourHourFormat = 0;
        int maxHourForTwentyFourHourFormat = 23;

        int minHourForAmPmFormat = 1;
        int maxHourForAmPmFormat = 12;

        int minMinute = 0;
        int maxMinute = 59;

        if (timing.length() < 3) {
            return false;
        }

        if (Parser.isInTwentyFourHourFormat(timing)) {

            int hours = Integer.parseInt(timing.substring(0, 2));
            int minutes = Integer.parseInt(timing.substring(2));

            boolean validHour = Parser.inBounds(hours,
                    minHourForTwentyFourHourFormat, maxHourForTwentyFourHourFormat);
            boolean validMinute = Parser.inBounds(minutes, minMinute, maxMinute);

            return validHour && validMinute;
        } else if (Parser.isAmPmFormat(timing)) {

            String time = timing.toLowerCase().replace("am", "");
            time = time.toLowerCase().replace("pm", "");
            String[] hoursAndMinutes = time.split("\\.");

            int hours = -1;

            if (Parser.isNumber(hoursAndMinutes[0])) {
                hours = Integer.parseInt(hoursAndMinutes[0]);
            }

            if (hours != -1 && hoursAndMinutes.length == 1) {
                return Parser.inBounds(hours, minHourForAmPmFormat, maxHourForAmPmFormat);
            }

            boolean minutesIsInt = hoursAndMinutes.length == 2 && Parser.isNumber(hoursAndMinutes[1]);

            if (hours != -1 && minutesIsInt) {
                int minutes = Integer.parseInt(hoursAndMinutes[1]);
                boolean validHour = Parser.inBounds(hours, minHourForAmPmFormat, maxHourForAmPmFormat);
                boolean validMinute = Parser.inBounds(minutes, minMinute, maxMinute);
                return validHour && validMinute;
            }
        }

        return false;
    }



    /**
     * Changes the timing format from "HHmm" to "HH.mm(am/pm)" if needed.
     *
     * @param time The timing to be changed.
     * @return The timing in the format "HH.mm(am/pm)" if needed.
     */
    public static String changeTimeFormat(String time) {

        String newTime;

        if (Parser.isNumber(time)) {
            int hours = Integer.parseInt(time.substring(0, 2));
            int minutes = Integer.parseInt(time.substring(2));

            if (hours == 0) {
                newTime = "12" + (minutes > 0 ? "." + minutes : "") + "am";
            } else if (hours < 12) {
                newTime = hours + (minutes > 0 ? "." + minutes : "") + "am";
            } else {
                newTime = (hours - 12) + (minutes > 0 ? "." + minutes : "") + "pm";
            }
        } else {
            newTime = time;
        }

        return newTime;
    }



    /**
     * Parses the user input for a todo command and creates the appropriate
     * Command object.
     *
     * @param components The components of the user input to be parsed.
     * @return The Command object that is created based on the user input.
     * @throws BondException The exception that is raised if the user input is
     *                       invalid.
     */
    private static AddTodoCommand parseTodo(String[] components) throws BondException {

        String taskName = "";

        // No valid task name specified for a todo task
        if (components.length == 1) {
            BondException.raiseException("todo", "EMPTY_DESCRIPTION");
        }

        for (int i = 1; i < components.length; i++) {
            taskName += components[i] + " ";
        }

        assert !taskName.isEmpty() : "Task name should not be empty";

        taskName = taskName.trim();

        return new AddTodoCommand(taskName);
    }



    /**
     * Parses the user input for a deadline command and creates the appropriate
     * Command object.
     *
     * @param components The components of the user input to be parsed.
     * @return The Command object that is created based on the user input.
     * @throws BondException The exception that is raised if the user input is
     *                       invalid.
     */
    private static AddDeadlineCommand parseDeadline(String[] components) throws BondException {

        if (components.length == 1) {
            BondException.raiseException("deadline", "EMPTY_DESCRIPTION");
        }

        String taskName;
        String deadline;

        taskName = Arrays.stream(components).skip(1).takeWhile(s -> !s.equals("/by"))
                .reduce((s1, s2) -> s1 + " " + s2).get();

        deadline = Arrays.stream(components).dropWhile(s -> !s.equals("/by"))
                .skip(1).reduce((s1, s2) -> s1 + " " + s2).get();

        assert !taskName.isEmpty() : "Task name should not be empty";
        assert !deadline.isEmpty() : "Deadline should not be empty";

        return new AddDeadlineCommand(taskName, deadline);
    }



    /**
     * Parses the user input for an event command and creates the appropriate
     * Command object.
     *
     * @param components The components of the user input to be parsed.
     * @return The Command object that is created based on the user input.
     * @throws BondException The exception that is raised if the user input is
     *                       invalid.
     */
    private static AddEventCommand parseEvent(String[] components) throws BondException {

        if (components.length == 1) {
            BondException.raiseException("event", "EMPTY_DESCRIPTION");
        }

        String taskName;
        String start;
        String end;

        taskName = Arrays.stream(components).skip(1).takeWhile(s -> !s.equals("/from"))
                .reduce((s1, s2) -> s1 + " " + s2).get();

        start = Arrays.stream(components).dropWhile(s -> !s.equals("/from")).skip(1)
                .takeWhile(s -> !s.equals("/to")).reduce((s1, s2) -> s1 + " " + s2).get();

        end = Arrays.stream(components).dropWhile(s -> !s.equals("/to"))
                .skip(1).reduce((s1, s2) -> s1 + " " + s2).get();

        assert !taskName.isEmpty() : "Task name should not be empty";
        assert !start.isEmpty() : "Start date should not be empty";
        assert !end.isEmpty() : "End date should not be empty";

        return new AddEventCommand(taskName, start, end);
    }



    /**
     * Parses the user input for a list command and creates the appropriate
     * Command object.
     *
     * @param components The components of the user input to be parsed.
     * @return The Command object that is created based on the user input.
     * @throws BondException The exception that is raised if the user input is
     *                       invalid.
     */
    private static ListCommand parseList(String[] components) throws BondException {
        if (components.length != 1) {
            BondException.raiseException("list", "EXTRA_DETAILS");
        }

        return new ListCommand();
    }



    /**
     * Parses the user input for a mark command and creates the appropriate
     * Command object.
     *
     * @param components The components of the user input to be parsed.
     * @return The Command object that is created based on the user input.
     * @throws BondException The exception that is raised if the user input is
     *                       invalid.
     */
    private static MarkCommand parseMark(String[] components) throws BondException {
        if (components.length == 1) {
            BondException.raiseException("mark", "MISSING_INDEX");
        } else if (!Parser.isNumber(components[1])) {
            BondException.raiseException("mark", "INVALID_INDEX");
        }

        int index = Integer.parseInt(components[1]) - 1;

        return new MarkCommand(index);
    }



    /**
     * Parses the user input for an unmark command and creates the appropriate
     * Command object.
     *
     * @param components The components of the user input to be parsed.
     * @return The Command object that is created based on the user input.
     * @throws BondException The exception that is raised if the user input is
     *                       invalid.
     */
    private static UnmarkCommand parseUnmark(String[] components) throws BondException {
        if (components.length == 1) {
            BondException.raiseException("unmark", "MISSING_INDEX");
        } else if (!Parser.isNumber(components[1])) {
            BondException.raiseException("unmark", "INVALID_INDEX");
        }

        int index = Integer.parseInt(components[1]) - 1;

        return new UnmarkCommand(index);
    }



    /**
     * Parses the user input for an exit command and creates the appropriate
     * Command object.
     *
     * @param components The components of the user input to be parsed.
     * @return The Command object that is created based on the user input.
     * @throws BondException The exception that is raised if the user input is
     *                       invalid.
     */
    private static ExitCommand parseExit(String[] components) throws BondException {
        if (components.length != 1) {
            BondException.raiseException("bye", "EXTRA_DETAILS");
        }

        return new ExitCommand();
    }



    /**
     * Parses the user input for a delete command and creates the appropriate
     * Command object.
     *
     * @param components The components of the user input to be parsed.
     * @return The Command object that is created based on the user input.
     * @throws BondException The exception that is raised if the user input is
     *                       invalid.
     */
    private static DeleteCommand parseDelete(String[] components) throws BondException {
        if (components.length == 1) {
            BondException.raiseException("delete", "MISSING_INDEX");
        } else if (!Parser.isNumber(components[1])) {
            BondException.raiseException("delete", "INVALID_INDEX");
        }

        int index = Integer.parseInt(components[1]) - 1;

        return new DeleteCommand(index);
    }



    /**
     * Parses the user input for a find command and creates the appropriate
     * Command object.
     *
     * @param components The components of the user input to be parsed.
     * @return The Command object that is created based on the user input.
     * @throws BondException The exception that is raised if the user input is
     *                       invalid.
     */
    private static FindCommand parseFind(String[] components) throws BondException {
        if (components.length == 1) {
            BondException.raiseException("find", "EMPTY_DESCRIPTION");
        } else if (components.length > 2) {
            BondException.raiseException("find", "EXTRA_DETAILS");
        } else if (Parser.isNumber(components[1])) {
            BondException.raiseException("find", "INVALID_KEYWORD");
        }

        String keyword = components[1];
        return new FindCommand(keyword);
    }



    /**
     * Parses the user input and creates the appropriate Command object.
     *
     * @param userCommand The user input to be parsed.
     * @return The Command object that is created based on the user input.
     * @throws BondException The exception that is raised if the user input is
     *                       invalid.
     */
    public static Command parse(String userCommand) throws BondException {

        String[] components = userCommand.split(" ");

        // Invalid Command syntax
        if (components.length == 0 || !Parser.isValidCommand(components[0])) {
            BondException.raiseException("", "INVALID_COMMAND_TYPE");
        }

        assert components.length > 0 : "Command should not be empty";

        String commandType = components[0].toLowerCase();

        switch (commandType) {
        case "todo":
            return parseTodo(components);
        case "deadline":
            return parseDeadline(components);
        case "event":
            return parseEvent(components);
        case "list":
            return parseList(components);
        case "mark":
            return parseMark(components);
        case "unmark":
            return parseUnmark(components);
        case "bye":
            return parseExit(components);
        case "delete":
            return parseDelete(components);
        case "find":
            return parseFind(components);
        default:
            assert false : "Program execution should not reach here.";
            return new InvalidCommand();
        }
    }
}
