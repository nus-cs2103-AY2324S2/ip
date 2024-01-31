package capone;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import capone.commands.*;
import capone.exceptions.CaponeException;

public class Parser {
    private static ArrayList<String> inputList;
    private static final Scanner scanner = new Scanner(System.in);

    public static ArrayList<String> splitInput(String input) {
        // Split inputs by space and store them in an arraylist for processing.
        return new ArrayList<>(Arrays.asList(input.split("\\s+")));
    }

    public static String readUserInput() {
        return Parser.scanner.nextLine();
    }

    // We process the user inputs and accordingly generate the correct command types.
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
     * Checks if date was an input. The recognized format is:
     * YYYY-MM-DD
     *
     * @param input the input string to be checked against.
     * @return true if a valid date is recognised, false otherwise.
     */
    public static boolean isDateFormat(String input) {
        String dateFormatRegex = "\\d{4}-\\d{2}-\\d{2}";

        // Check if the input string matches the format
        return input.matches(dateFormatRegex);
    }

    /**
     * Checks if time was an input. The recognized formats is:
     * 1800 (24-hour format).
     *
     * @param input the input string to be checked against.
     * @return true if a valid time is recognised, false otherwise.
     */
    public static boolean isTimeFormat(String input) {
        String timeFormatRegex = "(\\d{4})";

        // Check if the input string matches the format
        return input.matches(timeFormatRegex);
    }


    public static LocalDateTime processDateTime(LocalDate date, LocalTime time) {
        if (date != null) {
            if (time != null) {
                return date.atTime(time);
            } else {
                return date.atStartOfDay();
            }
        } else {
            // If only the time is specified, the deadline will be the time at the next day.
            if (time != null) {
                return LocalDate.now().plusDays(1).atTime(time);
            } else {
                // Else, if both date and time are null, return null input to use
                // the string input of date/time by user.
                return null;
            }
        }
    }

    public static LocalDate parseDate(String date) throws CaponeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, dateFormatter);
        } catch (DateTimeException e) {
            throw new CaponeException("Oops! You have entered an invalid date. Please try again.");
        }
    }

    public static LocalTime parseTime(String time) throws CaponeException {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            return LocalTime.parse(time, timeFormatter);
        } catch (DateTimeException e) {
            throw new CaponeException("Oops! You have entered an invalid time. Please try again.");
        }
    }

}
