package ukecat;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Reads string inputs and returns useful information.
 */
public class Parser {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Parse user input and stores the input and words array into the Storage class.
     * Leading and trailing whitespaces are also trimmed from the words array.
     */
    public static void parse(String input) {
        Storage.setInput(input.trim());
        Storage.setWords(Storage.getInput().split("\\s+"));

    }

    /**
     * Parses the input string to extract the description for a todo using regex.
     * If the input matches the pattern, the description is extracted and stored in the Storage class.
     * If the input does not match the expected format, a UkeCatException is thrown.
     *
     * @param input The input string representing a todo.
     * @throws UkeCatException If input does not match the expected format.
     */
    public static void parseToDo(String input) throws UkeCatException {
        Pattern pattern = Pattern.compile("^todo\\s+(.+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            Storage.setDesc(matcher.group(1)); // store first capturing group
        } else {
            throw new UkeCatException("Wrong format, use: todo <desc>");
        }
    }

    /**
     * Parses the input string to extract the description for a deadline using regex.
     * If the input matches the pattern, the description is extracted and stored in the Storage class.
     * If the input does not match the expected format, a UkeCatException is thrown.
     *
     * @param input The input string representing a deadline.
     * @throws UkeCatException If input does not match the expected format.
     */
    public static void parseDeadline(String input) throws UkeCatException {
        Pattern pattern = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            Storage.setDesc(matcher.group(1));
            Storage.setBy(LocalDate.parse(matcher.group(2)));
        } else {
            throw new UkeCatException("Wrong format, use: deadline <desc> /by yyyy-mm-dd");
        }
    }

    /**
     * Parses the input string to extract the description for an event using regex.
     * If the input matches the pattern, the description is extracted and stored in the Storage class.
     * If the input does not match the expected format, a UkeCatException is thrown.
     *
     * @param input The input string representing an event.
     * @throws UkeCatException If input does not match the expected format.
     */
    public static void parseEvent(String input) throws UkeCatException {
        Pattern pattern = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            Storage.setDesc(matcher.group(1));
            Storage.setStart(LocalDate.parse(matcher.group(2)));
            Storage.setEnd(LocalDate.parse(matcher.group(3)));
        } else {
            throw new UkeCatException("Wrong format, use: event <desc> /from yyyy-mm-dd /to yyyy-mm-dd");
        }
    }

    /**
     * Parses the user input or CSV representation of a recurring task and updates the Storage accordingly.
     * Supports adding a new recurring task or loading an existing one from CSV.
     * <p>
     * For user input:
     * Matches the input against the pattern for adding a new recurring task.
     * If a match is found, sets the next refresh date and updates the input accordingly.
     * <p>
     * For CSV representation:
     * Matches the input against the pattern for loading an existing recurring task from CSV.
     * If a match is found, sets the description, recurring type, and next refresh date.
     * <p>
     * The expected formats are:
     * - For user input: {@code "recur <desc> /every day|week|month"}
     * - For CSV: {@code "recur <desc> /every day|week|month <nextRefresh>"}
     *
     * @param input The user input or CSV representation of a recurring task.
     * @throws UkeCatException If the input format is incorrect.
     */
    public static void parseRecurTask(String input) throws UkeCatException {
        // matching from user input
        Pattern patternInit = Pattern.compile("^recur\\s+(.+)\\s+/every\\s+(day|week|month)");
        Matcher matcherInit = patternInit.matcher(input);
        if (matcherInit.matches()) {
            LocalDate added = LocalDate.now().plusDays(2);
            Storage.setNextRefresh(added);
            input += (" " + added);
        }

        // matching from csv
        Pattern pattern = Pattern.compile("^recur\\s+(.+)\\s+/every\\s+(day|week|month)\\s+(.+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            Storage.setDesc(matcher.group(1));
            Storage.setRecurType(matcher.group(2));
            Storage.setNextRefresh(LocalDate.parse(matcher.group(3)));
        } else {
            throw new UkeCatException("Wrong format, use: recur <desc> /every day|week|month");
        }
    }

    /**
     * Extracts the index of task to be deleted from input words array.
     *
     * @param words An array of words representing the user input.
     * @return The task number (index) to be deleted.
     * @throws UkeCatException If the input does not match the expected format
     *         or if the task number is not a valid integer.
     */
    public static int parseDeleteTask(String[] words) throws UkeCatException {
        try {
            if (words.length == 2) {
                return Integer.parseInt(words[1]) - 1;
            } else {
                throw new UkeCatException("Wrong format, use: delete <task#>");
            }
        } catch (NumberFormatException e) {
            throw new UkeCatException("Wrong format, use: delete <task#>");
        }
    }

    /**
     * Extracts the index of task to be marked from input words array.
     *
     * @param words An array of words representing the user input.
     * @return The task number (index) to be deleted.
     * @throws UkeCatException If the input does not match the expected format or if the task number is
     *         not a valid integer.
     */
    public static int parseMarkTask(String[] words) throws UkeCatException {
        try {
            if (words.length == 2) {
                return Integer.parseInt(words[1]) - 1;
            } else {
                throw new UkeCatException("Wrong format, use: mark / unmark <task#>");
            }
        } catch (NumberFormatException e) {
            throw new UkeCatException("Wrong format, use: mark / unmark <task#>");
        }
    }

    /**
     * Parses the user input for finding tasks and extracts the keyword.
     *
     * @param words The array of words representing the user input.
     * @return The keyword used for finding tasks.
     * @throws UkeCatException If the input format is incorrect.
     */
    public static String parseFindTask(String[] words) throws UkeCatException {
        if (words.length == 2) {
            return words[1];
        } else {
            throw new UkeCatException("Wrong format, use: find <keyword>");
        }
    }

    /**
     * Converts a Task object into a CSV (Comma-Separated Values) format string.
     * The CSV format is as follows:
     * - ToDo: T, 0/1, desc
     * - Deadline: D, 0/1, desc, by
     * - Event: E, 0/1, desc, from, to
     * - RecurTask: R, 0/1, desc, recurType, nextRefresh
     *
     * @param t The Task object to be converted to CSV.
     * @return A CSV format string representing the given Task.
     */
    public static String parseTaskToCsv(Task t) {
        if (t instanceof ToDo) {
            return String.format("T,%s,%s", t.getStatus(), t.getDescription());
        } else if (t instanceof Deadline) {
            Deadline x = (Deadline) t;
            return String.format("D,%s,%s,%s", t.getStatus(), t.getDescription(), x.getBy());
        } else if (t instanceof Event) {
            Event x = (Event) t;
            return String.format("E,%s,%s,%s,%s", t.getStatus(), t.getDescription(),
                    x.getStart(), x.getEnd());
        } else {
            RecurTask x = (RecurTask) t;
            return String.format("R,%s,%s,%s,%s", t.getStatus(), t.getDescription(),
                    x.getRecurType(), x.getNextRefresh());
        }
    }

    /**
     * Parses a CSV (Comma-Separated Values) format string and converts it into a format
     * compatible with the UkeCat application. The CSV format is expected to represent tasks
     * in the following way:
     * - ToDo: T, 0/1, desc
     * - Deadline: D, 0/1, desc, by
     * - Event: E, 0/1, desc, from, to
     * - RecurTask: R, 0/1, desc, recurType, nextRefresh
     * The method extracts information from the CSV string, modifies the content in the Storage
     * class, and then calls the respective parsing methods for ToDo, Deadline, Event, or RecurTask tasks.
     *
     * @param csv The CSV format string to be parsed.
     */
    public static void parseCsv(String csv) {
        String[] words = csv.split(",");
        Storage.setWords(words);
        try {
            switch (words[0]) {
            case "T":
                Storage.getWords()[0] = "todo";
                Storage.setInput(String.format("todo %s", words[2]));
                parseToDo(Storage.getInput());
                break;
            case "D":
                Storage.getWords()[0] = "deadline";
                Storage.setInput(String.format("deadline %s /by %s", words[2], words[3]));
                parseDeadline(Storage.getInput());
                break;
            case "E":
                Storage.getWords()[0] = "event";
                Storage.setInput(String.format("event %s /from %s /to %s", words[2], words[3], words[4]));
                parseEvent(Storage.getInput());
                break;
            case "R":
                Storage.getWords()[0] = "recur";
                Storage.setInput(String.format("recur %s /every %s %s", words[2], words[3], words[4]));
                parseRecurTask(Storage.getInput());
                break;
            default:
                System.out.println("Can't read csv, file corrupted, abort!");
                System.exit(-1);
            }
        } catch (UkeCatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Closes the Scanner used for reading user input.
     */
    public static void closeScanner() {
        sc.close();
    }
}
