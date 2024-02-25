package Luna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Parser for human text to program commands.
 * Handles dissemination of commands depending on the user input, including human errors.
 */
public class Parser {

    final static String DATE_FORMAT = "dd-MM-yyyy";
    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

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
            if (strings.length != 1) {
                return new InvalidCommand("too many arguments for [list]");
            } else {
                return new ListCommand();
            }
        } else if (strings[0].equalsIgnoreCase("todo")) {
            if (strings.length == 1) {
                return new InvalidCommand("too little arguments for [todo]");
            } else if (strings.length > 2) {
                return new InvalidCommand("too many arguments for [todo]");
            } else {
                return new TodoCommand(strings[1]);
            }
        } else if (strings[0].equalsIgnoreCase("deadline")) {
            if (strings.length < 3) {
                return new InvalidCommand("too little arguments for [deadline]");
            } else if (strings.length > 3) {
                return new InvalidCommand("too many arguments for [deadline]");
            } else if (!isDateValid(strings[2])) {
                System.out.println("___" + strings[2]);

                return new InvalidCommand("End Date incorrect format: " + DATE_FORMAT);

            } else {
                return new DeadlineCommand(strings[1], LocalDate.parse(strings[2], dateFormatter));
            }
        } else if (strings[0].equalsIgnoreCase("event")) {
            if (strings.length < 4) {
                return new InvalidCommand("too little arguments for [event]");
            } else if (strings.length > 4) {
                return new InvalidCommand("too many arguments for [event]");
            } else if (!isDateValid(strings[2])) {
                return new InvalidCommand("Start Date incorrect format: " + DATE_FORMAT);
            } else if (!isDateValid(strings[3])) {
                return new InvalidCommand("End Date incorrect format: " + DATE_FORMAT);
            } else {
                return new EventCommand(strings[1], LocalDate.parse(strings[2], dateFormatter), LocalDate.parse(strings[3], dateFormatter));
            }
        } else if (strings[0].equalsIgnoreCase("mark")) {
            if (strings.length == 1) {
                return new InvalidCommand("too little arguments for [mark]");
            } else if (strings.length > 2) {
                return new InvalidCommand("too many arguments for [mark]");
            } else if (!isInteger(strings[1])) {
                return new InvalidCommand("Index value must be numeric");
            }else {
                return new MarkCommand(Integer.parseInt(strings[1])-1);
            }
        } else if (strings[0].equalsIgnoreCase("unmark")) {
            if (strings.length == 1) {
                return new InvalidCommand("too little arguments for [unmark]");
            } else if (strings.length > 2) {
                return new InvalidCommand("too many arguments for [unmark]");
            } else if (!isInteger(strings[1])) {
                return new InvalidCommand("Index value must be numeric");
            } else {
                return new UnmarkCommand(Integer.parseInt(strings[1])-1);
            }
        } else if (strings[0].equalsIgnoreCase("delete")) {
            if (strings.length == 1) {
                return new InvalidCommand("too little arguments for [delete]");
            } else if (strings.length > 2) {
                return new InvalidCommand("too many arguments for [delete]");
            } else if (!isInteger(strings[1])) {
                return new InvalidCommand("Index value must be numeric");
            } else {
                return new DeleteCommand(Integer.parseInt(strings[1])-1);
            }
        } else if (strings[0].equalsIgnoreCase("save")) {
            if (strings.length != 1) {
                return new InvalidCommand("too many arguments for [save]");
            } else {
                return new SaveCommand();
            }
        } else if (strings[0].equalsIgnoreCase("load")) {
            if (strings.length != 1) {
                return new InvalidCommand("too many arguments for [load]");
            } else {
                return new LoadCommand();
            }
        } else if (strings[0].equalsIgnoreCase("find")) {
            if (strings.length < 2) {
                return new InvalidCommand("too little arguments for [find]");
            } else if (strings.length > 2) {
                return new InvalidCommand("too many arguments for [find]");
            } else {
                return new FindCommand(strings[1]);
            }
        } else if (strings[0].equalsIgnoreCase("exit")) {
            return new ExitCommand();
        } else {
            return new InvalidCommand();
        }
    }


    /**
     * Returns true if the given string is in a date format
     *
     * @param dateStr the string to check validity
     * @return boolean whether string is a valid date
     */
    public static boolean isDateValid(String dateStr)
    {
        try {
            LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the given string is an integer value
     *
     * @param str the string to check validity
     * @return boolean whether string is an integer
     *
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


}
