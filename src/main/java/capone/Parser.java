package capone;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import capone.commands.ByeCommand;
import capone.commands.Command;
import capone.commands.DeadlineCommand;
import capone.commands.DeleteCommand;
import capone.commands.EventCommand;
import capone.commands.FindCommand;
import capone.commands.HelpCommand;
import capone.commands.InvalidCommand;
import capone.commands.ListCommand;
import capone.commands.MarkCommand;
import capone.commands.TodoCommand;
import capone.commands.UnmarkCommand;
import capone.commands.UpdateCommand;
import capone.exceptions.CaponeException;
import capone.exceptions.InvalidDateFormatException;
import capone.exceptions.InvalidTimeException;

/**
 * This class is responsible for processing user inputs and generating
 * corresponding command objects. It also provides methods for reading
 * and splitting user inputs, and checking date and time formats.
 *
 * @author Tay Rui-Jie
 */
public class Parser {
    private static ArrayList<String> inputList;
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Splits the user input into a list of strings.
     *
     * @param input The user input to be split.
     * @return ArrayList of tokens obtained by splitting the input.
     */
    public static ArrayList<String> splitInput(String input) {
        return new ArrayList<>(Arrays.asList(input.split("\\s+")));
    }

    /**
     * Processes user inputs and returns the appropriate
     * Command instance based on the first word of the input.
     *
     * @return Command object corresponding to the user input.
     */
    public static Command processInputs(String input) {
        Parser.inputList = Parser.splitInput(input);
        String firstWord = inputList.get(0);
        return Parser.findCommand(firstWord);
    }

    /**
     * Finds the command type from the user's input.
     *
     * @param command The user's input command.
     * @return A new instance of the user's command.
     */
    private static Command findCommand(String command) {
        if (command.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (command.equalsIgnoreCase("mark")) {
            return new MarkCommand(Parser.inputList);
        } else if (command.equalsIgnoreCase("unmark")) {
            return new UnmarkCommand(Parser.inputList);
        } else if (command.equalsIgnoreCase("todo")) {
            return new TodoCommand(Parser.inputList);
        } else if (command.equalsIgnoreCase("deadline")) {
            return new DeadlineCommand(Parser.inputList);
        } else if (command.equalsIgnoreCase("event")) {
            return new EventCommand(Parser.inputList);
        } else if (command.equalsIgnoreCase("delete")) {
            return new DeleteCommand(Parser.inputList);
        } else if (command.equalsIgnoreCase("find")) {
            return new FindCommand(Parser.inputList);
        } else if (command.equalsIgnoreCase("update")) {
            return new UpdateCommand(Parser.inputList);
        } else if (command.equalsIgnoreCase("bye")) {
            return new ByeCommand(scanner);
        } else if (command.equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else {
            return new InvalidCommand();
        }
    }

    /**
     * Checks if the given input string follows the date format 'YYYY-MM-DD'.
     *
     * @param input The input string to be checked.
     * @return True if the input matches the date format, false otherwise.
     */
    public static boolean isDateFormat(String input) {
        String dateFormatRegex = "\\d{4}-\\d{2}-\\d{2}";
        return input.matches(dateFormatRegex);
    }

    /**
     * Checks if the given input string follows the time
     * format 'HHmm' (24-hour format).
     *
     * @param input The input string to be checked.
     * @return True if the input matches the time format, false otherwise.
     */
    public static boolean isTimeFormat(String input) {
        String timeFormatRegex = "(\\d{4})";
        return input.matches(timeFormatRegex);
    }

    /**
     * Checks if the given LocalDateTime pair is valid.
     * (i.e. the starting date and time has to be before the ending date and time).
     *
     * @param startDateTime The starting date (and time).
     * @param endDateTime The ending date (and time).
     * @return True if startDateTime is before endDateTime, false otherwise.
     */
    public static boolean isVaildDatePair(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return (startDateTime.isBefore(endDateTime));
    }

    /**
     * Processes the date and time components and returns a LocalDateTime object.
     *
     * @param date The LocalDate object representing the date.
     * @param time The LocalTime object representing the time.
     * @return LocalDateTime object combining the date and time.
     */
    public static LocalDateTime processDateTime(LocalDate date, LocalTime time) {
        if (date == null && time == null) {
            return null;
        }
        if (date == null) {
            return LocalDate.now().plusDays(1).atTime(time);
        }
        if (time == null) {
            return date.atStartOfDay();
        }
        return date.atTime(time);
    }

    /**
     * Parses the input string into a LocalDate object.
     *
     * @param date The input string representing the date.
     * @return LocalDate object parsed from the input.
     * @throws InvalidDateFormatException If the input string is not a valid date.
     */
    public static LocalDate parseDate(String date) throws InvalidDateFormatException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, dateFormatter);
        } catch (DateTimeException e) {
            throw new InvalidDateFormatException("Oops! You have entered an invalid date. Please try again.");
        }
    }

    /**
     * Parses the input string into a LocalTime object.
     *
     * @param time The input string representing the time.
     * @return LocalTime object parsed from the input.
     * @throws InvalidTimeException If the input string is not a valid time.
     */
    public static LocalTime parseTime(String time) throws InvalidTimeException {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            return LocalTime.parse(time, timeFormatter);
        } catch (DateTimeException e) {
            throw new InvalidTimeException("Oops! You have entered an invalid time. Please try again.");
        }
    }

    /**
     * Parses string inputs into a LocalDateTime object.
     *
     * @param startNdx Starting index of the string to be parsed.
     * @param endNdx Ending index of the string to be parsed.
     * @param inputList List of strings to be parsed.
     * @return LocalDateTime object parsed from the input.
     * @throws InvalidDateFormatException If the input string is not a valid Date or Time.
     */
    public static LocalDateTime parseDateTime(int startNdx, int endNdx, ArrayList<String> inputList)
            throws CaponeException {
        LocalDate date = null;
        LocalTime time = null;

        // Parse the user string into LocalDate or LocalTime objects.
        for (int i = startNdx; i < endNdx; i++) {
            if (Parser.isDateFormat(inputList.get(i))) {
                date = Parser.parseDate(inputList.get(i));
            } else if (Parser.isTimeFormat(inputList.get(i))) {
                time = Parser.parseTime(inputList.get(i));
            } else {
                throw new InvalidDateFormatException("Oops! You have entered an invalid date. Please try again.");
            }
        }

        // Parse the LocalDate and LocalTime objects into a single LocalDateTime object.
        return Parser.processDateTime(date, time);
    }

    /**
     * Parses string inputs into a task description.
     *
     * @param startNdx Starting index of the string to be parsed.
     * @param endNdx Ending index of the string to be parsed.
     * @param inputList List of strings to be parsed.
     * @return String description parsed from the input.
     */
    public static String parseDescription(int startNdx, int endNdx, ArrayList<String> inputList) {
        StringBuilder description = new StringBuilder();
        for (int i = startNdx; i < endNdx; i++) {
            if (i == endNdx - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }
        return description.toString();
    }
}
