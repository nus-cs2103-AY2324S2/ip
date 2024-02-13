package lrbg.codriver.command;

import lrbg.codriver.data.Deadline;
import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.Task;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

import java.time.LocalDate;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    /** The description of the deadline task. */
    private final String description;
    /** The date of the deadline task. */
    private final LocalDate date;

    /**
     * Creates a new DeadlineCommand with the given description and date.
     * @param description The description of the deadline task.
     * @param date The date of the deadline task.
     */
    public DeadlineCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        Task newTask = new Deadline(this.description, this.date);
        tasks.addTask(newTask);
        return ui.showAddTask(newTask, tasks.getSize());
    }

    /**
     * Returns true if the given object is equal to this command, only for testing purposes.
     * @param obj The object to compare to.
     * @return True if the given object contains the same description and date, false otherwise.
     */
    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof DeadlineCommand) {
            DeadlineCommand other = (DeadlineCommand) obj;
            return other.description.equals(this.description) && other.date.equals(this.date);
        } else {
            return false;
        }
    }
}
