package seedu.bobby;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * <h1> Parser </h1>
 * This Parser class parses the user inputs of the Bobby program and returns
 * the required information. The outputs are then passed as arguments to the other functions
 * from the other classes that produce the functionality of the program,
 * like marking the correct task number in the task list.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class Parser {

    /**
     * Returns an integer of the task to be deleted, marked or unmarked. It is used as an
     * argument for the TaskList class.
     *
     * @param input the user input from system (command)
     * @return Integer parsed from the user input
     * @throws BobbyException
     */
    public static int parseNum(String input) throws BobbyException {
        assert !input.isEmpty() : "unable to parse empty input";
        String[] items = input.split(" ");
        if (items.length == 1) {
            throw new BobbyException("Oops! Please state the task number.");
        } else {
            return Integer.parseInt(items[1].trim());
        }
    }

    /**
     * Returns a string of the description of the todo task that can then be used
     * to initialise a Todo task and add it to the TaskList.
     *
     * @param input the user input from system (command)
     * @return String parsed from the user input
     * @throws BobbyException
     */
    public static String parseTodo(String input) throws BobbyException {
        assert !input.isEmpty() : "unable to parse empty input";
        String[] items = input.split(" ", 2);
        if (items.length == 1) {
            UI.emptyDesc("todo");
        }
        return items[1].trim();
    }

    /**
     * Returns a string of the task description, which is then used
     * to initialise a Deadlines task and add it to the TaskList
     *
     * @param input the user input from system (command)
     * @return String parsed from the user input, extracting only the description.
     * @throws BobbyException
     */
    public static String parseDeadlineTask(String input) throws BobbyException {
        assert !input.isEmpty() : "unable to parse empty input";
        String task = "";
        String[] items = input.split(" ", 2);
        if (items.length == 1) {
            UI.emptyDesc("deadline");
        }
        String[] parts = input.split("/by ");
        task = parts[0].replaceFirst("deadline ", "").trim();
        return task;
    }

    /**
     * Returns LocalDateTime to be used as argument for creating Deadline object.
     * Called when creating a deadline task.
     *
     * @param input the user input from system (command)
     * @return String array which contains elements parsed from the user input, extracting
     * only the required information, the description and deadline.
     * @throws BobbyException
     */
    public static LocalDateTime parseDeadlineCommand(String input) throws BobbyException {
        assert !input.isEmpty() : "unable to parse empty input";
        String[] items = input.split(" ", 2);
        if (items.length == 1) {
            UI.emptyDesc("deadline");
        }
        try {
            String[] parts = input.split("/by ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            LocalDateTime deadline = LocalDateTime.parse(parts[1], formatter);
            return deadline;
        } catch (DateTimeParseException e) {
            throw new BobbyException("Oops, please state your deadline in the format: dd-MM-yyyy HHmm");
        }
    }

    /**
     * Returns LocalDateTime after parsing the input string. Called when updating deadline information.
     *
     * @param input user input from system
     * @return LocalDateTime parsed from String
     * @throws BobbyException
     */
    public static LocalDateTime parseDeadline(String input) throws BobbyException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            LocalDateTime deadline = LocalDateTime.parse(input.trim(), formatter);
            return deadline;
        } catch (DateTimeParseException e) {
            throw new BobbyException("Oops, please state your deadline in the format: dd-MM-yyyy HHmm");
        }
    }

    /**
     * Returns a string array that contains the description of the event,
     * start and end timing. Used to initialise a Events Task and add it to the TaskList.
     *
     * @param input the user input from system (command)
     * @return String array which contains elements parsed from user input. First element is the description of the event,
     * second element is the start timing and third element is the end timing of the event.
     * @throws BobbyException
     */
    public static String[] parseEvent(String input) throws BobbyException {
        assert !input.isEmpty() : "unable to parse empty input";
        String[] items = input.split(" ", 2);
        try {
            if (items.length == 1) {
                UI.emptyDesc("event");
            }
            String[] helper = input.split("/from ");
            String[] parts = new String[3];

            parts[0] = helper[0].replaceFirst("event ", ""); //to store event description
            parts[1] = helper[1].split("/to ")[0]; //to store event start timing
            parts[2] = helper[1].split("/to ")[1]; //to store event end timing
            return parts;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BobbyException("Please state the details like this: event event_name /from timing /to timing.");
        }

    }

    /**
     * Returns a string of the description or keyword of the task to be found in task list.
     *
     * @param input the user input from system (command)
     * @return String parsed from the user input
     * @throws BobbyException
     */
    public static String parseFind(String input) throws BobbyException {
        assert !input.isEmpty() : "unable to parse empty input";
        String[] items = input.split(" ", 2);
        if (items.length == 1) {
            throw new BobbyException("Oops, please state the description of the task you want to find.");
        }
        return items[1];
    }

    /**
     * Returns an array of Strings where the first element is the task attribute and the
     * second element is the new information to be updated. These information are then
     * used to call TaskList's update method
     * @param input user input from the system
     * @return String array of information parsed from the user input
     * @throws BobbyException
     */
    public static String[] parseUpdate(String input) throws BobbyException {
        assert !input.isEmpty() : "unable to parse empty input";
        String[] items = input.split("/", 2);
        if (items.length == 1) {
            throw new BobbyException("Oops, please state the attribute you want to update " +
                    "and the information to update.\n" + "Run your command like this:\n" +
                    "update <taskNum> /<attribute> <newInfo>");
        }
        String[] parts = items[1].split(" ", 2);
        return parts;
    }

}
