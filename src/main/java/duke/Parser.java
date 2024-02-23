package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToggleMarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parses user input and returns corresponding Command objects for execution.
 */
public class Parser {
    private static final String patternBye = "^bye";
    private static final String patternList = "^list";
    private static final String patternMark = "^mark \\d+";
    private static final String patternUnmark = "^unmark \\d+";
    private static final String patternDelete = "^delete \\d+";
    private static final String patternTodo = "^todo .+";
    private static final String patternDeadline = "^deadline .+ \\/by .+";
    private static final String patternEvent = "^event .+ \\/from .+ \\/to .+";
    private static final String patternFind = "^find \\S+$";


    public Parser() {
        //do nothing
    }

    /**
     * Helper function that checks if a string matches a regex pattern.
     *
     * @param input a String taken from the command line user input
     * @param pattern a String defining the regex pattern of a specific command type
     * @return a boolean value signifying if the input matches the regex pattern
     */
    private static boolean matchesPattern(String input, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        return m.matches();
    }

    /**
     *  Method that handles user input and returns an appropriate Command object
     *  to be executed in the main program.
     *
     * @param input a String taken from the user's command line input
     * @return a Command object corresponding to the user's input
     * @throws IllegalArgumentException if user input format is unrecognised
     */
    public static Command handleInput(String input) {
        try {
            if (matchesPattern(input, patternBye)) {
                return new ExitCommand();
            } else if (matchesPattern(input, patternList)) {
                return new ListCommand();
            } else if (matchesPattern(input, patternMark)) {
                String[] wordArray = input.split(" ", 0);
                Integer index = Integer.parseInt(wordArray[1]);
                return new ToggleMarkCommand(index, true);
            } else if (matchesPattern(input, patternUnmark)) {
                String[] wordArray = input.split(" ", 0);
                Integer index = Integer.parseInt(wordArray[1]);
                return new ToggleMarkCommand(index, false);
            } else if (matchesPattern(input, patternDelete)) {
                String[] wordArray = input.split(" ", 0);
                Integer index = Integer.parseInt(wordArray[1]);
                return new DeleteCommand(index);
            } else if (matchesPattern(input, patternTodo)) {
                String tempString = input.substring(5).trim();
                Todo todo = new Todo(tempString);
                return new AddCommand(todo);
            } else if (matchesPattern(input, patternDeadline)) {
                String tempString = input.substring(9).trim();
                String[] tempArray = tempString.split("/by", 0);
                if (tempArray.length == 1) {
                    throw new IllegalArgumentException("Blunder! "
                            + "Declare yer deadline as such: 'deadline * /by *', ye scurvy dog!");
                }
                String description = tempArray[0].trim();
                LocalDateTime by = parseDate(tempArray[1].trim());
                Deadline deadline = new Deadline(description, by);
                return new AddCommand(deadline);
            } else if (matchesPattern(input, patternEvent)) {
                String tempString = input.substring(6).trim();
                String[] tempArray = tempString.split("/from", 0);
                if (tempArray.length == 1) {
                    throw new IllegalArgumentException("Blunder! "
                            + "Declare yer event as such: 'event * /from * /to *', "
                            + "ye scurvy dog!");
                }
                String description = tempArray[0].trim();
                tempString = tempArray[1].trim();
                tempArray = tempString.split("/to", 0);
                if (tempArray.length == 1) {
                    throw new IllegalArgumentException("Blunder! "
                            + "Declare yer event as such: 'event * /from * /to *', "
                            + "ye scurvy dog!");
                }
                LocalDateTime from = parseDate(tempArray[0].trim());
                LocalDateTime to = parseDate(tempArray[1].trim());
                Event event = new Event(description, from, to);
                return new AddCommand(event);
            } else if (matchesPattern(input, patternFind)) {
                String[] wordArray = input.split(" ", 0);
                String keyword = wordArray[1];
                return new FindCommand(keyword);
            } else {
                throw new IllegalArgumentException("Arrr, me apologies! I cannot fathom that.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Blunder! "
                    + "I be searchin' the seas but couldn't spy the task ye named, me heartie!");
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Blunder! "
                    + "Declare yer dates as dd/MM/yyyy HHmm, ye scurvy dog!");
        }
    }

    /**
     * Helper function to convert a String input into a LocalDateTime object.
     *
     * @param input a String representing a date in the format dd/MM/yyyy HHmm
     * @return a corresponding LocalDateTime object
     * @throws IllegalArgumentException if date String not in the correct format
     */
    public static LocalDateTime parseDate(String input) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(input, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Blunder! "
                    + "The date format ye provided be as tangled as a ship's riggin'.\n"
                    + "Write yer dates in the format dd/MM/yyyy HHmm or dd/MM/yyyy");
        }
        return time;
    }
}
