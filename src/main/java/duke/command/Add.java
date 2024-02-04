package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.TaskList;
import duke.exception.CommandNotDefinedException;
import duke.exception.DukeException;
import duke.exception.EmptyTextException;
import duke.exception.TimeFormatException;
import duke.exception.TimeInconsistException;
import duke.exception.WrongUsageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class for adding a task into task list
 */
public class Add implements Command {
    static final int TODO_LENGTH = 5;
    static final int DEADLINE_LENGTH = 9;
    static final int BY_LENGTH = 3;
    static final int EVENT_LENGTH = 6;
    static final int FROM_LENGTH = 5;
    static final int TO_LENGTH = 3;
    static final int DEADLINE_TOKEN_NUM = 2;
    static final int EVENT_TOKEN_NUM = 3;

    private Task task;
    private TaskList tasks;

    /**
     * Constructor
     * add task to task list.
     * Identify which type of task is it.
     * @param text the description of a task
     * @throws DukeException wrong inputs might happens
     */
    public Add(String text, TaskList taskList) throws DukeException {
        if (text.startsWith("todo")) {
            this.task = handleTodo(text);
            taskList.addTask(this.task);
        } else if (text.startsWith("deadline")) {
            this.task = handleDeadline(text);
            taskList.addTask(this.task);
        } else if (text.startsWith("event")) {
            this.task = handleEvent(text) ;
            taskList.addTask(this.task);
        } else {
            throw new CommandNotDefinedException();
        }
        this.tasks = taskList;
    }

    private Todo handleTodo(String text) throws DukeException {
        if (text.length() <= TODO_LENGTH) {
            throw new EmptyTextException("description", "todo");
        }
        String descrip = text.substring(TODO_LENGTH);
        return new Todo(descrip);
    }

    private Deadline handleDeadline(String text) throws DukeException{
        String[] token = text.split("/");
        if (token.length != DEADLINE_TOKEN_NUM) {
            throw new WrongUsageException("deadline xxx /by xxx");
        }
        String by = token[1];
        if (token[0].length() <= DEADLINE_LENGTH) {
            throw new EmptyTextException("description", "deadline");
        }
        String descrip = token[0].substring(DEADLINE_LENGTH);
        if (!by.startsWith("by ")) {
            throw new WrongUsageException("deadline xxx /by xxx");
        }
        if (by.length() <= BY_LENGTH) {
            throw new EmptyTextException("due", "deadline");
        }
        String byText = by.substring(BY_LENGTH).trim();
        if (!checkTimeForm(byText)) {
            throw new TimeFormatException();
        }
        return new Deadline(descrip, LocalDate.parse(byText));
    }

    private Event handleEvent(String text) throws DukeException {
        String[] token = text.split("/");
        if (token.length != EVENT_TOKEN_NUM) {
            throw new WrongUsageException("event xxx /from xxx /to xxx");
        }
        String from = token[1];
        String to = token[2];
        if (token[0].length() <= EVENT_LENGTH) {
            throw new EmptyTextException("description", "event");
        }
        String descrip = token[0].substring(EVENT_LENGTH);
        if (!from.startsWith("from ") || !to.startsWith("to ")) {
            throw new WrongUsageException("event xxx /from yyyy-mm-dd /to yyyy-mm-dd");
        }
        if (from.length() <= FROM_LENGTH) {
            throw new EmptyTextException("start", "event");
        }
        if (to.length() <= TO_LENGTH) {
            throw new EmptyTextException("end", "event");
        }
        String fromText = from.substring(FROM_LENGTH).trim();
        String toText = to.substring(TO_LENGTH).trim();
        if (!checkTimeForm(fromText) || !checkTimeForm(toText)) {
            throw new TimeFormatException();
        }
        LocalDate fromDay = LocalDate.parse(fromText);
        LocalDate toDay = LocalDate.parse(toText);
        if (fromDay.isAfter(toDay)) {
            throw new TimeInconsistException();
        }
        return new Event(descrip, fromDay, toDay);
    }

    /**
     * Override the interface method.
     */
    @Override
    public String reply() {
        assert this.task != null : "Add task not performed.";
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %s tasks in the list.\n",
                this.task, this.tasks.getListLength());
    }

    private boolean checkTimeForm(String time) {
        try {
            LocalDate.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
