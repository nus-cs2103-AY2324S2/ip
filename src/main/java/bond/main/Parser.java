package bond.main;

import java.util.HashMap;

import bond.command.AddDeadlineCommand;
import bond.command.AddEventCommand;
import bond.command.AddToDoCommand;
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
     * Checks if the String representation of timing is in a valid format.
     *
     * @param timing The timing to be checked.
     * @return True if the timing is in a valid format, false otherwise.
     */
    public static boolean isValidTiming(String timing) {

        if (timing.length() < 3) {
            return false;
        }

        boolean endsWithAmPm = timing.toLowerCase().endsWith("am")
                || timing.toLowerCase().endsWith("pm");

        if (Parser.isNumber(timing) && timing.length() == 4) {

            int hours = Integer.parseInt(timing.substring(0, 2));
            int minutes = Integer.parseInt(timing.substring(2));

            boolean validHour = hours >= 0 && hours <= 23;
            boolean validMinute = minutes >= 0 && minutes <= 59;

            return validHour && validMinute;
        } else if (endsWithAmPm) {

            String time = timing.toLowerCase().replace("am", "");
            time = time.toLowerCase().replace("pm", "");
            String[] hoursAndMinutes = time.split("\\.");

            if (hoursAndMinutes.length == 1 && Parser.isNumber(hoursAndMinutes[0])) {
                int hours = Integer.parseInt(hoursAndMinutes[0]);
                return hours >= 1 && hours <= 12;
            } else if (hoursAndMinutes.length == 2) {

                if (Parser.isNumber(hoursAndMinutes[0]) && Parser.isNumber(hoursAndMinutes[1])) {
                    int hours = Integer.parseInt(hoursAndMinutes[0]);
                    int minutes = Integer.parseInt(hoursAndMinutes[1]);
                    boolean validHour = hours >= 1 && hours <= 12;
                    boolean validMinute = minutes >= 0 && minutes <= 59;
                    return validHour && validMinute;
                }
            }

            return false;
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
     * Parses the user input and creates the appropriate Command object.
     *
     * @param userCommand The user input to be parsed.
     * @return The Command object that is created based on the user input.
     * @throws BondException The exception that is raised if the user input is
     *                       invalid.
     */
    public static Command parse(String userCommand) throws BondException {

        String taskName = "";

        String[] components = userCommand.split(" ");

        // Invalid Command syntax
        if (components.length == 0 || !Parser.isValidCommand(components[0])) {
            BondException.raiseException("", "INVALID_COMMAND_TYPE");
        }

        assert components.length > 0 : "Command should not be empty";

        if (components[0].equalsIgnoreCase("todo")) {

            // No valid task name specified for a todo task
            if (components.length == 1) {
                BondException.raiseException("todo", "EMPTY_DESCRIPTION");
            }

            for (int i = 1; i < components.length; i++) {
                taskName += components[i] + " ";
            }

            assert taskName.length() > 0 : "Task name should not be empty";

            taskName = taskName.trim();

            return new AddToDoCommand(taskName);

        } else if (components[0].equalsIgnoreCase("deadline")) {

            if (components.length == 1) {
                BondException.raiseException("deadline", "EMPTY_DESCRIPTION");
            }

            String deadline = "";

            for (int i = 1; i < components.length; i++) {

                if (components[i].equals("/by")) {

                    for (int j = i + 1; j < components.length; j++) {
                        deadline += components[j] + " ";
                    }

                    break;
                } else {
                    taskName += components[i] + " ";
                }
            }

            assert taskName.length() > 0 : "Task name should not be empty";
            assert deadline.length() > 0 : "Deadline should not be empty";

            taskName = taskName.trim();
            deadline = deadline.trim();

            return new AddDeadlineCommand(taskName, deadline);

        } else if (components[0].equalsIgnoreCase("event")) {

            if (components.length == 1) {
                BondException.raiseException("event", "EMPTY_DESCRIPTION");
            }

            int state = 0;
            String start = "";
            String end = "";

            for (int i = 1; i < components.length; i++) {

                if (components[i].equals("/from")) {
                    state = 1;
                } else if (components[i].equals("/to")) {
                    state = 2;
                }

                switch (state) {

                case 0:
                    taskName += components[i] + " ";
                    break;

                case 1:
                    if (!components[i].equals("/from")) {
                        start += components[i] + " ";
                    }
                    break;

                case 2:
                    if (!components[i].equals("/to")) {
                        end += components[i] + " ";
                    }
                    break;

                default:
                    break;
                }
            }

            assert taskName.length() > 0 : "Task name should not be empty";
            assert start.length() > 0 : "Start date should not be empty";
            assert end.length() > 0 : "End date should not be empty";

            taskName = taskName.trim();
            start = start.trim();
            end = end.trim();

            return new AddEventCommand(taskName, start, end);

        } else if (components[0].equalsIgnoreCase("list")) {

            if (components.length != 1) {
                BondException.raiseException("list", "EXTRA_DETAILS");
            }

            return new ListCommand();

        } else if (components[0].equalsIgnoreCase("mark")) {

            if (components.length == 1) {
                BondException.raiseException("mark", "MISSING_INDEX");
            } else if (!Parser.isNumber(components[1])) {
                BondException.raiseException("mark", "INVALID_INDEX");
            }

            int index = Integer.parseInt(components[1]) - 1;

            return new MarkCommand(index);

        } else if (components[0].equalsIgnoreCase("unmark")) {

            if (components.length == 1) {
                BondException.raiseException("unmark", "MISSING_INDEX");
            } else if (!Parser.isNumber(components[1])) {
                BondException.raiseException("unmark", "INVALID_INDEX");
            }

            int index = Integer.parseInt(components[1]) - 1;

            return new UnmarkCommand(index);

        } else if (components[0].equalsIgnoreCase("bye")) {

            if (components.length != 1) {
                BondException.raiseException("bye", "EXTRA_DETAILS");
            }

            return new ExitCommand();

        } else if (components[0].equalsIgnoreCase("delete")) {

            if (components.length == 1) {
                BondException.raiseException("delete", "MISSING_INDEX");
            } else if (!Parser.isNumber(components[1])) {
                BondException.raiseException("delete", "INVALID_INDEX");
            }

            int index = Integer.parseInt(components[1]) - 1;

            return new DeleteCommand(index);

        } else if (components[0].equalsIgnoreCase("find")) {

            if (components.length == 1) {
                BondException.raiseException("find", "EMPTY_DESCRIPTION");
            } else if (components.length > 2) {
                BondException.raiseException("find", "EXTRA_DETAILS");
            } else if (Parser.isNumber(components[1])) {
                BondException.raiseException("find", "INVALID_KEYWORD");
            }

            String keyword = components[1];
            return new FindCommand(keyword);

        } else {
            return new InvalidCommand();
        }
    }
}
