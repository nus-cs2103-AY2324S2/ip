package command;

import java.time.LocalDateTime;

import exception.BuddyException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;


/**
 * Represents Command that updates details of tasks.
 */
public class EditCommand extends Command {
    protected int taskIndex;
    protected String task;
    protected LocalDateTime by;
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates EditCommand with index of task to update.
     *
     * @param taskIndex
     */
    public EditCommand(int taskIndex, String task, LocalDateTime by, LocalDateTime from, LocalDateTime to) {
        assert taskIndex >= 0;
        this.task = task;
        this.taskIndex = taskIndex;
        this.by = by;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BuddyException {
        String response = "I've edited the following task:\n";
        try {
            if (isAllNull()) {
                response = "Nothing changed! Change specified is not valid!";
                return response;
            }

            Task taskToEdit = tasks.getTask(taskIndex);
            editTask(taskToEdit);
            editDeadline(taskToEdit);
            editStartTime(taskToEdit);
            editEndTime(taskToEdit);

            response += taskToEdit;
            return response;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new BuddyException("Not a valid task number!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
    private boolean isAllNull() {
        return task == null && by == null && from == null && to == null;
    }

    private void editTask(Task t) {
        if (task != null) {
            t.setDescription(task);
        }
    }

    private void editDeadline(Task t) throws BuddyException {
        if (by != null) {
            boolean isDeadline = t instanceof Deadline;
            if (isDeadline) {
                Deadline dl = (Deadline) t;
                dl.setBy(by);
            } else {
                throw new BuddyException("Cannot edit by time as task is not a deadline!");
            }
        }
    }

    private void editStartTime(Task t) throws BuddyException {
        if (from != null) {
            boolean isEvent = t instanceof Event;
            if (isEvent) {
                Event ev = (Event) t;
                ev.setFrom(from);
            } else {
                throw new BuddyException("Cannot edit from time as task is not an event!");
            }
        }
    }

    private void editEndTime(Task t) throws BuddyException {
        if (to != null) {
            boolean isEvent = t instanceof Event;
            if (isEvent) {
                Event ev = (Event) t;
                ev.setTo(to);
            } else {
                throw new BuddyException("Cannot edit to time as task is not an event!");
            }
        }
    }
}
