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

    public static String parse(String input) {
        return input.split(" ")[0];
    }

    public static TodoTask parse_todo(String input) throws DukeException {

        String task_name;
        TodoTask task;

        task_name = String.join(" ", Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length));
        if (!task_name.isBlank()) {
            // calling the method
            task = new TodoTask(task_name, input);
            return task;
        } else {
            throw new DukeException("\tInvalid todo command");
        }
    }

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

    public static int parse_delete(String input) throws DukeException {
        if (input.split(" ").length == 2) {
            return Integer.parseInt(input.split(" ")[1]);
        } else {
            throw new DukeException("\tInvalid delete command");
        }
    }

    public static int parse_mark(String input) throws DukeException {
        if (input.split(" ").length == 2) {
            return Integer.parseInt(input.split(" ")[1]);
        } else {
            throw new DukeException("\tInvalid mark command");
        }
    }

    public static int parse_unmark(String input) throws DukeException {
        if (input.split(" ").length == 2) {
            return Integer.parseInt(input.split(" ")[1]);
        } else {
            throw new DukeException("\tInvalid unmark command");
        }
    }

}
