package chipchat.action;

import chipchat.App;
import chipchat.exception.ChipchatException;
import chipchat.storage.Storage;
import chipchat.task.Task;
import chipchat.task.Event;
import chipchat.task.Deadline;
import chipchat.task.Todo;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

import java.time.LocalDate;

public class AddTask extends Action {
    private static final String SUCCESS_MSG = "Got it. I've added this task:";
    private final Task task;
    private final CommandType taskType;

    private AddTask(Task task, CommandType taskType) {
        this.task = task;
        this.taskType = taskType;
    }

    public static Action add(String description, boolean isDone) {
        return new AddTask(new Todo(description, isDone), CommandType.TODO);
    }

    public static Action add(String description, boolean isDone, LocalDate dueBy) {
        return new AddTask(new Deadline(description, isDone, dueBy), CommandType.DEADLINE);
    }

    public static Action add(String description, boolean isDone, LocalDate dateFrom, LocalDate dateTo) {
        return new AddTask(new Event(description, isDone, dateFrom, dateTo), CommandType.EVENT);
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(task);
            ui.showMsg(SUCCESS_MSG);
            ui.showMsg(this.task.toString());
            storage.save(tasks);
        } catch (ChipchatException x) {
            System.err.format("Exception at AddTask::run(), %s\n", x);
        }
    }
}
