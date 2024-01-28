package missa;

import missa.command.*;
import missa.exception.IncorrectTaskTypeException;
import missa.exception.NoContentException;
import missa.exception.NoSuchTaskException;
import missa.exception.NoTimingException;
import missa.task.Deadline;
import missa.task.Event;
import missa.task.Task;
import missa.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * A class that deals with making sense of the user missa.command.
 */
public class Parser {
    /**
     * Analyses user input and translates it to a missa.command.
     *
     * @param input A string representation of user input.
     * @param tasks missa.TaskList stored.
     * @return The respective missa.command.
     */
    public Command parse(String input, TaskList tasks) throws
            NoSuchTaskException, NoContentException,
            NoTimingException, IncorrectTaskTypeException {
        if (input.equals("bye")) { // Bye missa.command.
            return new ByeCommand(tasks);
        }
        if (input.equals("list")) { // List missa.command.
            return new ListCommand(tasks);
        }
        if (input.startsWith("mark")) { // Mark missa.command.
            String[] inputs = input.split(" ");
            if (inputs.length < 2) {
                throw new NoSuchTaskException();
            }
            int idx = Integer.valueOf(inputs[1]);
            if (idx > tasks.getSize()) {
                throw new NoSuchTaskException();
            }
            return new MarkCommand(tasks, idx - 1);
        }
        if (input.startsWith("unmark")) { // Unmark missa.command.
            String[] inputs = input.split(" ");
            if (inputs.length < 2) {
                throw new NoSuchTaskException();
            }
            int idx = Integer.valueOf(inputs[1]);
            if (idx > tasks.getSize()) {
                throw new NoSuchTaskException();
            }
            return new UnmarkCommand(tasks, idx - 1);
        }
        if (input.startsWith("delete")) { // Delete missa.command.
            String[] inputs = input.split(" ");
            if (inputs.length < 2) {
                throw new NoSuchTaskException();
            }
            int idx = Integer.valueOf(inputs[1]);
            if (idx > tasks.getSize()) {
                throw new NoSuchTaskException();
            }
            return new DeleteCommand(tasks, idx - 1);
        } else { // Add missa.command.
            String[] task = input.split(" ", 2);
            String taskType = task[0];
            Task nextTask = null;

            if (taskType.equals("todo")) { // Checks if the task type is todo.
                if (task.length < 2) {
                    throw new NoContentException();
                }
                String content = task[1];
                nextTask = new ToDo(content);
                return new AddCommand(nextTask, tasks);

            } else if (taskType.equals("deadline")) { // Checks if the task type is deadline.
                if (task.length < 2) {
                    throw new NoContentException();
                }
                if (!task[1].contains("/by")) {
                    throw new NoTimingException();
                }
                String[] content = task[1].split(" /by ");

                nextTask = new Deadline(
                        content[0],
                        strToDateTime(content[1]));
                return new AddCommand(nextTask, tasks);

            } else if (taskType.equals("event")) { // Checks if the task type is event.
                if (task.length < 2) {
                    throw new NoContentException();
                }
                if (!task[1].contains("/from") || !task[1].contains("/to")) {
                    throw new NoTimingException();
                }
                String[] content = task[1].split(" /from ");
                String text = content[0];
                String[] interval = content[1].split(" /to ");

                nextTask = new Event(
                        text,
                        strToDateTime(interval[0]),
                        strToDateTime(interval[1]));
                return new AddCommand(nextTask, tasks);

            } else {
                throw new IncorrectTaskTypeException();
            }
        }
    }

    /**
     * Change user input of date and time to java object LocalDateTime.
     *
     * @param userInput String representation of date and time in the form of "yyyy-MM-dd HH".
     * @return Java object LocalDateTime.
     */
    public LocalDateTime strToDateTime(String userInput) throws NoTimingException {
        String[] time = userInput.split(" ");
        int[] timing = Arrays.stream(time[0].split("-"))
                .mapToInt(Integer::valueOf).toArray();

        if (time.length != 2 || timing.length != 3) {
            throw new NoTimingException();
        }

        return LocalDateTime.of(
                        LocalDate.of(timing[0], timing[1], timing[2]),
                        LocalTime.of(Integer.valueOf(time[1]), 0));
    }
}
