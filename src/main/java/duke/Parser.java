package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Parses user commands and manages the execution of corresponding tasks.
 */
public class Parser {
    /**
     * Represents the possible requests that can be parsed from user commands.
     */
    public enum Request {
        BYE, LIST, MARK, TODO, DEADLINE, EVENT, DELETE, FIND, INVALID
    }

    /**
     * Parses the user command and executes the corresponding action based on the request.
     *
     * @param myList    The list of tasks to be modified.
     * @param userInput The user's input command.
     * @return A string message indicating the result of the command execution.
     */
    public String parseCommand(MyList myList, String userInput) throws DukeException, NumberFormatException,
            ArrayIndexOutOfBoundsException {
        Request request = getRequest(userInput);
        int index;
        String s;
        String taskString;
        String dateTimePattern;
        String[] s1;
        String[] s2;
        Task task;

        switch (request) {
        case BYE:
            return "Bye. Hope to see you again soon!";
        case LIST:
            return myList.getTasks();
        case MARK:
            index = Integer.parseInt(userInput.substring("mark".length()).trim());
            return myList.markTask(index);
        case TODO:
            s = userInput.substring("todo".length()).trim();

            if (s.isEmpty()) {
                throw new DukeException("duke.Task description cannot be empty.");
            }
            task = new Todo(s);
            return myList.addTask(task);
        case DEADLINE:
            s = userInput.substring("deadline".length()).trim();
            s1 = s.split("/by");
            if (s1.length > 2) {
                throw new DukeException("Please enter format deadline (task) /by (yyyy-mm-dd hhmm)");
            } else if (s1[1].trim().isEmpty()) {
                throw new DukeException("Empty timing. Please enter format deadline (task) /by (yyyy-mm-dd hhmm)");
            }
            taskString = s1[0].trim();
            String byString = s1[1].trim();
            dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

            if (Pattern.matches(dateTimePattern, byString)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime dateTimeBy = LocalDateTime.parse(byString, formatter);
                //Task task = new Deadline(taskString, dateTimeBy);
                task = new Deadline(taskString, dateTimeBy);
                return myList.addTask(task);
            } else {
                throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
            }
        case EVENT:
            s = userInput.substring("event".length()).trim();
            s1 = s.split("/from");

            if (s1.length > 2) {
                throw new DukeException("Please enter format event (task) /from (yyyy-mm-dd hhmm) "
                        + "/to (yyyy-mm-dd hhmm)");
            } else if (s1[1].trim().isEmpty()) {
                throw new DukeException("Empty timing. Please enter format event (task) /from "
                        + "(yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
            }
            s2 = s1[1].split("/to");

            if (s2.length > 2) {
                throw new DukeException("Please enter format event (task) /from (yyyy-mm-dd hhmm)"
                        + " /to (yyyy-mm-dd hhmm)");
            } else if (s2[1].trim().isEmpty()) {
                throw new DukeException("Empty timing. Please enter format event (task) /from "
                        + "(yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
            }
            taskString = s1[0].trim();
            String fromString = s2[0].trim();
            String toString = s2[1].trim();
            dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

            if (Pattern.matches(dateTimePattern, fromString) && Pattern.matches(dateTimePattern, toString)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime dateTimeFrom = LocalDateTime.parse(fromString, formatter);
                LocalDateTime dateTimeTo = LocalDateTime.parse(toString, formatter);
                task = new Event(taskString, dateTimeFrom, dateTimeTo);
                return myList.addTask(task);
            } else {
                throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
            }
        case DELETE:
            index = Integer.parseInt(userInput.substring("delete".length()).trim());
            return myList.delete(index);
        case FIND:
            String keyword = userInput.substring("find".length()).trim();
            return myList.findByKeyword(keyword);
        case INVALID:
            return "OOPS! That was an invalid input";
        }
        return "";
    }

    /**
     * Determines the request type based on the user's input command.
     *
     * @param userInput The user's input command.
     * @return The Request enum corresponding to the parsed command.
     */
    public Request getRequest(String userInput) {
        String inputUpper = userInput.toUpperCase();

        for (Request request : Request.values()) {
            if (inputUpper.startsWith(request.name())) {
                return request;
            }
        }

        return Request.INVALID;
    }
}
