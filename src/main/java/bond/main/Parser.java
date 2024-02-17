package bond.main;

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
     * Changes the date format from "MMM dd yyyy" to "yyyy-MM-dd".
     *
     * @param month The month in the date.
     * @param day   The day in the date.
     * @param year  The year in the date.
     * @return The date in the format "yyyy-MM-dd".
     */
    public static String changeDateFormat(String month, String day, String year) {
        String newMonth = "";
        String newDay = "";

        switch (month) {
        case "Jan":
            newMonth = "01";
            break;
        case "Feb":
            newMonth = "02";
            break;
        case "Mar":
            newMonth = "03";
            break;
        case "Apr":
            newMonth = "04";
            break;
        case "May":
            newMonth = "05";
            break;
        case "Jun":
            newMonth = "06";
            break;
        case "Jul":
            newMonth = "07";
            break;
        case "Aug":
            newMonth = "08";
            break;
        case "Sep":
            newMonth = "09";
            break;
        case "Oct":
            newMonth = "10";
            break;
        case "Nov":
            newMonth = "11";
            break;
        case "Dec":
            newMonth = "12";
            break;
        default:
            break;
        }

        if (day.length() == 1) {
            newDay = "0" + day;
        } else {
            newDay = day;
        }

        return year + "-" + newMonth + "-" + newDay;
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

        if (components[0].equalsIgnoreCase("todo")) {

            // No valid task name specified for a todo task
            if (components.length == 1) {
                BondException.raiseException("todo", "EMPTY_DESCRIPTION");
            }

            for (int i = 1; i < components.length; i++) {
                taskName += components[i] + " ";
            }

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
