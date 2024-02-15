package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * <h1> Parser </h1>
 * This Parser class parses the user inputs of the Duke program and returns
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
     * @throws DukeException
     */
    public static int parseNum(String input) throws DukeException {
        assert !input.isEmpty() : "unable to parse empty input";
        String[] items = input.split(" ");
        if (items.length == 1) {
            throw new DukeException("Oops! Please state the task number.");
        } else {
            return Integer.parseInt(input.split(" ")[1]);
        }
    }

    /**
     * Returns a string of the description of the todo task that can then be used
     * to initialise a Todo task and add it to the TaskList.
     *
     * @param input the user input from system (command)
     * @return String parsed from the user input
     * @throws DukeException
     */
    public static String parseTodo(String input) throws DukeException{
        assert !input.isEmpty() : "unable to parse empty input";
        String[] items = input.split(" ", 2);
        if (items.length == 1) {
            UI.emptyDesc("todo");
        }
        return items[1];
    }

    /**
     * Returns a string of the task description, which is then used
     * to initialise a Deadlines task and add it to the TaskList
     *
     * @param input the user input from system (command)
     * @return String parsed from the user input, extracting only the description.
     * @throws DukeException
     */
    public static String parseDeadlineTask(String input) throws DukeException{
        assert !input.isEmpty() : "unable to parse empty input";
        String task = "";
        String[] items = input.split(" ", 2);
        if (items.length == 1) {
            UI.emptyDesc("deadline");
        }
        String[] parts = input.split("/by ");
        task = parts[0].replaceFirst("deadline ", "");
        return task;
    }

    /**
     * Returns a string array of the description of the deadline and the deadline, which is then used
     * to initialise a Deadlines task and add it to the TaskList
     *
     * @param input the user input from system (command)
     * @return String array which contains elements parsed from the user input, extracting
     * only the required information, the description and deadline.
     * @throws DukeException
     */
    public static LocalDateTime parseDeadline(String input) throws DukeException{
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
            throw new DukeException("Oops, please state your deadline in the format: dd-MM-yyyy HHmm");
        }
    }

    /**
     * Returns a string array that contains the description of the event,
     * start and end timing. Used to initialise a Events Task and add it to the TaskList.
     *
     * @param input the user input from system (command)
     * @return String array which contains elements parsed from user input. First element is the description of the event,
     * second element is the start timing and third element is the end timing of the event.
     * @throws DukeException
     */
    public static String[] parseEvent(String input) throws DukeException{
        assert !input.isEmpty() : "unable to parse empty input";
        String[] items = input.split(" ", 2);
        try {
            if (items.length == 1) {
                UI.emptyDesc("event");
            }
            String[] helper = input.split("/from ");
            String[] parts = new String[3];
            parts[0] = helper[0].replaceFirst("event ", ""); //task
            parts[1] = helper[1].split("/to ")[0]; //from
            parts[2] = helper[1].split("/to ")[1]; //to
            return parts;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please state the details like this: event event_name /from timing /to timing.");
        }

    }

    /**
     * Returns a string of the description of the task to be found in task list.
     *
     * @param input the user input from system (command)
     * @return String parsed from the user input
     * @throws DukeException
     */
    public static String parseFind(String input) throws DukeException{
        assert !input.isEmpty() : "unable to parse empty input";
        String[] items = input.split(" ", 2);
        if (items.length == 1) {
            throw new DukeException("Oops, please state the description of the task you want to find.");
        }
        return items[1];
    }

}
