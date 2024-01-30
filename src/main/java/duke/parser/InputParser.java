package duke.parser;

import duke.Actions;
import duke.TextTemplate;
import duke.TaskList;
import duke.exceptions.InvalidDateFormException;
import duke.exceptions.InvalidInputException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TodoTask;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
public class InputParser {
    private boolean isActive;

    public InputParser() {
        this.isActive = true;
    }

    public boolean isActive() {
        return this.isActive;
    }

    private void exit() {
        this.isActive = false;
    }

    private String listTasks(TaskList tasks) {
        return tasks.listTasks();
    }

    private String markTask(String s, TaskList tasks) throws InvalidInputException {
        String[] parts = s.split("\\s+");
        int taskNum = Integer.parseInt(parts[1]) - 1;
        if (taskNum >= tasks.size()) {
            throw new InvalidInputException(TextTemplate.TASK_DOES_NOT_EXIST);
        }
        Task t = tasks.markTask(taskNum);
        return TextTemplate.MARK_TASK + "\n" + t.toString() + "\n" + TextTemplate.LINE_BREAK;
    }

    private String unmarkTask(String s, TaskList tasks) throws InvalidInputException {
        String[] parts = s.split("\\s+", 2);
        int taskNum = Integer.parseInt(parts[1]) - 1;
        if (taskNum >= tasks.size()) {
            throw new InvalidInputException(TextTemplate.TASK_DOES_NOT_EXIST);
        }
        Task t = tasks.unmarkTask(taskNum);
        return TextTemplate.UNMARK_TASK + "\n" + t.toString() + "\n" + TextTemplate.LINE_BREAK;
    }

    private String deleteTask(String s, TaskList tasks) throws InvalidInputException {
        String[] parts = s.split("\\s+", 2);
        int taskNum = Integer.parseInt(parts[1]) - 1;
        if (taskNum >= tasks.size()) {
            throw new InvalidInputException(TextTemplate.TASK_DOES_NOT_EXIST);
        }
        Task t = tasks.remove(taskNum);
        return TextTemplate.DELETE_TASK + "\n" + t.toString() + "\n" + TextTemplate.LINE_BREAK;
    }

    private String addTodo(String s, TaskList tasks) {
        String[] parts = s.split(" ", 2);
        TodoTask t = new TodoTask(parts[1]);
        tasks.add(t);

        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, tasks.size());
        return TextTemplate.ADD_TASK + "\n" + t.toString() + "\n" + taskCounterMsg + "\n" + TextTemplate.LINE_BREAK;
    }

    private String addEvent(String s, TaskList tasks) {
        String[] parts = s.split(" /from ", 2);
        String desc = parts[0].split(" ", 2)[1];
        String[] duration = parts[1].split(" /to ", 2);
        LocalDateTime start;
        LocalDateTime end;

        try {
            start = parseDateTime(duration[0]);
            end = parseDateTime(duration[1]);
        } catch (InvalidDateFormException e) {
            return e.getMessage() + "\n" + TextTemplate.LINE_BREAK;
        }

        EventTask e = new EventTask(desc, start, end);
        tasks.add(e);

        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, tasks.size());
        return TextTemplate.ADD_TASK + "\n" + e.toString() + "\n" + taskCounterMsg + "\n" + TextTemplate.LINE_BREAK;
    }

    private String addDeadline(String s, TaskList tasks) {
        String[] parts = s.split(" /by ", 2);
        String[] firstPart = parts[0].split(" ", 2);
        String desc = firstPart[1];
        String end = parts[1];
        LocalDateTime deadline;

        try {
            deadline = parseDateTime(end);
        } catch (InvalidDateFormException e) {
            return e.getMessage() + "\n" + TextTemplate.LINE_BREAK;
        }

        DeadlineTask d = new DeadlineTask(desc, deadline);
        tasks.add(d);

        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, tasks.size());
        return TextTemplate.ADD_TASK + "\n" + d.toString() + "\n" + taskCounterMsg + "\n" + TextTemplate.LINE_BREAK;
    }

    private static LocalDateTime parseDateTime(String s) throws InvalidDateFormException {
        // Parse the end string into LocalDateTime
        LocalDateTime dateTime;
        try {
            if (s.length() == 10) {
                // If the end string is in the format yyyy-mm-dd
                dateTime = LocalDateTime.parse(s + "T00:00:00");
            } else {
                // If the end string is in the format yyyy-mm-dd HHmm
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                dateTime = LocalDateTime.parse(s, formatter);
            }
            return dateTime;
        } catch (Exception e) {
            throw new InvalidDateFormException();
        }
    }


    public Actions getAction(String input) throws InvalidInputException {
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
        if (Pattern.matches("deadline .+ /by .+", input)) {
            return Actions.DEADLINE;
        }
        if (Pattern.matches( "event .+ /from .+ /to .+", input)) {
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
        return Actions.INVALID;
    }

    public String processCommand(String input, TaskList tasks) throws InvalidInputException {
        Actions act = this.getAction(input);
        switch (act) {
        case BYE:
            this.exit();
            return TextTemplate.EXIT + "\n" + TextTemplate.LINE_BREAK;
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
        case EVENT:
            return this.addEvent(input, tasks);
        case DEADLINE:
            return this.addDeadline(input, tasks);
        case INVALID:
            throw new InvalidInputException();
        }
        return null;
    }
}
