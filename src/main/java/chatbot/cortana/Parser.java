package chatbot.cortana;

import java.time.LocalDateTime;

import chatbot.task.DeadlineTask;
import chatbot.task.EventTask;
import chatbot.task.TodoTask;

/**
 * Parser class to parse user input
 */
public class Parser {

    /**
     * Parses the command from the user input
     * @param input the user input
     * @return the command
     */
    public static Command parseCommand(String input) {
        if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.startsWith("find")) {
            return Command.FIND;
        } else {
            return Command.INVALID;
        }
    }

    /**
     * Parses the index from the user input
     * @param command the command to be executed
     * @param input the user input
     * @return the index
     */
    public static int parseIndex(Command command, String input) {
        if (command == Command.MARK) {
            return Integer.parseInt(input.substring(5)) - 1;
        } else if (command == Command.UNMARK) {
            return Integer.parseInt(input.substring(7)) - 1;
        } else if (command == Command.DELETE) {
            return Integer.parseInt(input.substring(7)) - 1;
        } else {
            return -1;
        }
    }

    /**
     * Parses the todo task from the user input
     * @param input the user input
     * @return the todo task
     */
    public static TodoTask parseTodoTask(String input) {
        assert input.startsWith("todo") && input.length() > 4;
        return new TodoTask(input.substring(5));
    }

    /**
     * Parses the deadline task from the user input
     * @param input the user input
     * @return the deadline task
     */
    public static DeadlineTask parseDeadlineTask(String input) {
        assert input.startsWith("deadline") && input.length() > 8;
        String[] arr = input.substring(9).split("/by");
        LocalDateTime dateTime = Utils.parseDateTimeString(arr[1].trim());
        return new DeadlineTask(arr[0].trim(), dateTime);
    }

    /**
     * Parses the event task from the user input
     * @param input the user input
     * @return the event task
     */
    public static EventTask parseEventTask(String input) {
        assert input.startsWith("event") && input.length() > 5;
        String[] arr = input.substring(6).split("/from");
        String[] arr2 = arr[1].split("/to");
        LocalDateTime startDateTime = Utils.parseDateTimeString(arr2[0].trim());
        LocalDateTime endDateTime = Utils.parseDateTimeString(arr2[1].trim());
        return new EventTask(arr[0].trim(), startDateTime, endDateTime);
    }

    /**
     * Parses the find string from the user input
     * @param input the user input
     * @return the find string
     */
    public static String parseFindString(String input) {
        assert input.startsWith("find") && input.length() > 4;
        return input.substring(5).trim();
    }

}
