package dwight.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import dwight.task.DeadlineTask;
import dwight.task.EventTask;
import dwight.task.TodoTask;
import dwight.exceptions.DukeException;


public class Parser {

    Parser() {
    }

    /**
     * Parses one word inputs such as "list"
     *
     * @param input The input line inputted by the user
     * @return returns the inputted command
     */
    public static String parse(String input) {
        return input.split(" ")[0];
    }

    /**
     * Parses todo commands
     *
     * @param input The input line inputted by the user
     * @return TodoTask instance
     * throws DukeException if input not correct TodoTask format
     */
    public static TodoTask parseTodo(String input) throws DukeException {

        String taskName;
        TodoTask task;

        taskName = String.join(" ", Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length));
        if (!taskName.isBlank()) {
            System.out.println(taskName);
            // calling the method
            task = new TodoTask(taskName, input);
            return task;
        } else {
            throw new DukeException("\tTodo description cannot be empty!\n\tEx: todo return book\n");
        }
    }

    /**
     * Parses deadline commands
     *
     * @param input The input line inputted by the user
     * @return DeadlineTask instance
     * throws DukeException if input not correct DeadLine format
     */
    public static DeadlineTask parseDeadline(String input) throws DukeException {
        String taskName, start, end, firstString, secondString;
        firstString = input.split(" /")[0];
        secondString = input.split(" /")[1];
        taskName = String.join(" ", Arrays.copyOfRange(firstString.split(" "), 1, firstString.split(" ").length));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        end = String.join(" ", Arrays.copyOfRange(secondString.split("by "), 1, secondString.split("by ").length));
        if (!taskName.isBlank() && !firstString.isBlank() && !secondString.isBlank()) {
            LocalDateTime end_time = LocalDateTime.parse(end, formatter);
            return new DeadlineTask(taskName, end_time, input);
        } else {
            throw new DukeException("\tInvalid deadline command");
        }


    }

    /**
     * Parses event commands
     *
     * @param input The input line inputted by the user
     * @return EventTask instance
     * throws DukeException if input not correct EventTask format
     */
    public static EventTask parseEvent(String input) throws DukeException {
        if (input.split(" /").length == 3) {
            String firstString, secondString, thirdString, taskName, start, end;
            firstString = input.split(" /")[0];
            secondString = input.split(" /")[1];
            thirdString = input.split(" /")[2];
            taskName = String.join(" ", Arrays.copyOfRange(firstString.split(" "), 1, firstString.split(" ").length));
            start = String.join(" ", Arrays.copyOfRange(secondString.split(" "), 1, secondString.split(" ").length));
            end = String.join(" ", Arrays.copyOfRange(thirdString.split(" "), 1, thirdString.split(" ").length));
            return new EventTask(taskName, start, end, input);
        } else {
            throw new DukeException("\tInvalid event command");
        }
    }

    /**
     * Parses delete commands
     *
     * @param input The input line inputted by the user
     * @return Index of item to be deleted
     * throws DukeException if input not correct delete command format
     */
    public static int parseDelete(String input) throws DukeException {
        if (input.split(" ").length == 2) {
            return Integer.parseInt(input.split(" ")[1]);
        } else {
            throw new DukeException("\tInvalid delete command");
        }
    }

    /**
     * Parses mark commands
     *
     * @param input The input line inputted by the user
     * @return Index of item to be marked
     * throws DukeException if input not correct mark command format
     */
    public static int parseMark(String input) throws DukeException {
        if (input.split(" ").length == 2) {
            return Integer.parseInt(input.split(" ")[1]);
        } else {
            throw new DukeException("\tInvalid mark command");
        }
    }

    /**
     * Parses unmark commands
     *
     * @param input The input line inputted by the user
     * @return Index of item to be unmarked
     * throws DukeException if input not correct unmark command format
     */
    public static int parseUnmark(String input) throws DukeException {
        if (input.split(" ").length == 2) {
            return Integer.parseInt(input.split(" ")[1]);
        } else {
            throw new DukeException("\tInvalid unmark command");
        }
    }

    /**
     * Parses keyword for find command
     *
     * @param input User inputted find command
     * @return Keyword that user wants to find
     * throws DukeException if input not correct find command format
     */
    public static String parseFind(String input) throws DukeException {
        if (input.split(" ").length > 1) {
            return String.join(" ", Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length));
        } else {
            throw new DukeException("\tFind keyword cannot be empty");
        }
    }

}
