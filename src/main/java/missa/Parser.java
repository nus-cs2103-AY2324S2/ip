package missa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import missa.command.AddCommand;
import missa.command.ByeCommand;
import missa.command.Command;
import missa.command.DeleteCommand;
import missa.command.FindCommand;
import missa.command.ListCommand;
import missa.command.MarkCommand;
import missa.command.UnmarkCommand;
import missa.exception.IncorrectTaskTypeException;
import missa.exception.NoContentException;
import missa.exception.NoSuchTaskException;
import missa.exception.NoTimingException;
import missa.task.Deadline;
import missa.task.DoAfter;
import missa.task.Event;
import missa.task.Task;
import missa.task.ToDo;

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
        if (input.equals("bye")) { // Bye command.
            return new ByeCommand(tasks);
        }
        if (input.equals("list")) { // List missa.command.
            return new ListCommand(tasks);
        }
        if (input.startsWith("mark")) { // Mark command.
            return getCommand(input, tasks, "mark");
        }
        if (input.startsWith("unmark")) { // Unmark command.
            return getCommand(input, tasks, "unmark");
        }
        if (input.startsWith("find")) { // Find command.
            return getFindCommand(input, tasks);
        }
        if (input.startsWith("delete")) { // Delete command.
            return getCommand(input, tasks, "delete");
        }
        // Add command.
        String[] task = input.split(" ", 2);
        String taskType = task[0];

        if (taskType.equals("todo")) { // Checks if the task type is todo.
            return getAddTodoCommand(tasks, task);
        } else if (taskType.equals("deadline")) { // Checks if the task type is deadline.
            return getAddDdlCommand(tasks, task);
        } else if (taskType.equals("event")) { // Checks if the task type is event.
            return getAddEventCommand(tasks, task);
        } else if (input.contains("/after")) { // Checks if the task type is do-after.
            return getAddDoAfterCommand(input, tasks);
        } else {
            throw new IncorrectTaskTypeException();
        }
    }

    /**
     * Returns add command that adds a do-after task.
     *
     * @param input User input containing task content and condition.
     * @param tasks Task list to store tasks.
     * @return Add command to add tasks.
     * @throws NoContentException Alerts users there is missing task info.
     */
    private static AddCommand getAddDoAfterCommand(String input, TaskList tasks)
            throws NoContentException {
        String[] taskInfo = input.split(" /after ");
        if (taskInfo.length < 2) {
            throw new NoContentException();
        }
        String content = taskInfo[0];
        String taskCondition = taskInfo[1];
        Task t1 = new DoAfter(content, taskCondition);
        return new AddCommand(t1, tasks);
    }

    /**
     * Returns add command that adds a new Event task.
     *
     * @param tasks Task list of all tasks.
     * @param task Task to be added later.
     * @return Add command to be executed.
     * @throws NoContentException Alerts users if no content is detected.
     * @throws NoTimingException Alerts users if no duration is detected.
     */
    private AddCommand getAddEventCommand(TaskList tasks, String[] task)
            throws NoContentException, NoTimingException {
        Task nextTask;
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
    }

    /**
     * Returns add command to add a new deadline task.
     *
     * @param tasks Task list containing all tasks.
     * @param task Task to be added.
     * @return Add command to be executed later.
     * @throws NoContentException Alerts users if no content is given.
     * @throws NoTimingException Alerts users if no deadline timing is given.
     */
    private AddCommand getAddDdlCommand(TaskList tasks, String[] task)
            throws NoContentException, NoTimingException {
        Task nextTask;
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
    }

    /**
     * Returns add command that adds a ToDo task.
     *
     * @param tasks Task list containing all tasks.
     * @param task Task to be added.
     * @return Add command for a new todo task.
     * @throws NoContentException Alerts users if no content is given.
     */
    private static AddCommand getAddTodoCommand(TaskList tasks, String[] task)
            throws NoContentException {
        Task nextTask;
        if (task.length < 2) {
            throw new NoContentException();
        }
        String content = task[1];
        nextTask = new ToDo(content);
        return new AddCommand(nextTask, tasks);
    }

    /**
     * Returns find command to execute.
     *
     * @param input User inputs containing keywords.
     * @param tasks Task list containing all tasks.
     * @return Relevant tasks.
     * @throws NoSuchTaskException Alerts users if no keyword is given.
     */
    private static FindCommand getFindCommand(String input, TaskList tasks)
            throws NoSuchTaskException {
        String[] inputs = input.split(" ", 2);
        if (inputs.length < 2) {
            throw new NoSuchTaskException();
        }
        return new FindCommand(inputs[1], tasks);
    }

    /**
     *  Returns mark/unmark/delete commands to execute.
     *
     * @param input User inputs containing index of task.
     * @param tasks Task List that stores all tasks.
     * @return Commands to be executed later.
     * @throws NoSuchTaskException Alerts users if there is missing information.
     */
    private static Command getCommand(String input, TaskList tasks, String type)
            throws NoSuchTaskException {
        String[] inputs = input.split(" ");
        if (inputs.length < 2) {
            throw new NoSuchTaskException();
        }
        int idx = Integer.valueOf(inputs[1]);
        if (idx > tasks.getSize()) {
            throw new NoSuchTaskException();
        }
        if (type.equals("mark")) {
            return new MarkCommand(tasks, idx - 1);
        } else if (type.equals("unmark")) {
            return new UnmarkCommand(tasks, idx - 1);
        } else if (type.equals("delete")) {
            return new DeleteCommand(tasks, idx - 1);
        } else {
            throw new NoSuchTaskException();
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
