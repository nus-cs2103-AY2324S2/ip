package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import duke.Actions;
import duke.TaskList;
import duke.TextTemplate;
import duke.exceptions.InvalidDateFormException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidMarkException;
import duke.tasks.DeadlineTask;
import duke.tasks.DoAfterTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TodoTask;


/**
 * The InputParser class handles the parsing of user input and performs corresponding actions.
 */
public class InputParser {
    private boolean isActive;

    /**
     * Constructs an InputParser object.
     * Sets the initial state of the parser to active.
     */
    public InputParser() {
        this.isActive = true;
    }

    /**
     * Checks if the parser is active.
     *
     * @return true if the parser is active, false otherwise
     */
    public boolean isActive() {
        return this.isActive;
    }

    private void exit() {
        this.isActive = false;
    }

    private String listTasks(TaskList tasks) {
        return tasks.listTasks();
    }

    private String markTask(String input, TaskList tasks) throws InvalidInputException, InvalidMarkException {
        String[] parts = input.split("\\s+");
        int taskNum = Integer.parseInt(parts[1]) - 1;
        assert taskNum >= 0 : "Task number should at least 1.";

        if (taskNum >= tasks.size()) {
            throw new InvalidInputException(TextTemplate.TASK_DOES_NOT_EXIST);
        }
        Task t = tasks.markTask(taskNum);
        return TextTemplate.MARK_TASK + "\n" + t.toString();
    }

    private String unmarkTask(String input, TaskList tasks) throws InvalidInputException {
        String[] parts = input.split("\\s+", 2);
        int taskNum = Integer.parseInt(parts[1]) - 1;
        assert taskNum >= 0 : "Task number should at least 1.";

        if (taskNum >= tasks.size()) {
            throw new InvalidInputException(TextTemplate.TASK_DOES_NOT_EXIST);
        }

        Task t = tasks.unmarkTask(taskNum);
        return TextTemplate.UNMARK_TASK + "\n" + t.toString();
    }

    private String deleteTask(String input, TaskList tasks) throws InvalidInputException {
        String[] parts = input.split("\\s+", 2);
        int taskNum = Integer.parseInt(parts[1]) - 1;
        assert taskNum >= 0 : "Task number should at least 1.";

        if (taskNum >= tasks.size()) {
            throw new InvalidInputException(TextTemplate.TASK_DOES_NOT_EXIST);
        }

        Task t = tasks.remove(taskNum);
        return TextTemplate.DELETE_TASK + "\n" + t.toString();
    }

    private String addTodo(String input, TaskList tasks) {
        // 1 split to split "todo task 1" into: todo, task1.
        String[] parts = input.split(" ", 2);
        TodoTask todo = new TodoTask(parts[1]);
        tasks.add(todo);

        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, tasks.size());
        return TextTemplate.ADD_TASK + "\n" + todo.toString() + "\n" + taskCounterMsg;
    }

    private String addEvent(String input, TaskList tasks) throws InvalidDateFormException {
        // 3 splits are necessary to split "event task 1 /from 2024-01-01 1600 /to 2024-01-02 1600" into:
        // event, task 1, 2024-01-01 1600, 2024-01-02 1600.
        String[] parts = input.split(" /from ", 2);
        String desc = parts[0].split(" ", 2)[1];
        String[] duration = parts[1].split(" /to ", 2);
        assert duration.length == 2 : "Event tasks should specify from and start time.";

        LocalDateTime start = parseDateTime(duration[0]);
        LocalDateTime end = parseDateTime(duration[1]);

        EventTask event = new EventTask(desc, start, end);
        tasks.add(event);

        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, tasks.size());
        return TextTemplate.ADD_TASK + "\n" + event.toString() + "\n" + taskCounterMsg;
    }

    private String addDoAfter(String s, TaskList tasks) {
        String[] parts = s.split(" /after ", 2);
        String[] firstPart = parts[0].split(" ", 2);
        String desc = firstPart[1];
        int beforeTaskIndex = Integer.valueOf(parts[1]);

        Task beforeTask = null;
        if (beforeTaskIndex > 0 && beforeTaskIndex <= tasks.size()) {
            beforeTask = tasks.get(beforeTaskIndex - 1);
        }

        DoAfterTask doAfter = new DoAfterTask(desc, beforeTask);
        tasks.add(doAfter);

        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, tasks.size());
        return TextTemplate.ADD_TASK + "\n" + doAfter.toString() + "\n" + taskCounterMsg;
    }

    private String addDeadline(String s, TaskList tasks) throws InvalidDateFormException {
        // 2 splits are necessary to split "deadline task 1 /by 2024-01-01 1600" into:
        // deadline, task 1, 2024-01-01 1600.
        String[] parts = s.split(" /by ", 2);
        String[] firstPart = parts[0].split(" ", 2);
        String desc = firstPart[1];
        String end = parts[1];

        LocalDateTime deadlineTime;
        deadlineTime = parseDateTime(end);

        DeadlineTask deadline = new DeadlineTask(desc, deadlineTime);
        tasks.add(deadline);

        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, tasks.size());
        return TextTemplate.ADD_TASK + "\n" + deadline.toString() + "\n" + taskCounterMsg;
    }

    private String matchKeyword(String input, TaskList tasks) {
        String[] parts = input.split(" ", 2);
        String keyword = parts[1];

        return tasks.findToString(keyword);
    }

    private static LocalDateTime parseDateTime(String input) throws InvalidDateFormException {
        LocalDateTime dateTime;
        try {
            if (input.length() == 10) {
                dateTime = LocalDateTime.parse(input + "T00:00:00");
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                dateTime = LocalDateTime.parse(input, formatter);
            }
            return dateTime;
        } catch (Exception e) {
            throw new InvalidDateFormException();
        }
    }
    private Actions getAction(String input) throws InvalidInputException {
        if (input.equals("bye")) {
            return Actions.BYE;
        }
        if (input.equals("list")) {
            return Actions.LIST;
        }
        if (input.equals("todo")) {
            throw new InvalidInputException(TextTemplate.TODO_NO_DESC);
        }
        if (Pattern.matches("todo .+", input)) {
            return Actions.TODO;
        }
        if (Pattern.matches("doafter .+ /after \\d+", input)) {
            return Actions.DOAFTER;
        }
        if (Pattern.matches("deadline .+ /by .+", input)) {
            return Actions.DEADLINE;
        }
        if (Pattern.matches("event .+ /from .+ /to .+", input)) {
            return Actions.EVENT;
        }
        if (Pattern.matches("mark \\d+", input)) {
            return Actions.MARK;
        }
        if (Pattern.matches("unmark \\d+", input)) {
            return Actions.UNMARK;
        }
        if (Pattern.matches("delete \\d+", input)) {
            return Actions.DELETE;
        }
        if (Pattern.matches("find .+", input)) {
            return Actions.MATCH;
        }
        return Actions.INVALID;
    }

    /**
     * Processes the user input and performs the corresponding action.
     * Actions: BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, INVALID
     *
     * @param input the user input to process
     * @param tasks the task list to operate on
     * @return the response message after processing the input
     * @throws InvalidInputException if the input is invalid or does not match any known action
     */
    public String processCommand(String input, TaskList tasks) throws InvalidInputException,
            InvalidMarkException, InvalidDateFormException {
        Actions act = this.getAction(input);
        switch (act) {
        case BYE:
            this.exit();
            return "exit";
        case LIST:
            return this.listTasks(tasks);
        case MARK:
            return this.markTask(input, tasks);
        case UNMARK:
            return this.unmarkTask(input, tasks);
        case DELETE:
            return this.deleteTask(input, tasks);
        case TODO:
            return this.addTodo(input, tasks);
        case DOAFTER:
            return this.addDoAfter(input, tasks);
        case EVENT:
            return this.addEvent(input, tasks);
        case DEADLINE:
            return this.addDeadline(input, tasks);
        case MATCH:
            return this.matchKeyword(input, tasks);
        default:
            throw new InvalidInputException();
        }
    }
}
