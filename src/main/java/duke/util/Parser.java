package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.TodoTask;
import duke.exceptions.DukeException;


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
    public static TodoTask parse_todo(String input) throws DukeException {

        String task_name;
        TodoTask task;

        task_name = String.join(" ", Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length));
        if (!task_name.isBlank()) {
            System.out.println(task_name);
            // calling the method
            task = new TodoTask(task_name, input);
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
    public static DeadlineTask parse_deadline(String input) throws DukeException {
        String task_name, start, end, first_string, second_string;
        first_string = input.split(" /")[0];
        second_string = input.split(" /")[1];
        task_name = String.join(" ", Arrays.copyOfRange(first_string.split(" "), 1, first_string.split(" ").length));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        end = String.join(" ", Arrays.copyOfRange(second_string.split("by "), 1, second_string.split("by ").length));
        if (!task_name.isBlank() && !first_string.isBlank() && !second_string.isBlank()) {
            LocalDateTime end_time = LocalDateTime.parse(end, formatter);
            return new DeadlineTask(task_name, end_time, input);
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
    public static EventTask parse_event(String input) throws DukeException {
        if (input.split(" /").length == 3) {
            String first_string, second_string, third_string, task_name, start, end;
            first_string = input.split(" /")[0];
            second_string = input.split(" /")[1];
            third_string = input.split(" /")[2];
            task_name = String.join(" ", Arrays.copyOfRange(first_string.split(" "), 1, first_string.split(" ").length));
            start = String.join(" ", Arrays.copyOfRange(second_string.split(" "), 1, second_string.split(" ").length));
            end = String.join(" ", Arrays.copyOfRange(third_string.split(" "), 1, third_string.split(" ").length));
            return new EventTask(task_name, start, end, input);
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
    public static int parse_delete(String input) throws DukeException {
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
    public static int parse_mark(String input) throws DukeException {
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
    public static int parse_unmark(String input) throws DukeException {
        if (input.split(" ").length == 2) {
            return Integer.parseInt(input.split(" ")[1]);
        } else {
            throw new DukeException("\tInvalid unmark command");
        }
    }

    public static String parse_find(String input) throws DukeException {
        if (input.split(" ").length > 1) {
            return String.join(" ", Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length));
        } else {
            throw new DukeException("\tFind keyword cannot be empty");
        }
    }

}
