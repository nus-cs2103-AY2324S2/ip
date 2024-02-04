package lrbg.codriver.command;

import lrbg.codriver.data.Deadline;
import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.Task;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate date;

    public DeadlineCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        Task newTask = new Deadline(this.description, this.date);
        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks.size());
    }

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
