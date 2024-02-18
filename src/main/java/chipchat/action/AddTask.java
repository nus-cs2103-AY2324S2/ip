package chipchat.action;

import java.time.LocalDate;
import java.util.List;

import chipchat.exception.ChipchatException;
import chipchat.storage.Storage;
import chipchat.task.Deadline;
import chipchat.task.Event;
import chipchat.task.Task;
import chipchat.task.TaskList;
import chipchat.task.Todo;
import chipchat.ui.Ui;

/**
 * Represents an executable action that will add new tasks to a task list. Subclass of Action.
 */
public class AddTask extends Action {
    private static final String SUCCESS_MSG = "Got it. I've added this task:";
    private final Task task;
    private final CommandType taskType;

    private AddTask(Task task, CommandType taskType) {
        this.task = task;
        this.taskType = taskType;
    }

    /**
     * Factory method to initialize this class with an instantiated Todo task.
     *
     * @param description name/description of task
     * @param isDone completion status of task
     * @return action class that will add a Todo task when executed
     */
    public static AddTask addTodo(String description, boolean isDone, List<String> tags) {
        return new AddTask(new Todo(description, isDone, tags), CommandType.TODO);
    }

    /**
     * Factory method to initialize this class with an instantiated Deadline task.
     *
     * @param description name/description of task
     * @param isDone completion status of task
     * @return action class that will add a deadline task when executed
     */
    public static AddTask addDeadline(String description, boolean isDone, LocalDate dueBy, List<String> tags) {
        return new AddTask(new Deadline(description, isDone, dueBy, tags), CommandType.DEADLINE);
    }

    /**
     * Factory method to initialize this class with an instantiated Event task.
     *
     * @param description name/description of task
     * @param isDone completion status of task
     * @return action class that will add an Event task when executed
     */
    public static AddTask addEvent(String description, boolean isDone, LocalDate dateFrom, LocalDate dateTo, List<String> tags) {
        return new AddTask(new Event(description, isDone, dateFrom, dateTo, tags), CommandType.EVENT);
    }

    /**
     * Adds the pre-initialized task to the given task list and reflect this status through UI and Storage.
     *
     * @param tasks task list to perform operations on
     * @param ui ui of application
     * @param storage storage of application
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(task);
            ui.showMsg(SUCCESS_MSG);
            ui.showMsg(this.task.toString());
            if (storage != null) {
                storage.save(tasks);
            }
        } catch (ChipchatException exc) {
            ui.showErrMsg(exc);
        }
    }
}
