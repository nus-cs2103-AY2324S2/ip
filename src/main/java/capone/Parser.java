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
import capone.commands.HelpCommand;
import capone.commands.InvalidCommand;
import capone.commands.ListCommand;
import capone.commands.MarkCommand;
import capone.commands.TodoCommand;
import capone.commands.UnmarkCommand;
import capone.exceptions.InvalidDateException;
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
     * Reads a line of user input from the console.
     *
     * @return The user input as a String.
     */
    public static String readUserInput() {
        return Parser.scanner.nextLine();
    }

    /**
     * Processes user inputs and returns the appropriate
     * Command instance based on the first word of the input.
     *
     * @return Command object corresponding to the user input.
     */
    public static Command processInputs() {
        Parser.inputList = Parser.splitInput(Parser.readUserInput());
        String firstWord = inputList.get(0);

        if (firstWord.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (firstWord.equalsIgnoreCase("mark")) {
            return new MarkCommand(Parser.inputList);
        } else if (firstWord.equalsIgnoreCase("unmark")) {
            return new UnmarkCommand(Parser.inputList);
        } else if (firstWord.equalsIgnoreCase("todo")) {
            return new TodoCommand(Parser.inputList);
        } else if (firstWord.equalsIgnoreCase("deadline")) {
            return new DeadlineCommand(inputList);
        } else if (firstWord.equalsIgnoreCase("event")) {
            return new EventCommand(inputList);
        } else if (firstWord.equalsIgnoreCase("delete")) {
            return new DeleteCommand(inputList);
        } else if (firstWord.equalsIgnoreCase("bye")) {
            return new ByeCommand(scanner);
        } else if (firstWord.equalsIgnoreCase("help")) {
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
     * Processes the date and time components and returns a LocalDateTime object.
     *
     * @param date The LocalDate object representing the date.
     * @param time The LocalTime object representing the time.
     * @return LocalDateTime object combining the date and time.
     */
    public static LocalDateTime processDateTime(LocalDate date, LocalTime time) {
        if (date != null) {
            if (time != null) {
                return date.atTime(time);
            } else {
                return date.atStartOfDay();
            }
        } else {
            if (time != null) {
                return LocalDate.now().plusDays(1).atTime(time);
            } else {
                return null;
            }
        }
    }

    /**
     * Parses the input string into a LocalDate object.
     *
     * @param date The input string representing the date.
     * @return LocalDate object parsed from the input.
     * @throws InvalidDateException If the input string is not a valid date.
     */
    public static LocalDate parseDate(String date) throws InvalidDateException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, dateFormatter);
        } catch (DateTimeException e) {
            throw new InvalidDateException("Oops! You have entered an invalid date. Please try again.");
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
}
