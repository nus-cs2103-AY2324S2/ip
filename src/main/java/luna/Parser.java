package luna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Parser for human text to program commands.
 * Handles dissemination of commands depending on the user input, including human errors.
 */
public class Parser {

    private static final String dateFormat = "dd-MM-yyyy";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

    /**
     * Identifies the string to contain command specific keywords and prompts the respective command.
     * Prompts an Invalid Command if the additional arguments do not fit the command criteria
     *
     * @param str the command string.
     * @return the command to be executed.
     */
    public static Command parse(String str) {
        String[] strings = str.trim().split(" ");
        if (strings[0].equalsIgnoreCase("list")) {
            return parseForList(strings);
        } else if (strings[0].equalsIgnoreCase("todo")) {
            return parseForTodo(strings);
        } else if (strings[0].equalsIgnoreCase("deadline")) {
            return parseForDeadline(strings);
        } else if (strings[0].equalsIgnoreCase("event")) {
            return parseForEvent(strings);
        } else if (strings[0].equalsIgnoreCase("mark")) {
            return parseForMark(strings);
        } else if (strings[0].equalsIgnoreCase("unmark")) {
            return parseForUnmark(strings);
        } else if (strings[0].equalsIgnoreCase("delete")) {
            return parseForDelete(strings);
        } else if (strings[0].equalsIgnoreCase("save")) {
            return parseForSave(strings);
        } else if (strings[0].equalsIgnoreCase("load")) {
            return parseForLoad(strings);
        } else if (strings[0].equalsIgnoreCase("find")) {
            return parseForFind(strings);
        } else if (strings[0].equalsIgnoreCase("snooze")) {
            return parseForSnooze(strings);
        } else if (strings[0].equalsIgnoreCase("exit")) {
            return parseForExit();
        } else {
            return new InvalidCommand();
        }
    }

    /**
     * Parses the strings to check whether it abides ListCommand format and arguments
     *
     * @param strings user additional inputs
     */
    public static Command parseForList(String[] strings) {
        if (strings.length != 1) {
            return new InvalidCommand("too many arguments for [list]");
        } else {
            return new ListCommand();
        }
    }

    /**
     * Parses the strings to check whether it abides TodoCommand format and arguments
     *
     * @param strings user additional inputs
     */
    public static Command parseForTodo(String[] strings) {
        if (strings.length == 1) {
            return new InvalidCommand("too little arguments for [todo]");
        } else if (strings.length > 2) {
            return new InvalidCommand("too many arguments for [todo]");
        } else {
            return new TodoCommand(strings[1]);
        }
    }

    /**
     * Parses the strings to check whether it abides DeadlineCommand format and arguments
     *
     * @param strings user additional inputs
     */
    public static Command parseForDeadline(String[] strings) {
        if (strings.length < 3) {
            return new InvalidCommand("too little arguments for [deadline]");
        } else if (strings.length > 3) {
            return new InvalidCommand("too many arguments for [deadline]");
        } else if (isDateInvalid(strings[2])) {
            return new InvalidCommand("End Date incorrect format: " + dateFormat);
        } else {
            return new DeadlineCommand(strings[1], LocalDate.parse(strings[2], dateFormatter));
        }
    }

    /**
     * Parses the strings to check whether it abides EventCommand format and arguments
     *
     * @param strings user additional inputs
     */
    public static Command parseForEvent(String[] strings) {
        if (strings.length < 4) {
            return new InvalidCommand("too little arguments for [event]");
        } else if (strings.length > 4) {
            return new InvalidCommand("too many arguments for [event]");
        } else if (isDateInvalid(strings[2])) {
            return new InvalidCommand("Start Date incorrect format: " + dateFormat);
        } else if (isDateInvalid(strings[3])) {
            return new InvalidCommand("End Date incorrect format: " + dateFormat);
        } else {
            return new EventCommand(strings[1], LocalDate.parse(strings[2], dateFormatter),
                    LocalDate.parse(strings[3], dateFormatter));
        }
    }

    /**
     * Parses the strings to check whether it abides MarkCommand format and arguments
     *
     * @param strings user additional inputs
     */
    public static Command parseForMark(String[] strings) {
        if (strings.length == 1) {
            return new InvalidCommand("too little arguments for [mark]");
        } else if (strings.length > 2) {
            return new InvalidCommand("too many arguments for [mark]");
        } else if (isNonInteger(strings[1])) {
            return new InvalidCommand("Index value must be numeric");
        } else {
            return new MarkCommand(Integer.parseInt(strings[1]) - 1);
        }
    }

    /**
     * Parses the strings to check whether it abides UnmarkCommand format and arguments
     *
     * @param strings user additional inputs
     */
    public static Command parseForUnmark(String[] strings) {
        if (strings.length == 1) {
            return new InvalidCommand("too little arguments for [unmark]");
        } else if (strings.length > 2) {
            return new InvalidCommand("too many arguments for [unmark]");
        } else if (isNonInteger(strings[1])) {
            return new InvalidCommand("Index value must be numeric");
        } else {
            return new UnmarkCommand(Integer.parseInt(strings[1]) - 1);
        }
    }

    /**
     * Parses the strings to check whether it abides DeleteCommand format and arguments
     *
     * @param strings user additional inputs
     */
    public static Command parseForDelete(String[] strings) {
        if (strings.length == 1) {
            return new InvalidCommand("too little arguments for [delete]");
        } else if (strings.length > 2) {
            return new InvalidCommand("too many arguments for [delete]");
        } else if (isNonInteger(strings[1])) {
            return new InvalidCommand("Index value must be numeric");
        } else {
            return new DeleteCommand(Integer.parseInt(strings[1]) - 1);
        }
    }

    /**
     * Parses the strings to check whether it abides SaveCommand format and arguments
     *
     * @param strings user additional inputs
     */
    public static Command parseForSave(String[] strings) {
        if (strings.length != 1) {
            return new InvalidCommand("too many arguments for [save]");
        } else {
            return new SaveCommand();
        }
    }

    /**
     * Parses the strings to check whether it abides LoadCommand format and arguments
     *
     * @param strings user additional inputs
     */
    public static Command parseForLoad(String[] strings) {
        if (strings.length != 1) {
            return new InvalidCommand("too many arguments for [load]");
        } else {
            return new LoadCommand();
        }
    }

    /**
     * Parses the strings to check whether it abides FindCommand format and arguments
     *
     * @param strings user additional inputs
     */
    public static Command parseForFind(String[] strings) {
        if (strings.length < 2) {
            return new InvalidCommand("too little arguments for [find]");
        } else if (strings.length > 2) {
            return new InvalidCommand("too many arguments for [find]");
        } else {
            return new FindCommand(strings[1]);
        }
    }

    /**
     * Parses the strings to check whether it abides SnoozeCommand format and arguments
     *
     * @param strings user additional inputs
     */
    public static Command parseForSnooze(String[] strings) {
        if (strings.length < 3) {
            return new InvalidCommand("too little arguments for [snooze]");
        } else if (strings.length > 3) {
            return new InvalidCommand("too many arguments for [snooze]");
        } else if (isNonInteger(strings[1])) {
            return new InvalidCommand("Index value must be numeric");
        } else if (isNonInteger(strings[2])) {
            return new InvalidCommand("Days for snoozing must be numeric");
        } else {
            return new SnoozeCommand(Integer.parseInt(strings[1]) - 1, Integer.parseInt(strings[2]));
        }
    }

    /**
     * Parses the strings to check whether it abides Exits format and arguments
     */
    public static Command parseForExit() {
        return new ExitCommand();
    }


    /**
     * Returns true if the given string is in a date format.
     *
     * @param dateStr the string to check validity.
     * @return boolean whether string is a valid date.
     */
    public static boolean isDateInvalid(String dateStr) {
        try {
            LocalDate.parse(dateStr, dateFormatter);
            return false;
        } catch (DateTimeParseException e) {
            return true;
        }
    }

    /**
     * Returns false if the given string is an integer value.
     *
     * @param str the string to check validity.
     * @return boolean whether string is not an integer.
     *
     */
    public static boolean isNonInteger(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
